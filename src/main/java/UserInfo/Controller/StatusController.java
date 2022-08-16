package UserInfo.Controller;

import UserInfo.Service.StatusService;
import UserInfo.Service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class StatusController {

    @Autowired
    private StatusService statusService;

    public void setStatusService(StatusService statusService){
        this.statusService = statusService;
    }

    public String status(){
        return "mypage/status";
    }
}
