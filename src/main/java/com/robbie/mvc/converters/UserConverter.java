package com.robbie.mvc.converters;

import com.robbie.mvc.entity.User;
import com.robbie.mvc.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

public class UserConverter implements Converter<String, User> {
    @Autowired
    private UserService userService;
    public User convert(String source) {
        return userService.findUserById(Long.valueOf(source));
    }
}
