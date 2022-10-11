package Calendar.Service;

import Calendar.Dao.MONTHLY_TB_Dao;
import Calendar.Dto.MonthlyCommand;
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

    //사용자 아이디로 일정 목록 조회하기
    public List<MonthlyInfo> selectMonthlyInfo_byUserId(String user_id){
        return monthly_tb_dao.selectMonthlyInfoList_byUserId(user_id);
    }

    //일정 추가하기
    public List<MonthlyInfo> insertNewMonthly(MonthlyCommand monthlyCommand){
        monthly_tb_dao.insertMonthly(monthlyCommand);
        return monthly_tb_dao.selectMonthlyInfoList_byUserId(monthlyCommand.getUser_id());
    }

    //일정 삭제하기
    public void delete(Integer monthly_idx){
        monthly_tb_dao.deleteMonthlyItem(monthly_idx);
    }

    //일정 정보 조회하기
    public MonthlyInfo selectMonthlyItem(String user_id, Integer monthly_id){
        return monthly_tb_dao.selectMonthlyItem(user_id, monthly_id);
    }

    //일정 수정하기
    public void update(MonthlyInfo monthlyInfo){
        monthly_tb_dao.updateMonthly(monthlyInfo);
    }
}
