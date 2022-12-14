package User.Dao;

import User.Dto.JoinCommand;
import User.Dto.LoginCommand;
import User.Dto.UserInfo;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import java.util.List;

/** 회원 계정 정보 테이블 **/
public class USER_TB_Dao {

    private JdbcTemplate jdbcTemplate;

    public USER_TB_Dao(DataSource dataSource){
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    //회원정보 조회(로그인)
    public UserInfo loginCheck(LoginCommand loginCommand){
        String sql = "SELECT * FROM USER_TB WHERE user_id=? AND user_pw=?";
        List<UserInfo> results = jdbcTemplate.query(sql, new USER_TB_Mapper(), loginCommand.getUser_id(), loginCommand.getUser_pw());

        return results.isEmpty() ? null : results.get(0);
    }

    //회원정보 조회
    public UserInfo select_userInfo(String user_id){
        String sql = "SELECT * FROM USER_TB WHERE user_id=?";
        List<UserInfo> results = jdbcTemplate.query(sql, new USER_TB_Mapper(), user_id);

        return results.isEmpty() ? null : results.get(0);
    }

    //회원 전화번호 조회
    public UserInfo select_userInfo_byPhone(String phone){
        String sql = "SELECT * FROM USER_TB WHERE phone=?";
        List<UserInfo> results = jdbcTemplate.query(sql, new USER_TB_Mapper(), phone);

        return results.isEmpty() ? null : results.get(0);
    }

    //아이디 중복 체크
    public Boolean check_id(String user_id){
        String sql = "SELECT * FROM USER_TB WHERE user_id=?";
        List<UserInfo> results = jdbcTemplate.query(sql, new USER_TB_Mapper(), user_id);

        return results.isEmpty() ? true : false;
    }

    //랜덤아이디 중복 체크
    public boolean possible_random_id(String random_id){
        String sql = "SELECT * FROM USER_TB WHERE user_id=?";
        List<UserInfo> results = jdbcTemplate.query(sql, new USER_TB_Mapper(), random_id);
        return results.isEmpty() ? true : false;
    }

    //회원정보 등록
    public void insert(JoinCommand joinCommand){
        String sql = "INSERT INTO USER_TB (user_id, user_pw, user_name, sns, birth, job, img, phone) VALUES " +
                "(?,?,?,?,?,?,?,?)";
        jdbcTemplate.update(sql, joinCommand.getUser_id(), joinCommand.getUser_pw(), joinCommand.getUser_name(), joinCommand.getSns(),
                                joinCommand.getUser_birth(), joinCommand.getJob(), joinCommand.getImg(), joinCommand.getPhone());
    }

    //회원 정보 삭제
    public boolean deleteUserInfo(String id, String pw){
        String sql = "DELETE FROM USER_TB WHERE user_id=? AND user_pw=?";
        int result = jdbcTemplate.update(sql, id, pw);

        if(result!=0){
            return true;
        }else {
            return false;
        }
    }

    //회원정보 수정
    public void update_userInfo(JoinCommand joinCommand){
        String sql = "UPDATE memberInfo SET pw=?, user_name=?, tel=?, addr=? WHERE id=?";
        jdbcTemplate.update(sql);
    }

}
