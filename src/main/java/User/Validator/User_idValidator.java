package User.Validator;

import User.Dto.IdCheck;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
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
    public boolean supports(Class<?> clazz) {
            return IdCheck.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

        IdCheck idCheck = (IdCheck) target;

        if(idCheck.getCheck_user_id() == null || idCheck.getCheck_user_id().trim().isEmpty()) {
            errors.rejectValue("check_user_id", "required");
        }else if(idCheck.getCheck_user_id().length()!=idCheck.getCheck_user_id().replaceAll("\\s", "").length()){
            errors.rejectValue("check_user_id", "cant_space");
        }else {
            Matcher matcher = pattern.matcher(idCheck.getCheck_user_id());
            if(!matcher.matches()){
                errors.rejectValue("check_user_id", "bad");
            }
        }

    }


}
