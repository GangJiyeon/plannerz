package ToDoList.Dao;

import ToDoList.Dto.Todolist_item_info;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TODOLIST_ITEM_TB_Mapper implements RowMapper<Todolist_item_info> {

    @Override
    public Todolist_item_info mapRow(ResultSet rs, int i) throws SQLException {

        String list_item_idx = rs.getString("list_item_idx");
        Integer list_idx = rs.getInt("list_idx");
        String item_title = rs.getString("item_title");
        Boolean done = rs.getBoolean("done");

        Todolist_item_info todolist_item_info = new Todolist_item_info(list_item_idx, list_idx, item_title, done);
        return todolist_item_info;
    }
}
