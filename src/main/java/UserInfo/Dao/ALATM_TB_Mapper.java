package UserInfo.Dao;

import UserInfo.Dto.AlarmInfo;
import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;


public class ALATM_TB_Mapper implements RowMapper<AlarmInfo> {
    @Override
    public AlarmInfo mapRow(ResultSet resultSet, int i) throws SQLException {

         String user_id = resultSet.getString("user_id");
         Boolean schedule_alarm = resultSet.getBoolean("schedule_alarm");
         Boolean write_alarm = resultSet.getBoolean("write_alarm");;
         Boolean check_alarm = resultSet.getBoolean("check_alarm");;
        String write_time = resultSet.getString("write_time");
        String check_time = resultSet.getString("check_time");

        AlarmInfo alarmInfo = new AlarmInfo(user_id, schedule_alarm, write_alarm, check_alarm, write_time, check_time);
        return alarmInfo;
    }
}
