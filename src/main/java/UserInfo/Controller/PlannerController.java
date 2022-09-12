package UserInfo.Controller;

import User.Service.PlannerService;
import org.springframework.stereotype.Controller;

@Controller
public class PlannerController {

    private PlannerService plannerService;

    public void setPlannerService(PlannerService plannerService){
        this.plannerService = plannerService;
    }
}
