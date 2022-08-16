package Project.Dao;

import Project.Dto.ProjectInfo;
import Project.Dto.Project_item_info;
import org.springframework.jdbc.core.RowMapper;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;


public class PROJECT_TB_Mapper implements RowMapper<ProjectInfo> {


    @Override
    public ProjectInfo mapRow(ResultSet rs, int i) throws SQLException {

        Integer project_idx = rs.getInt("project_idx");
        String title = rs.getString("title");
        Date target_date = rs.getDate("target_date");
        String user_id = rs.getString("user_id");
        ProjectInfo projectInfo = new ProjectInfo(project_idx, title, target_date, user_id);
        return projectInfo;
    }


}
