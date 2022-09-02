package User.Controller;

import User.Dto.UserInfo;
import User.Service.FindAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class FindAccountController {

    @Autowired
    private FindAccountService findAccountService;

    public void setFindAccountService(FindAccountService findInfoService) {
        this.findAccountService = findInfoService;
    }

    @GetMapping ("/view/find/id")
    public String viewAccountUserId(Model model){
        model.addAttribute("userInfo", new UserInfo());
        return "find_id";
    }

    @GetMapping ("/view/find/pw")
    public String viewAccountUserPw(Model model){
        model.addAttribute("userInfo", new UserInfo());
        return "find_pw";
    }

    @PostMapping ("/find/id")
    public String findUserId(Model model, UserInfo userInfo){
        String phone = userInfo.getPhone();
        UserInfo newUserInfo = findAccountService.findUserId_byPhone(phone);

        if(newUserInfo == null){
            model.addAttribute("success_find", false);
        }else {
            model.addAttribute("success_find", true);
            model.addAttribute("newUserInfo", newUserInfo);
        }

        return "find_id";
    }

    @PostMapping ("/find/pw")
    public String findUserPw(Model model, UserInfo userInfo){
        String user_id = userInfo.getUser_id();
        UserInfo newUserInfo = findAccountService.findUserPw_byUserId(user_id);

        if(newUserInfo == null){
            model.addAttribute("success_find", false);
        }else {
            model.addAttribute("success_find", true);
            model.addAttribute("newUserInfo", newUserInfo);
        }


        return "find_pw";
    }
}
