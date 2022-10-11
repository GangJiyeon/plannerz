package Calendar.Dao;

import Calendar.Dto.MonthlyCommand;
import Calendar.Dto.MonthlyInfo;
import User.Dao.USER_TB_Mapper;
import User.Dto.UserInfo;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

/** 일정 테이블 **/
public class MONTHLY_TB_Dao {

    private JdbcTemplate jdbcTemplate;

    public MONTHLY_TB_Dao(DataSource dataSource){
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    //사용자 아이디로 작성된 일정목록 조회하기
    public List<MonthlyInfo> selectMonthlyInfoList_byUserId(String user_id){

        String sql = "SELECT * FROM MONTHLY_TB WHERE user_id=?";
        List<MonthlyInfo> results = jdbcTemplate.query(sql, new MONTHLY_TB_Mapper(), user_id);
        return results.isEmpty() ? null : results;
    }

    //일정 추가하기
    public void insertMonthly(MonthlyCommand monthlyCommand){
        String sql = "INSERT INTO MONTHLY_TB (user_id, title, start_date, finish_date, bg_color, tx_color, alarm_time) VALUES " +
                "(?,?,?,?,?,?,?)";
        jdbcTemplate.update(sql, monthlyCommand.getUser_id(), monthlyCommand.getTitle(), monthlyCommand.getStart_date(),
                monthlyCommand.getFinish_date(), monthlyCommand.getBg_color(), monthlyCommand.getTx_color(), monthlyCommand.getAlarm_time());
    }

    //일정 삭제하기
    public void deleteMonthlyItem(Integer monthly_idx){
        String sql = "DELETE FROM MONTHLY_TB WHERE monthly_idx=?";
        int result = jdbcTemplate.update(sql, monthly_idx);
    }

    //일정 정보 조회하기
    public MonthlyInfo selectMonthlyItem(String user_id, Integer monthly_idx){

        String sql = "SELECT * FROM MONTHLY_TB WHERE user_id=? AND monthly_idx=?";
        List<MonthlyInfo> results = jdbcTemplate.query(sql, new MONTHLY_TB_Mapper(), user_id, monthly_idx);
        return results.isEmpty() ? null : results.get(0);
    }

    //일정 수정하기
    public void updateMonthly(MonthlyInfo monthlyInfo){
        String sql = "UPDATE MONTHLY_TB SET title=?, start_date=?," +
                "finish_date=?, bg_color=?, tx_color=?, alarm_time=?" +
                "WHERE monthly_idx=? ";
        jdbcTemplate.update(sql, monthlyInfo.getTitle(), monthlyInfo.getStart_date(), monthlyInfo.getFinish_date(),
                monthlyInfo.getBg_color(), monthlyInfo.getTx_color(), monthlyInfo.getAlarm_time(), monthlyInfo.getMonthly_idx());
    }
}
