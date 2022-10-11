package User.Service;

import User.Dao.USER_TB_Dao;
import User.Dto.LoginCommand;
import User.Dto.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    @Autowired
    private USER_TB_Dao user_tb_dao;

    public void setUser_tb_dao(USER_TB_Dao user_tb_dao){
        this.user_tb_dao = user_tb_dao;
    }

    //사용자 정보 조회(로그인 시)
    public UserInfo loginService(LoginCommand loginCommand){
        UserInfo userInfo = user_tb_dao.loginCheck(loginCommand);
        return userInfo;
    }

    //사용자 정보 조회
    public UserInfo select_userInfo(String user_id){
        UserInfo userInfo = user_tb_dao.select_userInfo(user_id);
        return userInfo;
    }


}
