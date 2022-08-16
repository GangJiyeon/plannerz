package Calendar.Dao;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;

public class WEEKLY_TB_Dao {

    private JdbcTemplate jdbcTemplate;

    public WEEKLY_TB_Dao(DataSource dataSource){
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

}
