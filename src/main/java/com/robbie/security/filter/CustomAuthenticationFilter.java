package com.robbie.security.filter;

import com.robbie.security.token.CustomAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CustomAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        String username = super.obtainUsername(request);
        String password = super.obtainPassword(request);
        int age;
        try {
            age = Integer.valueOf(request.getParameter("age"));
        } catch (NumberFormatException e){
            age = 0;
        }
        CustomAuthenticationToken token = new CustomAuthenticationToken(username,password,age);
        super.setDetails(request,token);
        return super.getAuthenticationManager().authenticate(token);
    }
}
