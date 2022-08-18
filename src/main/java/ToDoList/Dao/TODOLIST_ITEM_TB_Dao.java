package ToDoList.Dao;

import Project.Dao.PROJ_ITEM_TB_Mapper;
import Project.Dto.Project_item_info;
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

public class TODOLIST_ITEM_TB_Dao {

    private JdbcTemplate jdbcTemplate;
    public TODOLIST_ITEM_TB_Dao(DataSource dataSource){
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public List<Todolist_item_info> select_itemList_byListIdx(Integer list_idx){
        String sql = "SELECT * FROM TODOLIST_ITEM_TB WHERE list_idx=?";
        List<Todolist_item_info> results = jdbcTemplate.query(sql, new TODOLIST_ITEM_TB_Mapper(), list_idx);
        return results.isEmpty() ? null : results;
    }

    public Boolean delete_listItem(Integer list_idx, Integer list_item_idx){
        String sql = "DELETE FROM TODOLIST_ITEM_TB WHERE list_idx=? AND list_item_idx=?";
        int result = jdbcTemplate.update(sql, list_idx, list_item_idx);

        if(result!=0){
            return true;
        }else {
            return false;
        }
    }

    public Boolean insert_todolistItem(Todolist_item_info todolist_item_info){
        String sql = "INSERT INTO TODOLIST_ITEM_TB (list_idx, item_title, done)" +
                "VALUES (?,?,?)";

        int result = jdbcTemplate.update(sql, todolist_item_info.getList_idx(),
                todolist_item_info.getItem_title(), todolist_item_info.getDone());

        if(result!=0){
            return true;
        }else {
            return false;
        }

    }
}
