package UserInfo.Controller;

import User.Dto.LoginSession;
import User.Dto.SNSAccount;
import User.Dto.UserInfo;
import User.Service.JoinService;
import User.Service.LoginService;
import UserInfo.Dto.AlarmInfo;
import UserInfo.Service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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

    @GetMapping("/pay")
    public String pay(){
        return "INIStdPayRequest";
    }

    @RequestMapping("/INIStdPayReturn")
    public String pay_result(){
        return "INIStdPayReturn";
    }

    @RequestMapping("/close")
    public String close(){
        return "close";
    }

    @RequestMapping("/popup")
    public String popup(){
        return "popup";
    }

    @GetMapping("userinfo")
    public String userinfo(Model model, HttpSession session){

        LoginSession loginSession = (LoginSession) session.getAttribute("loginSession");
        String user_id = loginSession.getUser_id();

        UserInfo userInfo = loginService.select_userInfo(user_id);

        model.addAttribute("selectUserInfo", userInfo);
        model.addAttribute("userInfo", new UserInfo());
        return "userinfo";
    }


}
