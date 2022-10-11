package Calendar.Controller;

import Calendar.Dto.MonthlyCommand;
import Calendar.Dto.MonthlyInfo;
import Calendar.Dto.MonthlyUpdate;
import Calendar.Service.MonthlyService;
import User.Dto.LoginSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import javax.servlet.http.HttpSession;
import java.util.List;

/** 먼슬리 관련 컨트롤러
 1. 제공기능
 캘린더 조회, 일정 삭제, 일정 추가
 **/
@Controller
public class MonthlyController {

    @Autowired
    private MonthlyService monthlyService;

    public void setMonthlyService(MonthlyService monthlyService) {
        this.monthlyService = monthlyService;
    }

    //캘린더 조회
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

    //캘린더 수정
    @PostMapping("/monthly/update")
    public String monthlyUpdate(MonthlyUpdate monthlyUpdate){

        MonthlyInfo monthlyInfo = new MonthlyInfo();
        monthlyInfo.setMonthly_idx(monthlyUpdate.getUpdate_monthly_idx());
        monthlyInfo.setTitle(monthlyUpdate.getUpdate_title());
        monthlyInfo.setStart_date(monthlyUpdate.getUpdate_start_date());
        monthlyInfo.setFinish_date(monthlyUpdate.getUpdate_finish_date());
        monthlyInfo.setBg_color(monthlyUpdate.getUpdate_bg_color());
        monthlyInfo.setTx_color(monthlyUpdate.getUpdate_tx_color());
        monthlyInfo.setAlarm_time(monthlyUpdate.getUpdate_alarm_time());
        monthlyService.update(monthlyInfo);

        return "redirect:/monthly";
    }

    //일정 추가
    @PostMapping("/monthly/add")
    public String monthlyAdd(HttpSession session, MonthlyCommand monthlyCommand){
        LoginSession loginSession = (LoginSession) session.getAttribute("loginSession");
        String user_id = loginSession.getUser_id();
        monthlyCommand.setUser_id(user_id);

        monthlyService.insertNewMonthly(monthlyCommand);

        return "redirect:/monthly";
    }

    //일정 삭제
    @GetMapping("/monthly/delete")
    public String monthly_delete(@RequestParam ("idx") Integer monthly_idx){

        monthlyService.delete(monthly_idx);
        return "redirect:/monthly";
    }


}
