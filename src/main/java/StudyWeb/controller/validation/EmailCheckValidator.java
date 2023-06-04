package StudyWeb.controller.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class EmailCheckValidator implements ConstraintValidator<EmailCheck, String> {
    static private final String email1 = "gmail.com";
    static private final String email2 = "naver.com";

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) {
            return false;
        }
        int atIndex = value.indexOf('@');
        String substring = value.substring(atIndex + 1);
        return substring.equals(email1) || substring.equals(email2);
    }
}