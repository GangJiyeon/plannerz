package UserInfo.Dao;

import User.Dto.SNSAccount;
import UserInfo.Dto.AlarmInfo;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import java.util.List;

public class ALARM_TB_Dao {

    private JdbcTemplate jdbcTemplate;

    public ALARM_TB_Dao(DataSource dataSource){
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public AlarmInfo select_user_alarmInfo(String user_id){
        String sql = "SELECT * FROM ALARM_TB WHERE user_id=?";
        List<AlarmInfo> results = jdbcTemplate.query(sql, new ALATM_TB_Mapper(), user_id);

        return results.isEmpty() ? null : results.get(0);
    }

    public void insert_userInfo(String user_id){
        String sql = "INSERT INTO ALARM_TB (user_id) VALUES (?)";
        jdbcTemplate.update(sql, user_id);
    }


    public List<AlarmInfo> select_schedule_true(){
        String sql = "SELECT * FROM ALARM_TB WHERE schedule_alarm=true";
        List<AlarmInfo> results = jdbcTemplate.query(sql, new ALATM_TB_Mapper());
        return results.isEmpty() ? null : results;
    }

    public List<AlarmInfo> select_write_true(int hour){
        String sql = "SELECT * FROM ALARM_TB WHERE write_alarm=true AND write_time=?";
        List<AlarmInfo> results = jdbcTemplate.query(sql, new ALATM_TB_Mapper(), hour);
        return results.isEmpty() ? null : results;
    }

    public List<AlarmInfo> select_check_true(int hour){
        String sql = "SELECT * FROM ALARM_TB WHERE check_alarm=true AND check_time=";
        List<AlarmInfo> results = jdbcTemplate.query(sql, new ALATM_TB_Mapper(), hour);
        return results.isEmpty() ? null : results;
    }

    public void update(AlarmInfo alarmInfo){
        String sql = "UPDATE ALARM_TB SET schedule_alarm=?, write_alarm=?, check_alarm=?, " +
                "write_time=?, check_time=? WHERE user_id=?";
        jdbcTemplate.update(sql, alarmInfo.getSchedule_alarm(), alarmInfo.getWrite_alarm(), alarmInfo.getCheck_alarm(),
                alarmInfo.getWrite_time(), alarmInfo.getCheck_time(), alarmInfo.getUser_id());
    }
}
