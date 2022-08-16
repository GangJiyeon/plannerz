package User.Service;

import User.Dao.USER_TB_Dao;
import User.Dto.JoinCommand;
import User.Dto.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

public class JoinService {

    @Autowired
    public USER_TB_Dao user_tb_dao;

    public void setUser_tb_dao(USER_TB_Dao user_tb_dao){
        this.user_tb_dao = user_tb_dao;
    }

    public Boolean checkid(String user_id){
        Boolean notExist = user_tb_dao.check_id(user_id);
        return notExist;
    }

    public void join(JoinCommand joinCommand){
        user_tb_dao.insert(joinCommand);
    }


}
