package User.Dao;

import User.Dto.SNSAccount;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SNS_ACCOUNT_TB_Mapper implements RowMapper<SNSAccount> {


    @Override
    public SNSAccount mapRow(ResultSet resultSet, int i) throws SQLException {

        String sns = resultSet.getString("sns");
        String user_id = resultSet.getString("user_id");
        String random_id = resultSet.getString("random_id");
        SNSAccount snsAccount = new SNSAccount(sns, user_id, random_id);
        return snsAccount;
    }
}
