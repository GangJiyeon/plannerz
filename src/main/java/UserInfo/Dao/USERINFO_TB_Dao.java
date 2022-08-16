package UserInfo.Dao;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;

public class USERINFO_TB_Dao {

    private JdbcTemplate jdbcTemplate;

    public USERINFO_TB_Dao(DataSource dataSource){
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

}
