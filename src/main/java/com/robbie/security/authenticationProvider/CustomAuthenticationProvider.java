package com.robbie.security.authenticationProvider;

import com.robbie.mvc.entity.User;
import com.robbie.mvc.repository.UserRepository;
import com.robbie.security.token.CustomAuthenticationToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider{
    @Autowired
    private UserRepository userRepository;
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        CustomAuthenticationToken token = (CustomAuthenticationToken)authentication;
        User user = userRepository.findByUsername(token.getName());
        if (user == null) {
            throw new BadCredentialsException("Please sign up first!");
        } else if (!token.getCredentials().toString().equalsIgnoreCase(user.getPassword())){
            throw new BadCredentialsException("The Credentials are invalid!");
        } else if (32 >= token.getAge()){
            throw new BadCredentialsException("Age must older than 32!");
        }
        return new CustomAuthenticationToken(user,user.getPassword(),user.getAuthorities(),user.getAge());
    }

    public boolean supports(Class<?> authentication) {
        return CustomAuthenticationToken.class.equals(authentication);
    }
}
