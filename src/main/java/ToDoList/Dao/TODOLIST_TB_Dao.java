package ToDoList.Dao;

import ToDoList.Dto.Todolist_info;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

/** 투두리스트 정보 테이블 **/
public class TODOLIST_TB_Dao {

    private JdbcTemplate jdbcTemplate;
    public TODOLIST_TB_Dao(DataSource dataSource){
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    //투두리스트 목록 조회
    public List<Todolist_info> selectToDoList_byUserId(String user_id){
        String sql = "SELECT * FROM TODOLIST_TB WHERE user_id=?";
        List<Todolist_info> results = jdbcTemplate.query(sql, new TODOLIST_TB_Mapper(), user_id);
        return results.isEmpty() ? null : results;
    }

    //투두리스트 등록하기
    public Integer insertTodolist(String list_title, String user_idx){

        Integer num;
        KeyHolder keyHolder = new GeneratedKeyHolder();

        num = jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection conn) throws SQLException {
                String sql = "INSERT INTO TODOLIST_TB (list_title, user_id) VALUES (?,?)";

                PreparedStatement pstmt = conn.prepareStatement(sql, new String[]{"list_idx"});
                pstmt.setString(1, list_title);
                pstmt.setString(2, user_idx);

                return pstmt;
            }
        }, keyHolder);

        Integer list_idx = Integer.parseInt(String.valueOf(keyHolder.getKey()));
        return list_idx;

    }

    //투두리스트 수정하기
    public Integer updateTodolist(Todolist_info todolist_info){
        String sql = "UPDATE TODOLIST_TB SET list_title=? WHERE list_idx=?";
        jdbcTemplate.update(sql, todolist_info.getList_title(), todolist_info.getList_idx());
        return todolist_info.getList_idx();
    }

    //투두리스트 삭제하기
    public Boolean delete_list(Integer list_idx){
        String sql = "DELETE FROM TODOLIST_TB WHERE list_idx=?";
        int result = jdbcTemplate.update(sql, list_idx);

        if(result!=0){
            return true;
        }else {
            return false;
        }
    }
}
