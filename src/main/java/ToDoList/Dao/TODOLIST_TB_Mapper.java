package ToDoList.Dao;

import ToDoList.Dto.Todolist_info;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TODOLIST_TB_Mapper implements RowMapper<Todolist_info> {
    @Override
    public Todolist_info mapRow(ResultSet rs, int i) throws SQLException {

        Integer list_idx = rs.getInt("list_idx");
        String list_title = rs.getString("list_title");
        String user_idx = rs.getString("user_id");

        Todolist_info todolist_info = new Todolist_info(list_idx, list_title, user_idx);
        return todolist_info;
    }
}
