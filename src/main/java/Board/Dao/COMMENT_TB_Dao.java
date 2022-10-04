package Board.Dao;


import Board.Dto.BoardInfo;
import Board.Dto.CommentCommand;
import Board.Dto.CommentInfo;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

//댓글 정보 테이블
public class COMMENT_TB_Dao {

    private JdbcTemplate jdbcTemplate;

    public COMMENT_TB_Dao(DataSource dataSource){
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public List<CommentInfo> selectCommentInfo_byBoardDdx(Integer board_idx){
        String sql = "SELECT * FROM COMMENT_TB WHERE board_idx = ? AND parent_comment IS NULL ORDER BY insert_date DESC ";
        List<CommentInfo> results = jdbcTemplate.query(sql, new COMMENT_TB_Mapper(), board_idx);
        return results.isEmpty() ? null : results ;
    }

    public List<CommentInfo> selectCocComment_byBoardIdx(Integer board_idx){
        String sql = "SELECT * FROM COMMENT_TB WHERE board_idx = ? AND parent_comment IS NOT NULL ORDER BY insert_date DESC";
        List<CommentInfo> results = jdbcTemplate.query(sql, new COMMENT_TB_Mapper(), board_idx);
        return results.isEmpty() ? null : results ;
    }

    public CommentInfo selectCommentInfo_byCommentIdx(Integer comment_idx){
        String sql = "SELECT * FROM COMMENT_TB WHERE comment_idx=?";
        List<CommentInfo> result = jdbcTemplate.query(sql, new COMMENT_TB_Mapper(), comment_idx);
        return result.isEmpty() ? null : result.get(0);
    }
    public void insertCommentInfo(CommentCommand commentCommand){
        String sql = "INSERT INTO COMMENT_TB (user_id, content, board_idx, parent_comment)" +
                " VALUES (?,?,?,?)";

        jdbcTemplate.update(sql, commentCommand.getUser_id(), commentCommand.getContent(), commentCommand.getParent_board_idx(), commentCommand.getParent_comment());

    }

    public void deleteCommentInfo(Integer comment_idx){
        String sql = "DELETE FROM COMMENT_TB WHERE comment_idx=?";
        int result = jdbcTemplate.update(sql,comment_idx);
    }

    public void deleteParent(Integer parent_comment){
        String sql = "DELETE FROM COMMENT_TB WHERE parent_comment=?";
        int result = jdbcTemplate.update(sql,parent_comment);
    }

    public void updateCommentInfo(Integer comment_idx, String content){
        String sql = "UPDATE COMMENT_TB SET content=? WHERE comment_idx=?";
        jdbcTemplate.update(sql, content, comment_idx);
    }

    public void plusCommentLike(CommentInfo commentInfo){
        int num = commentInfo.getLike()+1;
        String sql = "UPDATE COMMENT_TB SET comment_like=? WHERE comment_idx=?";
        jdbcTemplate.update(sql, num, commentInfo.getComment_idx());
    }

    public void minusCommentLike(CommentInfo commentInfo){
        int num = commentInfo.getLike()-1;
        String sql = "UPDATE COMMENT_TB SET comment_like=? WHERE comment_idx=?";
        jdbcTemplate.update(sql, num, commentInfo.getComment_idx());
    }
}
