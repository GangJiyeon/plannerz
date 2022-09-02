package Calendar.Dao;

import Calendar.Dto.MonthlyInfo;
import User.Dao.USER_TB_Mapper;
import User.Dto.UserInfo;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class MONTHLY_TB_Dao {

    private JdbcTemplate jdbcTemplate;

    public MONTHLY_TB_Dao(DataSource dataSource){
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    //사용자의
    public List<MonthlyInfo> selectMonthlyInfoList_byUserId(String user_id){

        String sql = "SELECT * FROM MONTHLY_TB WHERE user_id=?";
        List<MonthlyInfo> results = jdbcTemplate.query(sql, new MONTHLY_TB_Mapper(), user_id);
        return results.isEmpty() ? null : results;
    }

    public void insertMonthly(MonthlyInfo monthlyInfo){
        String sql = "INSERT INTO MONTHLY_TB (user_id, title, start_date, finish_date, bg_color, tx_color, alarm_time) VALUES " +
                "(?,?,?,?,?,?,?)";
        jdbcTemplate.update(sql, monthlyInfo.getUser_id(), monthlyInfo.getTitle(), monthlyInfo.getStart_date(),
                monthlyInfo.getFinish_date(), monthlyInfo.getBg_color(), monthlyInfo.getTx_color(), monthlyInfo.getAlarm_time());
    }
}
