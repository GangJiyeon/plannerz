package User.Validator;

import User.Dto.IdCheck;
import User.Dto.JoinCommand;
import User.Dto.LoginCommand;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class JoinValidator implements Validator {

    private static final String user_pwRegExp =
            "^(?=.*[A-Za-z])(?=.*[0-9])(?=.*[$@$!%*#?&])[A-Za-z[0-9]$@$!%*#?&]{8,20}$";

    private Pattern pattern;

    @Override
    public boolean supports(Class<?> aClass) {
        return false;
    }

    @Override
    public void validate(Object target, Errors errors) {

        JoinCommand joinCommand = (JoinCommand) target;

        if(joinCommand.getUser_pw()==null){
            System.out.println("pw empty");
            errors.reject("user_pw", "required");
        }else {
            pattern = Pattern.compile(user_pwRegExp);
            Matcher matcher = pattern.matcher(joinCommand.getUser_pw());

            if(!matcher.matches()){
                errors.reject("user_pw", "bad");
            }

            int length = joinCommand.getUser_pw().length();

            if(length < 8 || 20 < length ){
                System.out.println("pw param error");
                errors.rejectValue("user_pw", "bad");
            }


        }

        if(!joinCommand.getPw_check().equals(joinCommand.getUser_pw())){
            System.out.println("pw not equal");
            errors.rejectValue("pw_check", "not_equal");
        }

    }
}
