package vn.tinhh.utils.core.functions;

import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.util.ObjectUtils;

public class ValidationUtil {

    public static final String REGEX_PHONE = "(\\+84|0)\\d{9}";

    public static boolean validate(Object o) {
        return !ObjectUtils.isEmpty(o);
    }

    public static boolean validateEmail(String email) {
        if (EmailValidator.getInstance().isValid(email)) {
            return true;
        }
        return false;
    }


    public static boolean validatePhoneNumbers(String phone) {
        return validate(phone) && phone.matches(REGEX_PHONE);
    }
}
