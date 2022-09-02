package Board.Dao;

import Board.Dto.CommentInfo;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class COMMENT_TB_Mapper implements RowMapper<CommentInfo> {

    @Override
    public CommentInfo mapRow(ResultSet rs, int i) throws SQLException {
        Integer comment_idx = rs.getInt("comment_idx");
        String user_id = rs.getString("user_id");
        String content = rs.getString("content");
        Integer board_idx = rs.getInt("board_idx");
        Integer parent_comment = rs.getInt("parent_comment");
        Date insert_date = rs.getDate("insert_date");
        Integer like = rs.getInt("comment_like");

        CommentInfo commentInfo = new CommentInfo(comment_idx, user_id, content, board_idx,
                parent_comment, insert_date, like);

        return commentInfo;
    }
}
