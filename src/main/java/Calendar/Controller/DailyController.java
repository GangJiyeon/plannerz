package Calendar.Controller;

import Calendar.Service.DailyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DailyController {

    @Autowired
    private DailyService dailyService;

    public void setDailyService(DailyService dailyService){
        this.dailyService = dailyService;
    }

    @GetMapping("/daily")
    public String daily(){
        return "calendar/daily";
    }

}
