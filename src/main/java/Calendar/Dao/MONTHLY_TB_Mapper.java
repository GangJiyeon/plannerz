package Calendar.Dao;

import Calendar.Dto.MonthlyInfo;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class MONTHLY_TB_Mapper implements RowMapper<MonthlyInfo> {

    @Override
    public MonthlyInfo mapRow(ResultSet rs, int i) throws SQLException {

         Integer monthly_idx = rs.getInt("monthly_idx");
         String user_id = rs.getString("user_id");
         String title = rs.getString("title");
         LocalDateTime start_date = (LocalDateTime) rs.getObject("start_date");
         LocalDateTime finish_date = (LocalDateTime) rs.getObject("finish_date");
         String bg_color = rs.getString("bg_color");
         String tx_color = rs.getString("tx_color");
         LocalDateTime alarm_time = (LocalDateTime) rs.getObject("alarm_time");

         MonthlyInfo monthlyInfo = new MonthlyInfo(monthly_idx, user_id, title, start_date, finish_date,
                 bg_color, tx_color, alarm_time);

        return monthlyInfo;
    }
}
