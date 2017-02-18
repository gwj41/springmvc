package com.robbie.mvc.validators;

import com.robbie.mvc.entity.User;
import org.joda.time.DateTime;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class UserValidator implements Validator {
    public boolean supports(Class<?> aClass) {
        return User.class.equals(aClass);
    }

    public void validate(Object o, Errors errors) {
        User user = (User)o;
        DateTime dateTime = new DateTime(user.getBirthday());
        if (dateTime.isAfterNow())
            errors.rejectValue("birthday","user.birthday","Birthday can not be later than today!");
    }
}
