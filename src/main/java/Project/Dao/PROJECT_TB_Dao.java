package Project.Dao;

import Project.Dto.ProjectInfo;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;

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


}
