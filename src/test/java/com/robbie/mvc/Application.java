package com.robbie.mvc;

import com.robbie.mvc.repository.UserRepository;
import com.robbie.mvc.utils.UserRepo;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Application {
    @Test
    public void springdata() {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring/applicationContext.xml");
        UserRepository userRepository = context.getBean(UserRepository.class);
        for (long i = 1; i < 7; i++) {
            userRepository.findByIDs(i);
        }
    }

    @Test
    public void injection() {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring/applicationContext.xml");
        UserRepo userRepository = (UserRepo) context.getBean("userRepo");
        userRepository.printUser();
//        userRepository.replaceMe("Oh my god!!!");
        System.out.println(userRepository.replaceMe("Oh my god!!!"));
    }
}
