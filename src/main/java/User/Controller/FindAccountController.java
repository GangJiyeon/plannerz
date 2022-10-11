package User.Controller;

import User.Dto.UserInfo;
import User.Service.FindAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;


/** 회원계정 찾기 관련 컨트롤러
 1. 뷰 페이지 보여주기 : 아이디 찾기, 비밀번호 찾기
 2. 기능 : 아이디 찾기, 비밀번호 찾기
 **/
@Controller
public class FindAccountController {

    @Autowired
    private FindAccountService findAccountService;

    public void setFindAccountService(FindAccountService findInfoService) {
        this.findAccountService = findInfoService;
    }

    //아이디 찾기 view 페이지 보여주기
    @GetMapping ("/view/find/id")
    public String viewAccountUserId(Model model){
        model.addAttribute("userInfo", new UserInfo());
        return "find_id";
    }

    //비밀번호 찾기 view 페이지 보여주기
    @GetMapping ("/view/find/pw")
    public String viewAccountUserPw(Model model){
        model.addAttribute("userInfo", new UserInfo());
        return "find_pw";
    }

    //아이디 찾기
    @PostMapping ("/find/id")
    public String findUserId(Model model, UserInfo userInfo){
        //핸드폰 번호로 사용자 계정 조회
        String phone = userInfo.getPhone();
        UserInfo newUserInfo = findAccountService.findUserId_byPhone(phone);

        //등록된 계정이 아닐 경우
        if(newUserInfo == null){
            model.addAttribute("success_find", false);

        //등록된 계정인 경우
        }else {
            model.addAttribute("success_find", true);
            //찾은 계정 뷰에 전달
            model.addAttribute("newUserInfo", newUserInfo);
        }
        return "find_id";
    }

    //비밀번호 찾기
    @PostMapping ("/find/pw")
    public String findUserPw(Model model, UserInfo userInfo){
        //입력받은 아이디로 계정정보 조회
        String user_id = userInfo.getUser_id();
        UserInfo newUserInfo = findAccountService.findUserPw_byUserId(user_id);

        //등록되지 않은 계정인 경우
        if(newUserInfo == null){
            model.addAttribute("success_find", false);
        //등록된 계졍인 경우
        }else {
            model.addAttribute("success_find", true);
            //조회한 계정 정보 전달
            model.addAttribute("newUserInfo", newUserInfo);
        }
        return "find_pw";
    }
}
