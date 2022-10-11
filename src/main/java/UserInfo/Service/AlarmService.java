package UserInfo.Service;

import UserInfo.Dao.ALARM_TB_Dao;
import UserInfo.Dto.AlarmInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlarmService {

    @Autowired
    public ALARM_TB_Dao alarm_tb_dao;

    public void setAlarm_tb_dao(ALARM_TB_Dao alarm_tb_dao){
        this.alarm_tb_dao = alarm_tb_dao;
    }

    public AlarmInfo select_alarmInfo(String user_id){
        return alarm_tb_dao.select_user_alarmInfo(user_id);
    }

    public void update(AlarmInfo alarmInfo){
        alarm_tb_dao.update(alarmInfo);
    }

    public void insert(String user_id){
        alarm_tb_dao.insert_userInfo(user_id);
    }

    //스케줄 동의 정보 조회
    public List<AlarmInfo> select_schedule(){
        return alarm_tb_dao.select_schedule_true();
    }

    //스케줄 체크 정보 조회
    public List<AlarmInfo> select_check(int hour){
        return alarm_tb_dao.select_check_true(hour);
    }

    //스케줄 작성 정보 조회
    public List<AlarmInfo> select_write(int hour){
        return alarm_tb_dao.select_write_true(hour);
    }

}
