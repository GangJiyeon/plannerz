package User.Service;

import User.Dao.USER_TB_Dao;
import User.Dto.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;

public class FindAccountService {

    @Autowired
    public USER_TB_Dao user_tb_dao;

    public void setUser_tb_dao(USER_TB_Dao user_tb_dao){
        this.user_tb_dao = user_tb_dao;
    }

    //전화번호로 사용자 정보 조회하기
    public UserInfo findUserId_byPhone(String phone){
        return user_tb_dao.select_userInfo_byPhone(phone);
    }

    //아이디로 사용자 정보 조회하기
    public UserInfo findUserPw_byUserId(String userId){
        return user_tb_dao.select_userInfo(userId);
    }


}
