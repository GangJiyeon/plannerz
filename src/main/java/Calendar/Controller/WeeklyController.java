package Calendar.Controller;

import Calendar.Service.WeeklyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WeeklyController {

    @Autowired
    private WeeklyService weeklyService;

    public void setWeeklyService(WeeklyService weeklyService){
        this.weeklyService = weeklyService;
    }

    @GetMapping("/weekly")
    public String weekly(){
        return "calendar/weekly";
    }
}
