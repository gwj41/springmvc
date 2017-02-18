package com.robbie.mvc.utils;

import org.springframework.beans.factory.support.MethodReplacer;

import java.lang.reflect.Method;

public class TestMethodReplacer implements MethodReplacer {
    public Object reimplement(Object obj, Method method, Object[] args) throws Throwable {
        System.out.println("====================Class is " + obj.getClass().getTypeName() + ",Method replaced " + method.getName());
        System.out.println("args is : " + args[0]);
        return "args is : " + args;
    }
}
