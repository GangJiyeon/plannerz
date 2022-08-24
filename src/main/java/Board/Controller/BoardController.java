package Board.Controller;

import Board.Dto.BoardCommand;
import Board.Dto.BoardInfo;
import Board.Service.BoardService;
import User.Dto.LoginSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

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
    public String board_form_do(Model model, BoardCommand boardCommand, HttpSession session) {

        LoginSession loginSession = (LoginSession) session.getAttribute("loginSession");
        boardCommand.setUser_id(loginSession.getUser_id());

        BoardInfo boardInfo = boardService.insertBoard(boardCommand);

        model.addAttribute("boardInfo", boardInfo);

        return "board_detail";
    }

    @GetMapping("/board/detail")
    public String board_detail(Model model, HttpSession session, @RequestParam("board_idx") Integer board_idx){
        model.addAttribute("boardCommand", new BoardCommand());

       BoardInfo boardInfo = boardService.selectBoardInfo(board_idx);
       model.addAttribute("boardInfo", boardInfo);

        LoginSession loginSession = (LoginSession) session.getAttribute("loginSession");
        model.addAttribute("loginSession", loginSession);
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
    public String board_delete(Model model, BoardCommand boardCommand, HttpSession session){
        boardService.delete_board(boardCommand.getBoard_idx());
        LoginSession loginSession = (LoginSession) session.getAttribute("loginSession");
        List<BoardInfo> boardInfoList = boardService.selectBoardList_byUserId(loginSession.getUser_id());
        model.addAttribute("boardInfoList", boardInfoList);
        return "board";
    }

    @PostMapping("/board/update")
    public String board_update_view(Model model, BoardCommand boardCommand){

        model.addAttribute("boardCommand", new BoardCommand());
        BoardInfo boardInfo = boardService.selectBoardInfo(boardCommand.getBoard_idx());
        model.addAttribute("boardInfo", boardInfo);

        return "board_update";
    }

    @PostMapping("/board/update.do")
    public String board_update(HttpSession session, Model model, BoardCommand boardCommand){



        boardService.update_board(boardCommand);
        BoardInfo boardInfo = boardService.selectBoardInfo(boardCommand.getBoard_idx());
        model.addAttribute("boardInfo", boardInfo);

        return "board_detail";
    }
}
