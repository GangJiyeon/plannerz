package Board.Controller;

import Board.Dto.*;
import Board.Service.BoardService;
import Board.Validator.BoardCommandValidator;
import Project.Validator.ProjectValidator;
import User.Dto.LoginSession;
import User.Dto.UserInfo;
import User.Service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

/** 게시판 관련 컨트롤러
 1. 제공기능
 게시글 조회, 작성, 수정, 삭제, 좋아요 추가, 좋아요 삭제, 조회수
 내가 쓴 게시글 목록 조회, 전체 게시글 목록 조회
 대댓글 작성, 삭제
 댓글 좋아요 추가, 좋아요 삭제, 수정, 삭제
 **/
@Controller
public class BoardController {

    @Autowired
    private BoardService boardService;

    @Autowired
    private LoginService loginService;

    public  void setLoginService(LoginService loginService){
        this.loginService = loginService;
    }

    public void setBoardService(BoardService boardService) {
        this.boardService = boardService;
    }

    /** 세션에서 사용자 id 가지고 오기 메서드
     provide: Session session, return: String user_id
    **/
    public String find_userSession(HttpSession session){
        LoginSession loginSession = (LoginSession) session.getAttribute("loginSession");
        String user_id = loginSession.getUser_id();
        return user_id;
    }


    //게시글 기본 뷰 보여주기(내가 쓴 게시글 조회)
    @GetMapping("/board")
    public String board(HttpSession session, Model model) {

        //로그인한 사용자 아이디로 작성된 게시글 조회
        List<BoardInfo> boardInfoList = boardService.selectBoardList_byUserId(find_userSession(session));

        //모델에 담아 view로 전송
        model.addAttribute("boardInfoList", boardInfoList);
        return "board";
    }

    //게시글 작성하기 뷰 보여주기
    @GetMapping("/board/form")
    public String board_form(Model model) {
        model.addAttribute("boardCommand", new BoardCommand());
        return "board_form";
    }

    //게시글 수정 뷰 페이지
    @RequestMapping("/board/update")
    public String board_update_view(Model model, BoardCommand boardCommand) {
        model.addAttribute("boardCommand", new BoardCommand());
        model.addAttribute("commentCommand", new CommentCommand());
        model.addAttribute("cocCommand", new CocCommand());

        BoardInfo boardInfo = boardService.selectBoardInfo(boardCommand.getBoard_idx());
        model.addAttribute("boardInfo", boardInfo);

        return "board_update";
    }

    //게시글 조회하기
    @GetMapping("/board/detail")
    public String board_detail(Model model, HttpSession session, @RequestParam("board_idx") Integer board_idx) {

        //사용자 로그인 세션 정보 조회
        LoginSession loginSession = (LoginSession) session.getAttribute("loginSession");

        //해당 게시글 정보 조회하기
        BoardInfo boardInfo = boardService.selectBoardInfo(board_idx);

        //게시글 조회수 업데이트(+1)
        boardService.updateBoardSee(boardInfo);
        boardInfo.setSee(boardInfo.getSee()+1);

        //게시글 작성자 정보 조회하기
        UserInfo board_userInfo = loginService.select_userInfo(boardInfo.getUser_id());

        //게시글 댓글 조회하기
        List<CommentInfo> commentInfoList = boardService.selectCommentInfo_byBoardIdx(board_idx);

        //해당 게시글의 댓글에 달린 대댓글 조회하기
        List<CommentInfo> cocCommentInfoList = boardService.selectCocComment_byBoardIdx(board_idx);

        //댓글 갯수 계산 후 전달하기
        if(commentInfoList != null && cocCommentInfoList !=null){
            model.addAttribute("commentAmount", commentInfoList.size()+cocCommentInfoList.size());
        }else if(commentInfoList != null && cocCommentInfoList ==null){
            model.addAttribute("commentAmount", commentInfoList.size());
        }else {
            model.addAttribute("commentAmount", 0);
        }

        //조회한 정보 모델에 담아서 전송
        model.addAttribute("loginSession", loginSession);               //로그인 세션
        model.addAttribute("board_userInfo", board_userInfo);           //게시글 작성자 정보
        model.addAttribute("boardInfo", boardInfo);                     //게시글 정보
        model.addAttribute("cocCommentInfoList", cocCommentInfoList);   //대댓글 정보
        model.addAttribute("commentInfoList", commentInfoList);         //댓글 정보

        //필요한 커맨드 객체 전송
        model.addAttribute("boardCommand", new BoardCommand());
        model.addAttribute("cocCommand", new CocCommand());
        model.addAttribute("commentCommand",  new CommentCommand());

        return "board_detail";
    }

    //게시글 작성하기
    @PostMapping("/board/form.do")
    public String board_form_do(Model model, BoardCommand boardCommand, Errors errors, HttpSession session,
                                @RequestParam(required = false, value = "file") MultipartFile file ) {

        //입력값 검증
        new BoardCommandValidator().validate(boardCommand, errors);
        if (errors.hasErrors()) {
            return "board_form";
        }

        LoginSession loginSession = (LoginSession) session.getAttribute("loginSession");
        boardCommand.setUser_id(loginSession.getUser_id());
        model.addAttribute("loginSession", loginSession);

        //이미지를 저장할 서버의 경로
        String uploadFolder = "/Users/gangjiyeon/IdeaProjects/Z/src/main/webapp/img/board";

        //사용자가 파일을 업로드 한 경우
        if (!file.isEmpty()) {
            String fileRealName = file.getOriginalFilename(); //파일명 얻기
            long size = file.getSize(); //파일 사이즈
            //서버에 저장할 파일이름(확장자 명 포함)
            String fileExtension = fileRealName.substring(fileRealName.lastIndexOf("."), fileRealName.length());

            //고유한 랜덤 문자를 통해 db와 서버에 저장할 파일명 재구성
            UUID uuid = UUID.randomUUID();
            String[] uuids = uuid.toString().split("-");
            String uniqueName = uuids[0];

            //uuid 적용
            File saveFile = new File(uploadFolder + "//" + uniqueName + fileExtension);
            try {
                file.transferTo(saveFile); // 파일저장
                String savedFile = saveFile.toString();
                boardCommand.setBoard_img1(uniqueName + fileExtension);
            } catch (IllegalStateException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        //파일을 업로드 하지 않았을 경우
        }else {
            //기본 이미지 저장
            boardCommand.setBoard_img1("sunny.jpg");
        }

        //boardInfo db에 저장
        BoardInfo boardInfo = boardService.insertBoard(boardCommand);

        return "redirect:/board/detail?board_idx=" + boardInfo.getBoard_idx();
    }

    //게시글 수정하기
    @PostMapping("/board/update.do")
    public String board_update_do(Model model, BoardCommand boardCommand, Errors errors, HttpSession session,
                                @RequestParam(required = false, value = "file") MultipartFile file ) {

        //입력값 검증
        new BoardCommandValidator().validate(boardCommand, errors);
        if (errors.hasErrors()) {
            return "board_form";
        }

        LoginSession loginSession = (LoginSession) session.getAttribute("loginSession");
        boardCommand.setUser_id(loginSession.getUser_id());
        model.addAttribute("loginSession", loginSession);

        //이미지를 저장할 서버의 경로
        String uploadFolder = "/Users/gangjiyeon/IdeaProjects/Z/src/main/webapp/img/board";

        if (!file.isEmpty()) {
            String fileRealName = file.getOriginalFilename();
            long size = file.getSize();

            //서버에 저장할 파일이름
            String fileExtension = fileRealName.substring(fileRealName.lastIndexOf("."), fileRealName.length());

            UUID uuid = UUID.randomUUID();
            String[] uuids = uuid.toString().split("-");
            String uniqueName = uuids[0];

            File saveFile = new File(uploadFolder + "//" + uniqueName + fileExtension);
            try {
                file.transferTo(saveFile); // 파일저장
                String savedFile = saveFile.toString();
                boardCommand.setBoard_img1(uniqueName + fileExtension);

            } catch (IllegalStateException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else {
            boardCommand.setBoard_img1("sunny.jpg");
        }

        boardService.update(boardCommand);

        return "redirect:/board/detail?board_idx=" + boardCommand.getBoard_idx();
    }

    //게시글 삭제하기
    @RequestMapping("/board/delete")
    public String board_delete(BoardCommand boardCommand) {
        boardService.delete_board(boardCommand.getBoard_idx());
        return "redirect:/board";
    }

    //게시글 좋아요 추가
    @GetMapping("/add/board/like")
    public String addBoardLike(@RequestParam("board_idx") Integer board_idx, HttpSession session){

        //BoardLikeInfo 구성
        BoardLikeInfo boardLikeInfo = new BoardLikeInfo();
        boardLikeInfo.setLike_board_idx(board_idx);
        boardLikeInfo.setLike_user_id(find_userSession(session));

        //게시글 정보 조회
        BoardInfo boardInfo = boardService.selectBoardInfo(board_idx);

        //게시글 좋아요 추가
        boardService.addBoardLike(boardLikeInfo, boardInfo);

        return "redirect:/board/detail?board_idx="+board_idx;
    }

    //게시글 좋아요 삭제
    @GetMapping("/delete/board/like")
    public String deleteBoardLike(@RequestParam("board_idx") Integer board_idx, HttpSession session){

        //BoardLikeInfo 구성
        BoardLikeInfo boardLikeInfo = new BoardLikeInfo();
        boardLikeInfo.setLike_board_idx(board_idx);
        boardLikeInfo.setLike_user_id(find_userSession(session));

        //게시글 정보 조회
        BoardInfo boardInfo = boardService.selectBoardInfo(board_idx);

        //게시글 좋아요 삭제
        boardService.deleteBoardLike(boardLikeInfo, boardInfo);

        return "redirect:/board/detail?board_idx="+board_idx;
    }

    //게시글 목록 조회
    @GetMapping("/board/list")
    public String board_list(Model model, @RequestParam(value = "page", required = false) Integer page) {

        //조회할 페이지 번호 구성
        if (page == null) {
            page = 1;
        }
        Integer start_num = page * 18 - 18;

        //해당 페이지 번호에 해당하는 게시글 목록 조회
        List<BoardInfo> boardInfoList_18 = boardService.selectBoardList_byStartNum(start_num);

        //뷰로 데이터 전송
        model.addAttribute("end", boardService.countBoard()/18+1);
        model.addAttribute("boardInfoList_18", boardInfoList_18);

        return "board_list";
    }

    //댓글 작성
    @PostMapping("/comment/add")
    public String comment_add(HttpSession session,CommentCommand commentCommand) {

        //필요한 정보 구성
        LoginSession loginSession = (LoginSession) session.getAttribute("loginSession");
        String user_img = loginSession.getImg();

        //댓글 작성
        boardService.insertCommentInfo(commentCommand, user_img, loginSession.getSns());

        return "redirect:/board/detail?board_idx="+commentCommand.getParent_board_idx();
    }

    //댓글 수정
    @PostMapping("/comment/update")
    public String comment_update(@RequestParam("comment_idx") Integer comment_idx,
                                 @RequestParam("content") String content,
                                 @RequestParam("board_idx") Integer board_idx) {

        boardService.updateCommentInfo(comment_idx, content);
        return "redirect:/board/detail?board_idx="+board_idx;
    }

    //댓글 삭제
    @GetMapping("/comment/delete")
    public String comment_delete(@RequestParam("comment_idx") Integer comment_idx,
                                 @RequestParam(value = "parent_comment", required = false) String parent_comment,
                                 @RequestParam("board_idx") Integer board_idx) {

        if (parent_comment==null){
            parent_comment = "0";
        }
        Integer comment = Integer.parseInt(parent_comment);

        //댓글 삭제하기
        boardService.deleteCommentInfo(comment_idx, comment);

        return "redirect:/board/detail?board_idx="+board_idx;
    }

    //댓글 좋아요 추가
    @GetMapping("/add/comment/like")
    public String addCommentLike(@RequestParam("comment_idx") Integer comment_idx,
                                 @RequestParam("board_idx") Integer board_idx, HttpSession session){

        //commentLikeInfo 구성
        CommentLikeInfo commentLikeInfo = new CommentLikeInfo();
        commentLikeInfo.setComment_idx(comment_idx);
        commentLikeInfo.setComment_user_id(find_userSession(session));

        //commentInfo 구성
        CommentInfo commentInfo = boardService.selectCommentInfo_byCommentIdx(comment_idx);

        //댓글 좋아요 추가
        boardService.addCommentLike(commentLikeInfo, commentInfo);

        return "redirect:/board/detail?board_idx="+board_idx;
    }

    //댓글 좋아요 삭제
    @GetMapping("/delete/comment/like")
    public String deleteCommentLike(@RequestParam("comment_idx") Integer comment_idx,
                                    @RequestParam("board_idx") Integer board_idx, HttpSession session){

        //commentLikeInfo 구성
        CommentLikeInfo commentLikeInfo = new CommentLikeInfo();
        commentLikeInfo.setComment_idx(comment_idx);
        commentLikeInfo.setComment_user_id(find_userSession(session));

        //commentInfo 조회
        CommentInfo commentInfo = boardService.selectCommentInfo_byCommentIdx(comment_idx);

        //댓글 좋아요 삭제
        boardService.deleteCommentLike(commentLikeInfo, commentInfo);

        return "redirect:/board/detail?board_idx="+board_idx;

    }

    //대댓글 작성
    @PostMapping("/comment/coc/add")
    public String coComment_add(HttpSession session, CocCommand cocCommand) {

        //필요한 객체 정보 구성
        LoginSession loginSession = (LoginSession)session.getAttribute("loginSession");
        CommentCommand commentCommand = new CommentCommand();
        commentCommand.setParent_comment(cocCommand.getC_parent_comment());
        commentCommand.setContent(cocCommand.getC_content());
        commentCommand.setParent_board_idx(cocCommand.getC_parent_board_idx());
        commentCommand.setUser_id(cocCommand.getC_user_id());

        //대댓글 추가
        boardService.insertCommentInfo(commentCommand, loginSession.getImg(), loginSession.getSns());

        return "redirect:/board/detail?board_idx="+commentCommand.getParent_board_idx();
    }


}