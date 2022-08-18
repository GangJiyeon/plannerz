package ToDoList.Dao;

import Project.Dao.PROJECT_TB_Mapper;
import Project.Dto.ProjectInfo;
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

public class TODOLIST_TB_Dao {

    private JdbcTemplate jdbcTemplate;
    public TODOLIST_TB_Dao(DataSource dataSource){
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public List<Todolist_info> selectToDoList_byUserId(String user_id){
        String sql = "SELECT * FROM TODOLIST_TB WHERE user_id=?";
        List<Todolist_info> results = jdbcTemplate.query(sql, new TODOLIST_TB_Mapper(), user_id);
        return results.isEmpty() ? null : results;
    }

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
}
