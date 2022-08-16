package UserInfo.Controller;

import UserInfo.Service.AlarmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AlarmController {

    @Autowired
    private AlarmService alarmService;

    public void setAlarmService(AlarmService alarmService){
        this.alarmService = alarmService;
    }

    @GetMapping
    public String alarm(){
        return "mypage/alarm";
    }
}
