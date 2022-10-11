package Board.Dao;

import Board.Dto.BoardLikeInfo;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import java.util.List;

/** 게시판 좋아요 테이블 **/
public class BOARD_LIKE_TB_Dao {

    private JdbcTemplate jdbcTemplate;

    public BOARD_LIKE_TB_Dao(DataSource dataSource){
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    //좋아요가 가능한 상태인 지 확인
    public boolean selectBoardLikeInfo(BoardLikeInfo boardLikeInfo){

        //해당 유저가 해당 게시물의 좋아요를 등록한 상태인 지 확인
        //기본값 false
        boolean possible = false;
        String sql = "SELECT * FROM BOARD_LIKE_TB WHERE like_board_idx=? AND like_user_id=?";
        List<BoardLikeInfo> results = jdbcTemplate.query(sql, new BOARD_LIKE_TB_Mapper(), boardLikeInfo.getLike_board_idx(), boardLikeInfo.getLike_user_id());

        //등록하지 않은 상태라면
        if(results.isEmpty()){
            possible = true;
        }

        return possible;
    }

    //게시글 좋아요 등록
    public void insertBoardLike(BoardLikeInfo boardLikeInfo){
        String sql = "INSERT INTO BOARD_LIKE_TB (like_board_idx, like_user_id) VALUES (?, ?)";
        jdbcTemplate.update(sql, boardLikeInfo.getLike_board_idx(), boardLikeInfo.getLike_user_id());
    }

    //게시글 좋아요 삭제
    public void deleteBoardLike(BoardLikeInfo boardLikeInfo){
        String sql = "DELETE FROM BOARD_LIKE_TB WHERE like_board_idx=? AND like_user_id=?";
        int result = jdbcTemplate.update(sql, boardLikeInfo.getLike_board_idx(), boardLikeInfo.getLike_user_id());
        if (result==0){
            System.out.println("fail");
        }
    }
}
