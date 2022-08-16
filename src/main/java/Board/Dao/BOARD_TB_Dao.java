package Board.Dao;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;

public class BOARD_TB_Dao {

    private JdbcTemplate jdbcTemplate;

    public BOARD_TB_Dao(DataSource dataSource){
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

}
