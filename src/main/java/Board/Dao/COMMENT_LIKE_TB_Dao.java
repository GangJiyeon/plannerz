package Board.Dao;

import Board.Dto.BoardLikeInfo;
import Board.Dto.CommentLikeInfo;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class COMMENT_LIKE_TB_Dao {

    private JdbcTemplate jdbcTemplate;

    public COMMENT_LIKE_TB_Dao(DataSource dataSource){
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public boolean selectCommentLikeInfo(CommentLikeInfo commentLikeInfo){

        boolean possible = false;
        String sql = "SELECT * FROM COMMENT_LIKE_TB WHERE comment_idx=? AND comment_user_id=?";
        List<CommentLikeInfo> results = jdbcTemplate.query(sql, new COMMENT_LIKE_TB_Mapper(), commentLikeInfo.getComment_idx(), commentLikeInfo.getComment_user_id());

        if(results.isEmpty()){
            possible = true;
        }

        return possible;
    }


    public void insertCommentLike(CommentLikeInfo commentLikeInfo){
        String sql = "INSERT INTO COMMENT_LIKE_TB (comment_idx, comment_user_id) VALUES (?, ?)";
        jdbcTemplate.update(sql, commentLikeInfo.getComment_idx(), commentLikeInfo.getComment_user_id());
    }

    public void deleteCommentLike(CommentLikeInfo commentLikeInfo){
        String sql = "DELETE FROM COMMENT_LIKE_TB WHERE comment_like_idx=?";
        jdbcTemplate.update(sql, commentLikeInfo.getComment_like_idx());
    }

}
