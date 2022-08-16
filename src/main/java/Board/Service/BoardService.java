package Board.Service;

import Board.Dao.BOARD_TB_Dao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BoardService {

    @Autowired
    public BOARD_TB_Dao board_tb_dao;

    public void setBoard_tb_dao(BOARD_TB_Dao board_tb_dao){
        this.board_tb_dao = board_tb_dao;
    }

}
