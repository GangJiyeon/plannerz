package Project.Dao;

import Project.Dto.Project_item_command;
import Project.Dto.Project_item_info;
import Project.Dto.Project_middle_command;
import Project.Dto.Project_middle_info;
import ToDoList.Dto.Todolist_item_info;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class PROJ_ITEM_TB_Dao {

    private JdbcTemplate jdbcTemplate;

    public PROJ_ITEM_TB_Dao(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public List<Project_item_info> select_itemList_byMidIdx(Integer middle_idx) {
        String sql = "SELECT * FROM PROJ_ITEM_TB WHERE middle_idx=?";
        List<Project_item_info> results = jdbcTemplate.query(sql, new PROJ_ITEM_TB_Mapper(), middle_idx);

        return results.isEmpty() ? null : results;
    }

    public Integer insertProjectItem(Project_item_command project_item_command) {

        Integer num;
        KeyHolder keyHolder = new GeneratedKeyHolder();

        num = jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection conn) throws SQLException {
                String sql = "INSERT INTO PROJ_ITEM_TB (title, middle_idx) VALUES (?,?)";

                PreparedStatement pstmt = conn.prepareStatement(sql, new String[]{"project_id"});
                pstmt.setString(1, project_item_command.getTitle());
                pstmt.setInt(2, project_item_command.getMiddle_idx());

                return pstmt;
            }
        }, keyHolder);

        Integer list_idx = Integer.parseInt(String.valueOf(keyHolder.getKey()));
        return list_idx;

    }


    public void insert_item_for_update(Integer middle_idx) {
        String sql = "INSERT INTO PROJ_ITEM_TB (middle_idx, title)" +
                "VALUES (?,?)";

        int result = jdbcTemplate.update(sql, middle_idx, "내용을 입력하세요");

    }

    public void delete_project_item(Integer item_idx) {
        String sql = "DELETE FROM PROJ_ITEM_TB WHERE item_idx=?";
        jdbcTemplate.update(sql, item_idx);
    }

    public void delete_project_all(Integer middle_idx) {
        String sql = "DELETE FROM PROJ_ITEM_TB WHERE middle_idx=?";
        jdbcTemplate.update(sql, middle_idx);
    }

    public void update_project_item(String title, String item_idx){
        String sql = "UPDATE PROJ_ITEM_TB SET title=? WHERE item_idx=?";
        jdbcTemplate.update(sql, title, item_idx);
    }
}
