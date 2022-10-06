package Calendar.Controller;

import Calendar.Dto.MonthlyCommand;
import Calendar.Dto.MonthlyInfo;
import Calendar.Dto.MonthlyUpdate;
import Calendar.Service.MonthlyService;
import User.Dto.LoginSession;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

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
        model.addAttribute("monthlyUpdate", new MonthlyUpdate());
        model.addAttribute("monthlyCommand", new MonthlyCommand());

        return "monthly";
    }

    @GetMapping("/monthly/item/select")
    public void monthlyItemSelect(HttpSession session, Model model,
                                    @RequestParam ("monthly_idx") Integer monthly_idx,
                                    HttpServletResponse response) throws IOException {

        System.out.println("hi");
        LoginSession loginSession = (LoginSession) session.getAttribute("loginSession");
        String user_id = loginSession.getUser_id();

        MonthlyInfo monthlyInfoItem = monthlyService.selectMonthlyItem(user_id, monthly_idx);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("title", monthlyInfoItem.getTitle());
        jsonObject.put("start", monthlyInfoItem.getStart_date());
        jsonObject.put("end", monthlyInfoItem.getFinish_date());
        jsonObject.put("color", monthlyInfoItem.getBg_color());
        jsonObject.put("textColor", monthlyInfoItem.getTx_color());
        jsonObject.put("id", monthlyInfoItem.getMonthly_idx());
        jsonObject.put("alarm", monthlyInfoItem.getAlarm_time());

        System.out.println(jsonObject);
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter output = response.getWriter();
        output.print(jsonObject);
        output.close();

    }

    @PostMapping("/monthly/update")
    public String monthlyUdate(HttpSession session, Model model, MonthlyUpdate monthlyUpdate){
        LoginSession loginSession = (LoginSession) session.getAttribute("loginSession");
        String user_id = loginSession.getUser_id();

        MonthlyInfo monthlyInfo = new MonthlyInfo();
        monthlyInfo.setMonthly_idx(monthlyUpdate.getUpdate_monthly_idx());
        monthlyInfo.setTitle(monthlyUpdate.getUpdate_title());
        monthlyInfo.setStart_date(monthlyUpdate.getUpdate_start_date());
        monthlyInfo.setFinish_date(monthlyUpdate.getUpdate_finish_date());
        monthlyInfo.setBg_color(monthlyUpdate.getUpdate_bg_color());
        monthlyInfo.setTx_color(monthlyUpdate.getUpdate_tx_color());
        monthlyInfo.setAlarm_time(monthlyUpdate.getUpdate_alarm_time());
        monthlyService.update(monthlyInfo);
        List<MonthlyInfo> monthlyInfoList = monthlyService.selectMonthlyInfo_byUserId(user_id);

        model.addAttribute("monthlyInfoList", monthlyInfoList);
        model.addAttribute("monthlyUpdate", new MonthlyUpdate());
        model.addAttribute("monthlyCommand", new MonthlyCommand());

        return "monthly";
    }

    @PostMapping("/monthly/add")
    public String monthlyAdd(HttpSession session, Model model, MonthlyCommand monthlyCommand){
        LoginSession loginSession = (LoginSession) session.getAttribute("loginSession");
        String user_id = loginSession.getUser_id();

        monthlyCommand.setUser_id(user_id);

        System.out.println(monthlyCommand.getBg_color());

        List<MonthlyInfo> monthlyInfoList = monthlyService.insertNewMonthly(monthlyCommand);
        model.addAttribute("monthlyInfoList", monthlyInfoList);
        model.addAttribute("monthlyUpdate", new MonthlyUpdate());
        model.addAttribute("monthlyCommand", new MonthlyCommand());
        return "monthly";
    }

    @GetMapping("/monthly/delete")
    public String monthly_delete(@RequestParam ("idx") Integer monthly_idx,
                                 HttpSession session, Model model){


        monthlyService.delete(monthly_idx);
        LoginSession loginSession = (LoginSession) session.getAttribute("loginSession");
        String user_id = loginSession.getUser_id();
        List<MonthlyInfo> monthlyInfoList = monthlyService.selectMonthlyInfo_byUserId(user_id);
        model.addAttribute("monthlyInfoList", monthlyInfoList);
        model.addAttribute("monthlyUpdate", new MonthlyUpdate());
        model.addAttribute("monthlyCommand", new MonthlyCommand());

        return "monthly";
    }


}
