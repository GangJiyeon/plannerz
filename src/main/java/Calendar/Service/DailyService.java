package Calendar.Service;

import Calendar.Dao.DAILY_TB_Dao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DailyService {

    @Autowired
    public DAILY_TB_Dao daily_tb_dao;

    public void setDaily_tb_dao(DAILY_TB_Dao daily_tb_dao){
        this.daily_tb_dao = daily_tb_dao;
    }

}
