package UserInfo.Controller;

import User.Dto.LoginSession;
import User.Dto.UserInfo;
import UserInfo.Service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

@Controller
public class UserInfoController {

    @Autowired
    private UserInfoService userInfoService;

    public void setUserInfoService(UserInfoService userInfoService){
        this.userInfoService = userInfoService;
    }

    @GetMapping("userinfo")
    public String userinfo(HttpSession session){

        LoginSession loginSession = (LoginSession) session.getAttribute("loginSession");
        loginSession.getUser_id();



        return "mypage/userinfo";
    }
}
