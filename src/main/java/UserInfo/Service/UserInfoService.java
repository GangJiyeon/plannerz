package UserInfo.Service;

import UserInfo.Dao.USERINFO_TB_Dao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserInfoService {

    @Autowired
    public USERINFO_TB_Dao userinfo_tb_dao;

    public void setUserinfo_tb_dao(USERINFO_TB_Dao userinfo_tb_dao){
        this.userinfo_tb_dao = userinfo_tb_dao;
    }

}
