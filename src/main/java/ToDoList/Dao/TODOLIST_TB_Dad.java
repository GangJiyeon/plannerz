package ToDoList.Dao;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;

public class TODOLIST_TB_Dad {

    private JdbcTemplate jdbcTemplate;

    public TODOLIST_TB_Dad(DataSource dataSource){
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

}
