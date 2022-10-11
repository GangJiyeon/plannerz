package Project.Dao;

import Project.Dto.Project_middle_info;
import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PROJ_MID_TB_Mapper implements RowMapper<Project_middle_info> {

    @Override
    public Project_middle_info mapRow(ResultSet rs, int i) throws SQLException {

        Integer project_middle_idx = rs.getInt("project_middle_idx");
        Integer project_idx = rs.getInt("project_idx");
        String title = rs.getString("title");

        Project_middle_info project_middle_info = new Project_middle_info(project_middle_idx, project_idx, title);
        return project_middle_info;
    }
}
