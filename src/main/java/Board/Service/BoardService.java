package Board.Service;

import Board.Dao.BOARD_TB_Dao;
import Board.Dto.BoardCommand;
import Board.Dto.BoardInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardService {

    @Autowired
    public BOARD_TB_Dao board_tb_dao;

    public void setBoard_tb_dao(BOARD_TB_Dao board_tb_dao){
        this.board_tb_dao = board_tb_dao;
    }

    public BoardInfo insertBoard(BoardCommand boardCommand){
       Integer board_idx = board_tb_dao.insertBoard(boardCommand);
       return board_tb_dao.selectBoardInfo_byBoardIdx(board_idx);
    }
    public BoardInfo selectBoardInfo(Integer board_idx){
        return board_tb_dao.selectBoardInfo_byBoardIdx(board_idx);
    }

    public List<BoardInfo> selectBoardList_byUserId(String user_id){
        return board_tb_dao.selectBoardList_byUserId(user_id);
    }

    public List<BoardInfo> selectBoardList_byStartNum(Integer start_num){
        return board_tb_dao.selectBoardList_byStartNum(start_num);
    }

    public void delete_board(Integer board_idx){
        board_tb_dao.delete_board(board_idx);
    }

    public void update_board(BoardCommand boardCommand){
        board_tb_dao.update_board(boardCommand);
    }
}
