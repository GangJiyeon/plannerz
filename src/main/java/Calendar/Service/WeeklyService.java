package Calendar.Service;

import Calendar.Dao.WEEKLY_TB_Dao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WeeklyService {

    @Autowired
    public WEEKLY_TB_Dao weekly_tb_dao;

    public void setWeekly_tb_dao(WEEKLY_TB_Dao weekly_tb_dao){
        this.weekly_tb_dao = weekly_tb_dao;
    }

}
