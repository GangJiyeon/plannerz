package UserInfo.Service;

import UserInfo.Dao.ALARM_TB_Dao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AlarmService {

    @Autowired
    public ALARM_TB_Dao alarm_tb_dao;

    public void setAlarm_tb_dao(ALARM_TB_Dao alarm_tb_dao){
        this.alarm_tb_dao = alarm_tb_dao;
    }

}
