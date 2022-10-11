package User.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import javax.servlet.http.HttpSession;

/** 회원계정 찾기 관련 컨트롤러
 1. 뷰 페이지 보여주기 : 로그아웃
 2. 기능 : 로그아웃
 **/
@Controller
public class LogoutController {

    //로그아웃
    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.invalidate();
        return "redirect:/home";
    }


}
