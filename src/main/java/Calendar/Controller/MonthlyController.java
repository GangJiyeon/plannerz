package Calendar.Controller;

import Calendar.Dto.MonthlyInfo;
import Calendar.Service.MonthlyService;
import User.Dto.LoginSession;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class MonthlyController {

    @Autowired
    private MonthlyService monthlyService;

    public void setMonthlyService(MonthlyService monthlyService) {
        this.monthlyService = monthlyService;
    }

    @GetMapping("/monthly")
    public String monthly(HttpSession session, Model model){
        LoginSession loginSession = (LoginSession) session.getAttribute("loginSession");
        String user_id = loginSession.getUser_id();
        List<MonthlyInfo> monthlyInfoList = monthlyService.selectMonthlyInfo_byUserId(user_id);

        model.addAttribute("monthlyInfoList", monthlyInfoList);

        model.addAttribute("monthlyInfo", new MonthlyInfo());

        return "calendar/monthly";
    }

    @PostMapping("/monthly/add")
    public String monthlyAdd(HttpSession session, Model model, MonthlyInfo monthlyInfo){



        LoginSession loginSession = (LoginSession) session.getAttribute("loginSession");
        String user_id = loginSession.getUser_id();

        monthlyInfo.setUser_id(user_id);

        System.out.println(monthlyInfo.getBg_color());
        /*
        List<MonthlyInfo> monthlyInfoList = monthlyService.insertNewMonthly(monthlyInfo);
        model.addAttribute("monthlyInfoList", monthlyInfoList);


         */
        model.addAttribute("monthlyInfo", new MonthlyInfo());




        return "calendar/monthly";
    }


}
