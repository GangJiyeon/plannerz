package Project.Dao;

import Project.Dto.Project_item_info;
import Project.Dto.Project_middle_info;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PROJ_ITEM_TB_Mapper implements RowMapper<Project_item_info> {

    @Override
    public Project_item_info mapRow(ResultSet rs, int i) throws SQLException {

        Integer item_idx = rs.getInt("item_idx");
        String title = rs.getString("title");
        Boolean done = rs.getBoolean("done");
        Integer middle_idx = rs.getInt("middle_idx");

        Project_item_info project_item_info = new Project_item_info(item_idx, title, done, middle_idx);

        return project_item_info;
    }
}
