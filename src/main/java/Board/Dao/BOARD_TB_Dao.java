package Board.Dao;

import Board.Dto.BoardCommand;
import Board.Dto.BoardInfo;
import Board.Dto.BoardLikeInfo;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BOARD_TB_Dao {

    private JdbcTemplate jdbcTemplate;

    public BOARD_TB_Dao(DataSource dataSource){
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    //게시글 작성
    public Integer insertBoard(BoardCommand boardCommand){

        Integer num;
        KeyHolder keyHolder = new GeneratedKeyHolder();

        num = jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection conn) throws SQLException {
                String sql = "INSERT INTO BOARD_TB (user_id, board_title, board_content, " +
                        "board_img1, board_img2, board_img3, board_img4, board_img5) " +
                        "VALUES (?,?,?,?,?,?,?,?)";

                PreparedStatement pstmt = conn.prepareStatement(sql, new String[]{"list_idx"});
                pstmt.setString(1, boardCommand.getUser_id());
                pstmt.setString(2, boardCommand.getBoard_title());
                pstmt.setString(3, boardCommand.getBoard_content());
                pstmt.setString(4, boardCommand.getBoard_img1());
                pstmt.setString(5, boardCommand.getBoard_img2());
                pstmt.setString(6, boardCommand.getBoard_img3());
                pstmt.setString(7, boardCommand.getBoard_img4());
                pstmt.setString(8, boardCommand.getBoard_img5());
                return pstmt;
            }
        }, keyHolder);

        Integer board_idx = Integer.parseInt(String.valueOf(keyHolder.getKey()));
        return board_idx;

    }

    //사용자의 게시글 목록 조회
    public List<BoardInfo> selectBoardList_byUserId(String user_id){
        String sql = "SELECT * FROM BOARD_TB WHERE user_id = ? ORDER BY insert_date DESC ";
        List<BoardInfo> results = jdbcTemplate.query(sql, new BOARD_TB_Mapper(), user_id);
        return results.isEmpty() ? null : results ;
    }

    //게시글 상세 조회
    public BoardInfo selectBoardInfo_byBoardIdx(Integer board_idx){
        String sql = "SELECT * FROM BOARD_TB WHERE board_idx = ?";
        List<BoardInfo> results = jdbcTemplate.query(sql, new BOARD_TB_Mapper(), board_idx);
        return results.isEmpty() ? null : results.get(0);
    }

    //게시글 전체 갯수 조회
    public int count_boardList(){
        String sql = "SELECT COUNT(*) FROM BOARD_TB";
        return jdbcTemplate.queryForObject(sql, Integer.class);
    }

    //페이지번호에 따른 게시글 리스트 조회
    public List<BoardInfo> selectBoardList_byStartNum(Integer start_num){


        String sql = "SELECT * FROM BOARD_TB ORDER BY insert_date DESC LiMiT ?,?";
        List<BoardInfo> results = jdbcTemplate.query(sql, new BOARD_TB_Mapper(), start_num, 18);

        results = results.isEmpty() ? null : results;
        return results;
    }

    //게시글 삭제
    public void delete_board(Integer board_idx){
        String sql = "DELETE FROM BOARD_TB WHERE board_idx=?";
        int result = jdbcTemplate.update(sql, board_idx);
    }

    //게시글 수정
    public void update_board(BoardCommand boardCommand){

        String sql = "UPDATE BOARD_TB SET board_title=?, board_content=?," +
                "board_img1=?, board_img2=?, board_img3=?, board_img4=?, board_img5=?" +
                "WHERE board_idx=? ";

        jdbcTemplate.update(sql, boardCommand.getBoard_title(), boardCommand.getBoard_content(), boardCommand.getBoard_img1(),
                boardCommand.getBoard_img2(), boardCommand.getBoard_img3(), boardCommand.getBoard_img4(), boardCommand.getBoard_img5(),
                boardCommand.getBoard_idx());

    }

    public void updateBoardSee(BoardInfo boardInfo){
        String sql = "UPDATE BOARD_TB SET see=? WHERE board_idx=?";
        jdbcTemplate.update(sql, boardInfo.getSee()+1, boardInfo.getBoard_idx());
    }

    public void addBoardLike(BoardInfo boardInfo){
        int num = boardInfo.getLike()+1;
        String sql = "UPDATE BOARD_TB SET board_like=? WHERE board_idx=?";
        jdbcTemplate.update(sql, num, boardInfo.getBoard_idx());
    }

    public void minusBoardLike(BoardInfo boardInfo){
        int num = boardInfo.getLike()-1;
        String sql = "UPDATE BOARD_TB SET board_like=? WHERE board_idx=?";
        jdbcTemplate.update(sql, num, boardInfo.getBoard_idx());
    }
}
