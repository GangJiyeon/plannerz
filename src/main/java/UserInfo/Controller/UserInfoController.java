package UserInfo.Controller;

import User.Dto.LoginSession;
import User.Dto.UserInfo;
import User.Service.LoginService;
import UserInfo.Service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
public class UserInfoController {

    @Autowired
    private UserInfoService userInfoService;

    @Autowired
    private LoginService loginService;

    public void setUserInfoService(UserInfoService userInfoService, LoginService loginService){
        this.userInfoService = userInfoService;
        this.loginService = loginService;
    }

    //결제 뷰 페이지 보여주기
    @GetMapping("/pay")
    public String pay(){
        return "INIStdPayRequest";
    }

    //결제 페이지
    @RequestMapping("/INIStdPayReturn")
    public String pay_result(){
        return "INIStdPayReturn";
    }

    //결제 종료 페이지
    @RequestMapping("/close")
    public String close(){
        return "close";
    }

    //결제 팝업 페이지
    @RequestMapping("/popup")
    public String popup(){
        return "popup";
    }

    //회원 정보 조회 메서드
    @GetMapping("/userinfo")
    public String userinfo(Model model, HttpSession session){

        LoginSession loginSession = (LoginSession) session.getAttribute("loginSession");
        if (loginSession == null){
            return "redirect:/login";
        }

        String user_id = loginSession.getUser_id();
        UserInfo userInfo = loginService.select_userInfo(user_id);

        model.addAttribute("selectUserInfo", userInfo);
        model.addAttribute("userInfo", new UserInfo());
        return "userinfo";
    }

    //메세지 전송 테스트 api
    @GetMapping("/message")
    public String message(@RequestParam("tel") String tel, @RequestParam("text") String text){
        AlarmController alarmController = new AlarmController();
        alarmController.message_test(tel, text);
        return "redirect:/userinfo";
    }

}
