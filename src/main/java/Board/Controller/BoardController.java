package Board.Controller;

import Board.Service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BoardController {

    @Autowired
    private BoardService boardService;

    public void setBoardService(BoardService boardService) {
        this.boardService = boardService;
    }

    @GetMapping("/board")
    public String board(){
        return "board";
    }
}
