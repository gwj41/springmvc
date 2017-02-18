package com.robbie.security.token;

import com.robbie.mvc.entity.User;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class CustomAuthenticationToken extends UsernamePasswordAuthenticationToken{
    private int age;

    public CustomAuthenticationToken(String principal, String credentials, int age) {
        super(principal, credentials);
        this.age = age;
    }

    public CustomAuthenticationToken(User principal, String credentials, Collection<? extends GrantedAuthority> authorities, int age) {
        super(principal, credentials, authorities);
        this.age = age;
    }

    public int getAge() {
        return age;
    }
}
