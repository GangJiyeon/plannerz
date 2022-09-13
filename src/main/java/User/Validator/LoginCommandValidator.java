package User.Validator;


import User.Dto.LoginCommand;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class LoginCommandValidator implements Validator {


    @Override
    public boolean supports(Class<?> aClass) {
        return false;
    }

    @Override
    public void validate(Object target, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "user_id", "required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "user_pw", "required");
    }
}
