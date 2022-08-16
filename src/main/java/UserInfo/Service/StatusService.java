package UserInfo.Service;

import UserInfo.Dao.STATUS_TB_Dao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StatusService {

    @Autowired
    public STATUS_TB_Dao status_tb_dao;

    public void setStatus_tb_dao(STATUS_TB_Dao status_tb_dao){
        this.status_tb_dao = status_tb_dao;
    }

}
