package Calendar.Service;

import Calendar.Dao.MONTHLY_TB_Dao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MonthlyService {

    @Autowired
    public MONTHLY_TB_Dao monthly_tb_dao;

    public void setMonthly_tb_dao(MONTHLY_TB_Dao monthly_tb_dao){
        this.monthly_tb_dao = monthly_tb_dao;
    }

}
