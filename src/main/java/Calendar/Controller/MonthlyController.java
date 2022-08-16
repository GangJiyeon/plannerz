package Calendar.Controller;

import Calendar.Service.MonthlyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MonthlyController {

    @Autowired
    private MonthlyService monthlyService;

    public void setMonthlyService(MonthlyService monthlyService) {
        this.monthlyService = monthlyService;
    }

    @GetMapping("/monthly")
    public String monthly(){
        return "calendar/monthly";
    }

    @GetMapping("/test")
    public String test(){
        return "calendar/test";
    }

}
