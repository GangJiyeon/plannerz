package Board.Dao;

import Board.Dto.BoardInfo;
import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class BOARD_TB_Mapper implements RowMapper<BoardInfo> {

    @Override
    public BoardInfo mapRow(ResultSet rs, int i) throws SQLException {

        Integer board_idx = rs.getInt("board_idx");
        String user_id = rs.getString("user_id");
        String board_title = rs.getString("board_title");
        String board_content = rs.getString("board_content");
        String board_img1 = rs.getString("board_img1");
        String board_img2 = rs.getString("board_img2");
        String board_img3 = rs.getString("board_img3");
        String board_img4 = rs.getString("board_img4");
        String board_img5 = rs.getString("board_img5");
        Integer like = rs.getInt("board_like");
        Integer comment = rs.getInt("comment");
        Date insert_date = rs.getDate("insert_date");
        Integer see = rs.getInt("see");

        BoardInfo boardInfo = new BoardInfo(board_idx, user_id, board_title, board_content,
                board_img1, board_img2, board_img3, board_img4, board_img5, like, comment, insert_date, see);
        return boardInfo;
    }


}
