package User.Validator;

import User.Dto.IdCheck;
import User.Dto.JoinAgreeCommand;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class AgreeValidator implements Validator {

    @Override
    public boolean supports(Class<?> aClass) {
        return false;
    }

    @Override
    public void validate(Object target, Errors errors) {

        JoinAgreeCommand joinAgreeCommand = (JoinAgreeCommand) target;

        if (joinAgreeCommand.getAgree1()!=true){
            errors.reject("agree1", "req_agree");
        }
        if (joinAgreeCommand.getAgree2()!=true){
            errors.reject("agree2", "req_agree");
        }

    }
}
