package User.Validator;

import User.Dto.IdCheck;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class User_idValidator implements Validator {

    private static final String user_idRegExp =
            "^[a-zA-Z0-9]*$";

    private Pattern pattern;

    public User_idValidator(){
        pattern = Pattern.compile(user_idRegExp);
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return false;
    }

    @Override
    public void validate(Object target, Errors errors) {

        IdCheck idCheck = (IdCheck) target;

        if(idCheck.getUser_id() == null || idCheck.getUser_id().trim().isEmpty()){
            errors.rejectValue("user_id", "required");
        }else if(idCheck.getUser_id().length()!=idCheck.getUser_id().replaceAll("\\s", "").length()){
            errors.rejectValue("user_id", "cant_space");
        }else {
            Matcher matcher = pattern.matcher(idCheck.getUser_id());
            if(!matcher.matches()){
                errors.rejectValue("user_id", "bad");
            }
        }
    }
}
