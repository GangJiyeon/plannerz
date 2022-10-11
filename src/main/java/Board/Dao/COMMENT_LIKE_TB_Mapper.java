package Board.Dao;

import Board.Dto.CommentLikeInfo;
import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

public class COMMENT_LIKE_TB_Mapper implements RowMapper<CommentLikeInfo> {

    @Override
    public CommentLikeInfo mapRow(ResultSet rs, int i) throws SQLException {

        Integer comment_like_idx = rs.getInt("comment_like_idx");
        Integer comment_idx = rs.getInt("comment_idx");
        String comment_user_id = rs.getString("comment_user_id");

        CommentLikeInfo commentLikeInfo = new CommentLikeInfo(comment_like_idx, comment_idx, comment_user_id);
        return commentLikeInfo;
    }
}
