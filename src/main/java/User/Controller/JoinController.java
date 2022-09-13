package User.Controller;

import User.Dto.*;
import User.Exception.CantMakeUserInfoException;
import User.Exception.DuplicateUserException;
import User.Service.JoinService;
import User.Validator.JoinValidator;
import User.Validator.User_idValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class JoinController {

    @Autowired
    private JoinService joinService;

    public void setLoginService(JoinService joinService) {
        this.joinService = joinService;
    }


    @Autowired
    private NaverLoginBO naverLoginBO;
    private String apiResult = null;

    //뷰 보여주기
    @RequestMapping("/join")
    public String join(Model model, HttpSession session){

        //<<네이버 회원가입>>
        /* 네이버아이디로 인증 URL을 생성하기 위하여 naverLoginBO클래스의 getAuthorizationUrl메소드 호출 */
        String naverAuthUrl = naverLoginBO.getAuthorizationUrl(session);

        //https://nid.naver.com/oauth2.0/authorize?response_type=code&client_id=sE***************&
        //redirect_uri=http%3A%2F%2F211.63.89.90%3A8090%2Flogin_project%2Fcallback&state=e68c269c-5ba9-4c31-85da-54c16c658125
        System.out.println("네이버:" + naverAuthUrl);

        // 네이버
        model.addAttribute("url", naverAuthUrl);


        //<<카카오 회원가입>>
        //카카오 인가코드 발급
        System.out.println("--------- 카카오연동 들어옴 ---------");

        String reqUrl =
                "https://kauth.kakao.com/oauth/authorize"
                        + "?client_id=7b08064aebeb5d53440182eabe007d83"
                        + "&redirect_uri=http://localhost:8080/Z/login/oauth_kakao"
                        + "&response_type=code";

        model.addAttribute("reqUrl", reqUrl);
        return "join1";
    }

    @RequestMapping("/success")
    public String success(){
        return "success";
    }

    @GetMapping("/identify")
    public String identify(){
        return "join1";
    }

    @PostMapping("/view/step2")
    public String go_step2(Model model) {
        return "join2";
    }

    @PostMapping("/view/step_2")
    public String view_step2_2(@RequestParam("user_name") String user_name,
                               @RequestParam("user_birth") String user_birth,
                               @RequestParam("phone") String phone,
                               Model model, HttpSession session){

        session.setAttribute("user_name", user_name);
        session.setAttribute("user_birth", user_birth);
        session.setAttribute("phone", phone);

        model.addAttribute("joinCommand", new JoinCommand());
        model.addAttribute("idCheck", new IdCheck());

        return "join2_2";
    }

    @PostMapping("/view/step3")
    public String go_step3(Model model, HttpSession session) {
        model.addAttribute("joinAgreeCommand", new JoinAgreeCommand());
        session.removeAttribute("user_name");
        session.removeAttribute("user_birth");
        session.removeAttribute("phone");

        return "join3";
    }

    @PostMapping("/view/step4")
    public String go_step4() {
        return "join4";
    }


    //아이디 중복 체크
    @PostMapping("/check/id")
    public String checkId( Model model, HttpSession session, @Valid IdCheck idCheck,
                          Errors errors) {

        //new User_idValidator().validate(idCheck, errors);

        model.addAttribute("joinAgreeCommand", new JoinAgreeCommand());
        //model.addAttribute("idCheck", new IdCheck());
        model.addAttribute("joinCommand", new JoinCommand());


        if (errors.hasErrors()) {
            System.out.println("error");
            return "join2_2";
        }

        try {
            boolean id_notExist = joinService.checkid(idCheck.getCheck_user_id());
            idCheck.setId_not_exist(id_notExist);

            if (id_notExist) {
                session.setAttribute("new_userId", idCheck.getCheck_user_id());
            } else {
                System.out.println("duplicate");
                errors.rejectValue("check_user_id","duplicate");
            }
        } catch (DuplicateUserException e) {

        }



        return "join2_2";

    }

    //회원정보 입력
    @PostMapping("/join/input")
    public String join(Model model, JoinCommand joinCommand,
                       Errors errors, HttpSession session) throws ParseException {


        model.addAttribute("idCheck", new IdCheck());
       // model.addAttribute("joinCommand", new JoinCommand());

        String birth = joinCommand.getBirth();

        String year = birth.substring(0,4);
        String month = birth.substring(4,6);
        String day = birth.substring(6);
        birth = year + "-" + month + "-" + day;
        System.out.println(birth);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

        String phone = joinCommand.getPhone();
        phone = phone.substring(0,3) + "-" + phone.substring(3,7) + "-" + phone.substring(7);
        joinCommand.setPhone(phone);

        Date user_birth = format.parse(birth);
        joinCommand.setUser_birth(user_birth);

        new JoinValidator().validate(joinCommand, errors);

        joinCommand.setUser_id((String) session.getAttribute("new_userId"));
        joinCommand.setSns("none");
        joinCommand.setImg(null);

        if(errors.hasErrors()){
            System.out.println(errors);
            System.out.println("has error");
            return "join2_2";
        }

        try{
            joinService.join(joinCommand);
            session.removeAttribute("user_id");
            model.addAttribute("joinAgreeCommand", new JoinAgreeCommand());
            return "join3";

        }catch (CantMakeUserInfoException e){
            return "join2_2";
        }
    }

    //동의 항목 체크
    @PostMapping("/check/agree")
    public String checkAgree(JoinAgreeCommand joinAgreeCommand){

        if(joinAgreeCommand.getAgree1()==true){
            if(joinAgreeCommand.getAgree2()==true){
                return "join4";
            }else {
                return "join3";
            }
        }else{
            return "join3";
        }


    }

}
