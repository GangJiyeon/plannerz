package UserInfo.Service;

import User.Dao.SNS_ACCOUNT_TB_Dao;
import User.Dao.USER_TB_Dao;
import User.Dto.SNSAccount;
import UserInfo.Dao.USERINFO_TB_Dao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserInfoService {

    @Autowired
    public USERINFO_TB_Dao userinfo_tb_dao;

    @Autowired
    public USER_TB_Dao user_tb_dao;

    @Autowired
    public SNS_ACCOUNT_TB_Dao sns_account_tb_dao;

    public void setUserinfo_tb_dao(USERINFO_TB_Dao userinfo_tb_dao){
        this.userinfo_tb_dao = userinfo_tb_dao;
    }

    public void setUser_tb_dao(USER_TB_Dao user_tb_dao){
        this.userinfo_tb_dao = userinfo_tb_dao;
    }

    public void setSns_account_tb_dao(SNS_ACCOUNT_TB_Dao sns_account_tb_dao){
        this.sns_account_tb_dao = sns_account_tb_dao;
    }

    public SNSAccount select_randomId(String user_id){
        SNSAccount select_snsAccount = sns_account_tb_dao.find_randomId(user_id);
        System.out.println(select_snsAccount.getRandom_id());
        return select_snsAccount;

    }

}
