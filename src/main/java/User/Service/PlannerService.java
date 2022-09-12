package User.Service;

import User.Dao.PLANNER_TB_Dao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlannerService {

    @Autowired
    public PLANNER_TB_Dao planner_tb_dao;

    public void setPlanner_tb_dao(PLANNER_TB_Dao planner_tb_dao){
        this.planner_tb_dao = planner_tb_dao;
    }
}
