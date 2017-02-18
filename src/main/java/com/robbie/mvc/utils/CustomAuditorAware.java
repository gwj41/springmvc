package com.robbie.mvc.utils;

import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

@Component
public class CustomAuditorAware implements AuditorAware<String>{
    public String getCurrentAuditor() {
        return "Robbie Gu";
    }
}
