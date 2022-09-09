package UserInfo.Controller;

import UserInfo.Service.StatusService;
import UserInfo.Service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class StatusController {

    @Autowired
    private StatusService statusService;

    public void setStatusService(StatusService statusService){
        this.statusService = statusService;
    }

    @GetMapping("status")
    public String status(){
        return "status";
    }
}
