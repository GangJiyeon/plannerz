package Project.Dao;

import Project.Dto.Project_middle_info;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;

public class PROJ_MID_TB_Dao {

    private JdbcTemplate jdbcTemplate;

    public PROJ_MID_TB_Dao(DataSource dataSource){
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public List<Project_middle_info> select_mid_info_by_projectIdx(Integer project_idx){
        String sql = "SELECT * FROM PROJ_MID_TB WHERE project_idx=?";
        List<Project_middle_info> results = jdbcTemplate.query(sql, new PROJ_MID_TB_Mapper(), project_idx);

        return results.isEmpty() ? null : results;
    }
}
