package com.robbie.mvc.utils;

import com.robbie.mvc.entity.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public enum UserFactory {
    instance;

    private List<User> users = new LinkedList<>();

    UserFactory() {
        final String ROLE = "ROLE_USER";
        GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(ROLE);
        User u = new User();
        u.setFirstName("John");
        u.setLastName("Smith");
        u.setId(1L);
        u.setBirthday(new Date());
        users.add(u);
        User u2 = new User();
        u2.setFirstName("Robbie");
        u2.setLastName("Gu");
        u2.setId(2L);
        u2.setBirthday(new Date());
        users.add(u2);
    }

    public List<User> getUsers() {
        return users;
    }
}
