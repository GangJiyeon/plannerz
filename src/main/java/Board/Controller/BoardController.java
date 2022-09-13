package Board.Controller;

import Board.Dto.*;
import Board.Service.BoardService;
import User.Dto.LoginSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Controller
public class BoardController {

    @Autowired
    private BoardService boardService;


    public void setBoardService(BoardService boardService) {
        this.boardService = boardService;
    }

    @GetMapping("/board")
    public String board(HttpSession session, Model model) {
        model.addAttribute("boardCommand", new BoardCommand());
        LoginSession loginSession = (LoginSession) session.getAttribute("loginSession");
        List<BoardInfo> boardInfoList = boardService.selectBoardList_byUserId(loginSession.getUser_id());
        model.addAttribute("boardInfoList", boardInfoList);
        return "board";
    }

    @GetMapping("/board/form")
    public String board_form(Model model) {
        model.addAttribute("boardCommand", new BoardCommand());
        return "board_form";
    }

    @PostMapping("/board/form.do")
    public String board_form_do(Model model, BoardCommand boardCommand, HttpSession session,
                                @RequestParam(required = false, value = "file") MultipartFile file ) {

        LoginSession loginSession = (LoginSession) session.getAttribute("loginSession");
        boardCommand.setUser_id(loginSession.getUser_id());
        model.addAttribute("loginSession", loginSession);

        //이미지를 저장할 서버의 경로

        String uploadFolder = "/Users/gangjiyeon/Desktop/포폴/plannerz/image/board/img";

        System.out.println(file!=null);
        System.out.println(file);
        if (file!=null) {
            String fileRealName = file.getOriginalFilename(); //파일명을 얻어낼 수 있는 메서드!
            long size = file.getSize(); //파일 사이즈

            //서버에 저장할 파일이름 fileextension으로 .jsp이런식의  확장자 명을 구함
            String fileExtension = fileRealName.substring(fileRealName.lastIndexOf("."), fileRealName.length());

            //고유한 랜덤 문자를 통해 db와 서버에 저장할 파일명을 새롭게 만들기
            UUID uuid = UUID.randomUUID();
            String[] uuids = uuid.toString().split("-");

            String uniqueName = uuids[0];

            // File saveFile = new File(uploadFolder+"\\"+fileRealName); uuid 적용 전
            File saveFile = new File(uploadFolder + "_" + uniqueName + fileExtension);  // 적용 후
            try {
                file.transferTo(saveFile); // 실제 파일 저장메서드(filewriter 작업을 손쉽게 한방에 처리해준다.)

                String savedFile = saveFile.toString();
                boardCommand.setBoard_img1(savedFile);

            } catch (IllegalStateException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }



        BoardInfo boardInfo = boardService.insertBoard(boardCommand);

        model.addAttribute("boardInfo", boardInfo);

        model.addAttribute("boardCommand", new BoardCommand());
        model.addAttribute("commentCommand", new CommentCommand());
        model.addAttribute("cocCommand", new CocCommand());

        return "board_detail";
    }

    @GetMapping("/board/detail")
    public String board_detail(Model model, HttpSession session, @RequestParam("board_idx") Integer board_idx) {


        BoardInfo boardInfo = boardService.selectBoardInfo(board_idx);
        boardService.updateBoardSee(boardInfo);
        boardInfo.setSee(boardInfo.getSee()+1);
        model.addAttribute("boardInfo", boardInfo);

        LoginSession loginSession = (LoginSession) session.getAttribute("loginSession");
        model.addAttribute("loginSession", loginSession);

        List<CommentInfo> commentInfoList = boardService.selectCommentInfo_byBoardIdx(board_idx);
        List<CommentInfo> cocCommentInfoList = boardService.selectCocComment_byBoardIdx(board_idx);

        if(commentInfoList != null){
            model.addAttribute("commentAmount", commentInfoList.size());
        }else {
            model.addAttribute("commentAmount", 0);
        }
        model.addAttribute("cocCommentInfoList", cocCommentInfoList);
        model.addAttribute("commentInfoList", commentInfoList);

        model.addAttribute("boardCommand", new BoardCommand());
        model.addAttribute("commentCommand", new CommentCommand());
        model.addAttribute("cocCommand", new CocCommand());


        return "board_detail";
    }

    @GetMapping("/board/list")
    public String board_list(Model model, @RequestParam(value = "page", required = false) Integer page) {

        if (page == null) {
            page = 1;
        }
        Integer start_num = page * 18 - 18;

        List<BoardInfo> boardInfoList_18 = boardService.selectBoardList_byStartNum(start_num);

        model.addAttribute("boardInfoList_18", boardInfoList_18);
        System.out.println(boardInfoList_18);
        return "board_list";
    }

    @PostMapping("/board/delete")
    public String board_delete(Model model, BoardCommand boardCommand, HttpSession session) {
        boardService.delete_board(boardCommand.getBoard_idx());
        LoginSession loginSession = (LoginSession) session.getAttribute("loginSession");
        List<BoardInfo> boardInfoList = boardService.selectBoardList_byUserId(loginSession.getUser_id());
        model.addAttribute("boardInfoList", boardInfoList);
        return "board";
    }

    @PostMapping("/board/update")
    public String board_update_view(Model model, BoardCommand boardCommand) {
        model.addAttribute("boardCommand", new BoardCommand());
        model.addAttribute("commentCommand", new CommentCommand());
        model.addAttribute("cocCommand", new CocCommand());

        BoardInfo boardInfo = boardService.selectBoardInfo(boardCommand.getBoard_idx());
        model.addAttribute("boardInfo", boardInfo);

        return "board_update";
    }

    @PostMapping("/board/update.do")
    public String board_update(HttpSession session, Model model, BoardCommand boardCommand) {

        model.addAttribute("boardCommand", new BoardCommand());
        model.addAttribute("commentCommand", new CommentCommand());
        model.addAttribute("cocCommand", new CocCommand());

        boardService.update_board(boardCommand);
        BoardInfo boardInfo = boardService.selectBoardInfo(boardCommand.getBoard_idx());
        model.addAttribute("boardInfo", boardInfo);

        return "board_detail";
    }

    //댓글 작성
    @PostMapping("/comment/add")
    public String comment_add(HttpSession session, Model model, CommentCommand commentCommand) {

        boardService.insertCommentInfo(commentCommand);

        model.addAttribute("boardCommand", new BoardCommand());
        model.addAttribute("commentCommand", new CommentCommand());
        model.addAttribute("cocCommand", new CocCommand());

        return "board_detail";
    }

    @PostMapping("comment/coc/add")
    public String coComment_add(HttpSession session, Model model, CocCommand cocCommand) {

        CommentCommand commentCommand = new CommentCommand();
        commentCommand.setParent_comment(cocCommand.getC_parent_comment());
        commentCommand.setContent(cocCommand.getC_content());
        commentCommand.setParent_board_idx(cocCommand.getC_parent_board_idx());
        commentCommand.setUser_id(cocCommand.getC_user_id());
        boardService.insertCommentInfo(commentCommand);

        model.addAttribute("boardCommand", new BoardCommand());
        model.addAttribute("commentCommand", new CommentCommand());
        model.addAttribute("cocCommand", new CocCommand());

        return "board_detail";
    }

    //댓글 삭제
    @GetMapping("/comment/delete")
    public String comment_delete(Model model, @RequestParam("comment_idx") Integer comment_idx) {
        model.addAttribute("boardCommand", new BoardCommand());
        model.addAttribute("commentCommand", new CommentCommand());
        model.addAttribute("cocCommand", new CocCommand());

        boardService.deleteCommentInfo(comment_idx);
        return "board_detail";
    }

    //댓글 수정
    @GetMapping("/comment/update")
    public String comment_update(Model model, @RequestParam("comment_idx") Integer comment_idx,
                                 @RequestParam("content") String content) {

        model.addAttribute("boardCommand", new BoardCommand());
        model.addAttribute("commentCommand", new CommentCommand());
        model.addAttribute("cocCommand", new CocCommand());

        boardService.updateCommentInfo(comment_idx, content);
        return "board_detail";
    }


    @GetMapping("/add/board/like")
    public String addBoardLike(Model model, @RequestParam("board_idx") Integer board_idx, HttpSession session){

        model.addAttribute("boardCommand", new BoardCommand());
        model.addAttribute("commentCommand", new CommentCommand());
        model.addAttribute("cocCommand", new CocCommand());

        LoginSession loginSession = (LoginSession) session.getAttribute("loginSession");
        String user_id = loginSession.getUser_id();
        BoardLikeInfo boardLikeInfo = new BoardLikeInfo();
        boardLikeInfo.setLike_board_idx(board_idx);
        boardLikeInfo.setLike_user_id(user_id);

        BoardInfo boardInfo = boardService.selectBoardInfo(board_idx);

        boardService.addBoardLike(boardLikeInfo, boardInfo);

        return "board_detail";
    }

    @GetMapping("/delete/board/like")
    public String deleteBoardLike(Model model, @RequestParam("board_idx") Integer board_idx, HttpSession session){

        model.addAttribute("boardCommand", new BoardCommand());
        model.addAttribute("commentCommand", new CommentCommand());
        model.addAttribute("cocCommand", new CocCommand());

        LoginSession loginSession = (LoginSession) session.getAttribute("loginSession");
        String user_id = loginSession.getUser_id();
        BoardLikeInfo boardLikeInfo = new BoardLikeInfo();
        boardLikeInfo.setLike_board_idx(board_idx);
        boardLikeInfo.setLike_user_id(user_id);

        BoardInfo boardInfo = boardService.selectBoardInfo(board_idx);

        boardService.deleteBoardLike(boardLikeInfo, boardInfo);
        boardInfo.setLike(boardInfo.getLike()-1);

        return "board_detail";
    }

    @GetMapping("/delete/comment/like")
    public String deleteCommentLike(Model model, @RequestParam("comment_idx") Integer comment_idx, HttpSession session){

        model.addAttribute("boardCommand", new BoardCommand());
        model.addAttribute("commentCommand", new CommentCommand());
        model.addAttribute("cocCommand", new CocCommand());

        LoginSession loginSession = (LoginSession) session.getAttribute("loginSession");
        String user_id = loginSession.getUser_id();

        CommentLikeInfo commentLikeInfo = new CommentLikeInfo();
        commentLikeInfo.setComment_idx(comment_idx);
        commentLikeInfo.setComment_user_id(user_id);

        CommentInfo commentInfo = boardService.selectCommentInfo_byCommentIdx(comment_idx);
        boardService.deleteCommentLike(commentLikeInfo, commentInfo);
        commentInfo.setLike(commentInfo.getLike()-1);

        return "board_detail";
    }

    @GetMapping("/add/comment/like")
    public String addCommentLike(Model model, @RequestParam("comment_idx") Integer comment_idx, HttpSession session){

        model.addAttribute("boardCommand", new BoardCommand());
        model.addAttribute("commentCommand", new CommentCommand());
        model.addAttribute("cocCommand", new CocCommand());

        LoginSession loginSession = (LoginSession) session.getAttribute("loginSession");
        String user_id = loginSession.getUser_id();

        CommentLikeInfo commentLikeInfo = new CommentLikeInfo();
        commentLikeInfo.setComment_idx(comment_idx);
        commentLikeInfo.setComment_user_id(user_id);

        CommentInfo commentInfo = boardService.selectCommentInfo_byCommentIdx(comment_idx);
        boardService.addCommentLike(commentLikeInfo, commentInfo);


        return "board_detail";
    }
}