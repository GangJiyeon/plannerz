package User.Dao;

import User.Dto.SNSAccount;
import User.Dto.UserInfo;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

/** SNS 계정 정보 테이블 **/
public class SNS_ACCOUNT_TB_Dao {

    private JdbcTemplate jdbcTemplate;
    public SNS_ACCOUNT_TB_Dao(DataSource dataSource){
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    //계정 조회
    public SNSAccount snsAccount(SNSAccount snsAccount){
        String sql = "SELECT * FROM SNS_ACCOUNT_TB WHERE sns=? AND user_id=?";
        List<SNSAccount> results = jdbcTemplate.query(sql, new SNS_ACCOUNT_TB_Mapper(), snsAccount.getSns(), snsAccount.getUser_id());
        return results.isEmpty() ? null : results.get(0);
    }

    //등록된 랜덤 아이디 조회
    public SNSAccount find_randomId(String user_id){
        String sql = "SELECT * FROM SNS_ACCOUNT_TB WHERE user_id=?";
        List<SNSAccount> results = jdbcTemplate.query(sql, new SNS_ACCOUNT_TB_Mapper(), user_id);
        return results.isEmpty() ? null : results.get(0);
    }

    //sns 계정 정보 등록
    public void insert_userInfo(String random_id, SNSAccount snsAccount){
        String sql = "INSERT INTO SNS_ACCOUNT_TB (sns, user_id, random_id) VALUES (?,?,?)";
        jdbcTemplate.update(sql, snsAccount.getSns(), snsAccount.getUser_id(), random_id);
    }

    //sns 계정 정보 삭제
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
