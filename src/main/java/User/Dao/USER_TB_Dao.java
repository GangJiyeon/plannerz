package User.Dao;


import User.Dto.JoinCommand;
import User.Dto.LoginCommand;
import User.Dto.SNSAccount;
import User.Dto.UserInfo;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class USER_TB_Dao {

    private JdbcTemplate jdbcTemplate;

    public USER_TB_Dao(DataSource dataSource){
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public UserInfo loginCheck(LoginCommand loginCommand){
        String sql = "SELECT * FROM USER_TB WHERE user_id=? AND user_pw=?";
        List<UserInfo> results = jdbcTemplate.query(sql, new USER_TB_Mapper(), loginCommand.getUser_id(), loginCommand.getUser_pw());

        return results.isEmpty() ? null : results.get(0);
    }

    public UserInfo select_userInfo(String user_id){
        String sql = "SELECT * FROM USER_TB WHERE user_id=?";
        List<UserInfo> results = jdbcTemplate.query(sql, new USER_TB_Mapper(), user_id);

        return results.isEmpty() ? null : results.get(0);
    }

    public UserInfo select_userInfo_byPhone(String phone){
        String sql = "SELECT * FROM USER_TB WHERE phone=?";
        List<UserInfo> results = jdbcTemplate.query(sql, new USER_TB_Mapper(), phone);

        return results.isEmpty() ? null : results.get(0);
    }

    public Boolean check_id(String user_id){
        String sql = "SELECT * FROM USER_TB WHERE user_id=?";
        List<UserInfo> results = jdbcTemplate.query(sql, new USER_TB_Mapper(), user_id);

        return results.isEmpty() ? true : false;
    }

    public boolean possible_random_id(String random_id){
        String sql = "SELECT * FROM USER_TB WHERE user_id=?";
        List<UserInfo> results = jdbcTemplate.query(sql, new USER_TB_Mapper(), random_id);
        return results.isEmpty() ? true : false;
    }

    public void insert(JoinCommand joinCommand){
        String sql = "INSERT INTO USER_TB (user_id, user_pw, user_name, sns, birth, job, img, phone) VALUES " +
                "(?,?,?,?,?,?,?,?)";
        jdbcTemplate.update(sql, joinCommand.getUser_id(), joinCommand.getUser_pw(), joinCommand.getUser_name(), joinCommand.getSns(),
                                joinCommand.getUser_birth(), joinCommand.getJob(), joinCommand.getImg(), joinCommand.getPhone());
    }
}
