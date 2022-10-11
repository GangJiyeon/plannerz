package Board.Dao;

import Board.Dto.CommentLikeInfo;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import java.util.List;

/** 댓글 좋아요 테이블 **/
public class COMMENT_LIKE_TB_Dao {

    private JdbcTemplate jdbcTemplate;

    public COMMENT_LIKE_TB_Dao(DataSource dataSource){
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    //좋아요가 가능한 상태인 지 확인
    public boolean selectCommentLikeInfo(CommentLikeInfo commentLikeInfo){

        //해당 유저가 해당 댓글의 좋아요를 등록한 상태인 지 확인
        //기본값 false
        boolean possible = false;
        String sql = "SELECT * FROM COMMENT_LIKE_TB WHERE comment_idx=? AND comment_user_id=?";
        List<CommentLikeInfo> results = jdbcTemplate.query(sql, new COMMENT_LIKE_TB_Mapper(), commentLikeInfo.getComment_idx(), commentLikeInfo.getComment_user_id());

        //등록되지 않았다면
        if(results.isEmpty()){
            possible = true;
        }

        return possible;
    }

    //게시글 좋아요 추가
    public void insertCommentLike(CommentLikeInfo commentLikeInfo){
        String sql = "INSERT INTO COMMENT_LIKE_TB (comment_idx, comment_user_id) VALUES (?, ?)";
        jdbcTemplate.update(sql, commentLikeInfo.getComment_idx(), commentLikeInfo.getComment_user_id());
    }

    //게시글 좋아요 삭제
    public void deleteCommentLike(CommentLikeInfo commentLikeInfo){
        String sql = "DELETE FROM COMMENT_LIKE_TB WHERE comment_idx=? AND comment_user_id=?";
        jdbcTemplate.update(sql, commentLikeInfo.getComment_idx(), commentLikeInfo.getComment_user_id());
    }

}
