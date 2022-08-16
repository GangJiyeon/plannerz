package Project.Dao;

import Project.Dto.Project_item_info;
import Project.Dto.Project_middle_info;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class PROJ_ITEM_TB_Dao {

    private JdbcTemplate jdbcTemplate;

    public PROJ_ITEM_TB_Dao(DataSource dataSource){
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public List<Project_item_info> select_itemList_byMidIdx(Integer middle_idx){
        String sql = "SELECT * FROM PROJ_ITEM_TB WHERE middle_idx=?";
        List<Project_item_info> results = jdbcTemplate.query(sql, new PROJ_ITEM_TB_Mapper(), middle_idx);

        return results.isEmpty() ? null : results;
    }

}
