package Calendar.Service;

import Calendar.Dao.MONTHLY_TB_Dao;
import Calendar.Dto.MonthlyInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MonthlyService {

    @Autowired
    public MONTHLY_TB_Dao monthly_tb_dao;
    public void setMonthly_tb_dao(MONTHLY_TB_Dao monthly_tb_dao){
        this.monthly_tb_dao = monthly_tb_dao;
    }

    public List<MonthlyInfo> selectMonthlyInfo_byUserId(String user_id){
        return monthly_tb_dao.selectMonthlyInfoList_byUserId(user_id);
    }

    public List<MonthlyInfo> insertNewMonthly(MonthlyInfo monthlyInfo){

        monthly_tb_dao.insertMonthly(monthlyInfo);
        return monthly_tb_dao.selectMonthlyInfoList_byUserId(monthlyInfo.getUser_id());
    }

}
