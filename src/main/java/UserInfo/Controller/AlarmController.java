package UserInfo.Controller;

import UserInfo.Service.AlarmService;
import com.google.gson.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AlarmController {

    private AlarmService alarmService;

    public void setAlarmService(AlarmService alarmService){
        this.alarmService = alarmService;
    }

    @GetMapping("/alarm")
    public String alarm(){
        return "alarm";
    }

    @GetMapping("/kakao")
    public String kakaoMessage(){
        return "kakao";
    }

    @GetMapping("/planner")
    public String view_planner(){
        return "planner";
    }

}
