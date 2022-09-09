package User.Controller;

import User.Dto.IdCheck;
import User.Dto.JoinAgreeCommand;
import User.Dto.JoinCommand;
import User.Dto.UserInfo;
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
import java.util.Date;

@Controller
public class JoinController {

    @Autowired
    private JoinService joinService;

    public void setLoginService(JoinService joinService) {
        this.joinService = joinService;
    }

    //뷰 보여주기
    @RequestMapping("/join")
    public String join(){
        return "join1";
    }

    @RequestMapping("/success")
    public String success(){
        return "join1";
    }

    @PostMapping("/view/step2")
    public String go_step2(Model model) {
        model.addAttribute("JoinCommand", new JoinCommand());
        model.addAttribute("idCheck", new IdCheck());
        return "join2";
    }

    @PostMapping("/view/step3")
    public String go_step3(Model model) {
        return "join3";
    }

    @PostMapping("/view/step4")
    public String go_step4() {
        return "join4";
    }


    //아이디 중복 체크
    @PostMapping("/check/id")
    public String checkId(Model model, @ModelAttribute("idCheck") IdCheck idCheck,
                          Errors errors, HttpSession session) {

        new User_idValidator().validate(idCheck, errors);

        model.addAttribute("idCheck", new IdCheck());
        model.addAttribute("JoinCommand", new JoinCommand());



        if (errors.hasErrors()) {
            System.out.println("error");
            return "join2";
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
        return "join2";

    }

 //   @InitBinder
 //   protected void initBinder(WebDataBinder binder){
 //       binder.setValidator(new User_idValidator());
 //   }


    //회원정보 입력
    @PostMapping("/join/input")
    public String join(Model model, JoinCommand joinCommand,
                       Errors errors, HttpSession session) {
        model.addAttribute("JoinAgreeCommand", new JoinAgreeCommand());
        model.addAttribute("idCheck", new IdCheck());
        model.addAttribute("JoinCommand", new JoinCommand());

        /*
        new JoinValidator().validate(joinCommand, errors);
         */
        joinCommand.setUser_id((String) session.getAttribute("new_userId"));
        joinCommand.setSns("none");
        joinCommand.setImg(null);



        /*
        if(errors.hasErrors()){
            System.out.println("has error");
            return "join2";
        }

         */

        try{
            joinService.join(joinCommand);
            session.removeAttribute("user_id");
            return "join3";

        }catch (CantMakeUserInfoException e){
            return "join2";
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
