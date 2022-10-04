package Project.Dao;

import Project.Dto.ProjectCommand;
import Project.Dto.ProjectInfo;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class PROJECT_TB_Dao {

    private JdbcTemplate jdbcTemplate;

    public PROJECT_TB_Dao(DataSource dataSource){
        jdbcTemplate = new JdbcTemplate(dataSource);
    }


    public List<ProjectInfo> selectProjectList_byUserId(String user_id){
        String sql = "SELECT * FROM PROJECT_TB WHERE user_id=?";
        List<ProjectInfo> results = jdbcTemplate.query(sql, new PROJECT_TB_Mapper(), user_id);

        return results.isEmpty() ? null : results;
    }


    public Integer insertProject(ProjectCommand projectCommand){

        Integer num;
        KeyHolder keyHolder = new GeneratedKeyHolder();

        num = jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection conn) throws SQLException {
                String sql = "INSERT INTO PROJECT_TB (title, target_date, user_id) VALUES (?,?,?)";

                PreparedStatement pstmt = conn.prepareStatement(sql, new String[]{"project_idx"});
                pstmt.setString(1, projectCommand.getTitle());
                pstmt.setObject(2, (Object) projectCommand.getTarget_date());
                pstmt.setString(3, projectCommand.getUser_id());

                return pstmt;
            }
        }, keyHolder);

        Integer list_idx = Integer.parseInt(String.valueOf(keyHolder.getKey()));
        return list_idx;

    }

    public void delete_project(Integer project_idx){
        String sql = "DELETE FROM PROJECT_TB WHERE project_idx=?";
        int result = jdbcTemplate.update(sql, project_idx);
    }

    public void update_project(ProjectCommand projectCommand, Integer project_idx){
        String sql = "UPDATE PROJECT_TB SET title=?, target_date=? WHERE project_idx=?";
        jdbcTemplate.update(sql, projectCommand.getTitle(), projectCommand.getTarget_date(), project_idx);
    }

}
