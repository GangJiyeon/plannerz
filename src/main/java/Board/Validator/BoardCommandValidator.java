package Board.Validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class BoardCommandValidator implements Validator {


    @Override
    public boolean supports(Class<?> aClass) {
        return false;
    }

    @Override
    public void validate(Object target, Errors errors) {

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "board_title", "required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "board_content", "required");

    }
}
