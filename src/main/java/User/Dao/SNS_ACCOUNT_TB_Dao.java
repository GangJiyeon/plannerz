package User.Dao;

import User.Dto.SNSAccount;
import User.Dto.UserInfo;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class SNS_ACCOUNT_TB_Dao {

    private JdbcTemplate jdbcTemplate;
    public SNS_ACCOUNT_TB_Dao(DataSource dataSource){
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public SNSAccount snsAccount(SNSAccount snsAccount){
        String sql = "SELECT * FROM SNS_ACCOUNT_TB WHERE sns=? AND user_id=?";
        List<SNSAccount> results = jdbcTemplate.query(sql, new SNS_ACCOUNT_TB_Mapper(), snsAccount.getSns(), snsAccount.getUser_id());
        return results.isEmpty() ? null : results.get(0);
    }

    public SNSAccount find_randomId(String user_id){
        String sql = "SELECT * FROM SNS_ACCOUNT_TB WHERE user_id=?";
        List<SNSAccount> results = jdbcTemplate.query(sql, new SNS_ACCOUNT_TB_Mapper(), user_id);
        return results.isEmpty() ? null : results.get(0);
    }


    public void insert_userInfo(String random_id, SNSAccount snsAccount){
        String sql = "INSERT INTO SNS_ACCOUNT_TB (sns, user_id, random_id) VALUES (?,?,?)";
        jdbcTemplate.update(sql, snsAccount.getSns(), snsAccount.getUser_id(), random_id);
    }


    public boolean deleteSNS_UserInfo(String random_id){
        String sql = "DELETE FROM SNS_ACCOUNT_TB WHERE random_id=?";
        int result = jdbcTemplate.update(sql, random_id);

        if(result!=0){
            return true;
        }else {
            return false;
        }
    }
}
