package com.robbie.mvc.controllers;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalControllerAdvice {
    @ModelAttribute(value = "currentDate")
    public Date getCurrentDate() {
        return new Date();
    }

    @ModelAttribute(value = "roles")
    public Map<String, String> getRoles() {
        Map<String, String> roles = new LinkedHashMap<>();
        roles.put("ROLE_USER","User");
        roles.put("ROLE_FOO","Admin");
        return roles;
    }

    @ExceptionHandler(NullPointerException.class)
    public String handleError(HttpServletRequest request,NullPointerException e) {
        e.printStackTrace();
        return "exceptions/exception";
    }
}
