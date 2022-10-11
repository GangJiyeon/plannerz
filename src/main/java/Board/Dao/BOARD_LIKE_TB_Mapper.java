package Board.Dao;

import Board.Dto.BoardLikeInfo;
import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;
public class BOARD_LIKE_TB_Mapper implements RowMapper<BoardLikeInfo> {
    @Override
    public BoardLikeInfo mapRow(ResultSet rs, int i) throws SQLException {
        Integer like_idx = rs.getInt("like_idx");
        Integer like_board_idx = rs.getInt("like_board_idx");
        String like_user_id = rs.getString("like_user_id");

        BoardLikeInfo boardLikeInfo = new BoardLikeInfo(like_idx, like_board_idx, like_user_id);
        return boardLikeInfo;
    }
}
