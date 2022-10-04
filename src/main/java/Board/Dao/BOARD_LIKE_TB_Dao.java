package Board.Dao;

import Board.Dto.BoardLikeInfo;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class BOARD_LIKE_TB_Dao {

    private JdbcTemplate jdbcTemplate;

    public BOARD_LIKE_TB_Dao(DataSource dataSource){
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public boolean selectBoardLikeInfo(BoardLikeInfo boardLikeInfo){

        boolean possible = false;
        String sql = "SELECT * FROM BOARD_LIKE_TB WHERE like_board_idx=? AND like_user_id=?";
        List<BoardLikeInfo> results = jdbcTemplate.query(sql, new BOARD_LIKE_TB_Mapper(), boardLikeInfo.getLike_board_idx(), boardLikeInfo.getLike_user_id());

        if(results.isEmpty()){
            possible = true;
        }

        return possible;
    }


    public void insertBoardLike(BoardLikeInfo boardLikeInfo){
        String sql = "INSERT INTO BOARD_LIKE_TB (like_board_idx, like_user_id) VALUES (?, ?)";
        jdbcTemplate.update(sql, boardLikeInfo.getLike_board_idx(), boardLikeInfo.getLike_user_id());

    }

    public void deleteBoardLike(BoardLikeInfo boardLikeInfo){
        String sql = "DELETE FROM BOARD_LIKE_TB WHERE like_board_idx=? AND like_user_id=?";
        int result = jdbcTemplate.update(sql, boardLikeInfo.getLike_board_idx(), boardLikeInfo.getLike_user_id());
        if (result==0){
            System.out.println("fail");
        }
    }
}
