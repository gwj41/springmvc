package com.robbie.mvc.utils;

import com.robbie.mvc.entity.User;

import java.util.List;

public class UserRepo {
    public User getUser() {
        return UserFactory.instance.getUsers().get(0);
    }

    public void printUser() {
        getUser().showMyself();
    }

    public String replaceMe(String str) {
        System.out.println("======================Please replace me====================");
        System.out.println("=========argument is " + str + "=============");
        return str;
    }
}
