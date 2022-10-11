package User.Dao;

import User.Dto.UserInfo;
import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class USER_TB_Mapper implements RowMapper<UserInfo> {

    @Override
    public UserInfo mapRow(ResultSet rs, int i) throws SQLException {

        String user_id = rs.getString("user_id");
        String user_pw = rs.getString("user_pw");
        String user_name = rs.getString("user_name");
        String sns = rs.getString("sns");
        Date join_date = rs.getDate("join_date");
        Date birth = rs.getDate("birth");
        String job = rs.getString("job");
        String img = rs.getString("img");
        String phone = rs.getString("phone");

        UserInfo userInfo = new UserInfo(user_id, user_pw, user_name, sns, join_date, birth, job, img, phone);
        return userInfo;
    }
}
