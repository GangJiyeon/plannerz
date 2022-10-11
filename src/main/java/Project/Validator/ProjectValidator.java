package Project.Validator;

import Project.Dto.ProjectCommand;
import User.Dto.JoinCommand;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ProjectValidator implements Validator {


    @Override
    public boolean supports(Class<?> aClass) {
        return false;
    }

    @Override
    public void validate(Object target, Errors errors) {

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "target_date", "required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "title", "required");

    }
}
