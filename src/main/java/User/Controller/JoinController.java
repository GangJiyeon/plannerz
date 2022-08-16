package User.Controller;

import User.Dto.IdCheck;
import User.Dto.JoinCommand;
import User.Exception.CantMakeUserInfoException;
import User.Exception.DuplicateUserException;
import User.Service.JoinService;
import User.Validator.JoinValidator;
import User.Validator.User_idValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
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

    @PostMapping("/view/step2")
    public String go_step2() {

        return "join2";
    }

    @PostMapping("/view/step3")
    public String go_step3() {

        return "join3";
    }

    @PostMapping("/view/step4")
    public String go_step4() {

        return "join4";
    }

    @PostMapping("/check/id")
    public String checkId(Model model, @Valid IdCheck idCheck,
                          Errors errors, HttpSession session) {

        idCheck.setId_exist(false);
        new User_idValidator().validate(idCheck, errors);

        if (errors.hasErrors()) {
            System.out.println("error");
            return "join2";
        }

        try {
            boolean id_notexist = joinService.checkid(idCheck.getUser_id());

            idCheck.setId_exist(id_notexist);

            if (id_notexist) {
                model.addAttribute("user_id", idCheck.getUser_id());
                model.addAttribute("duplicate", idCheck.getId_exist());
                session.setAttribute("user_id", idCheck.getUser_id());
            } else {
                System.out.println("duplicate");
            }
        } catch (DuplicateUserException e) {

        }
        return "join2";

    }

    @PostMapping("/join/input")
    public String join(Model model, @Valid JoinCommand joinCommand,
                       Errors errors, HttpSession session) {


        new JoinValidator().validate(joinCommand, errors);

        joinCommand.setUser_id((String) session.getAttribute("user_id"));
        joinCommand.setImg(null);

        System.out.println(joinCommand.getUser_id());
        System.out.println(joinCommand.getUser_pw());
        System.out.println(joinCommand.getPw_check());
        System.out.println(joinCommand.getBirth());
        System.out.println(joinCommand.getUser_name());
        System.out.println(joinCommand.getJob());
        System.out.println(joinCommand.getImg());


        if(errors.hasErrors()){
            System.out.println("has error");
            return "join2";
        }

        try{
            joinService.join(joinCommand);
            session.removeAttribute("user_id");
            return "join3";

        }catch (CantMakeUserInfoException e){
            return "join2";
        }
    }
}
