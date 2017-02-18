package com.robbie.mvc.interceptors;

import com.robbie.mvc.utils.HitCounter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

public class GlobalInterceptor extends HandlerInterceptorAdapter{
    @Autowired
    private HitCounter hitCounter;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        request.setCharacterEncoding("utf-8");
        hitCounter.setHits(hitCounter.getHits() + 1);
        System.out.println("Hits: " + hitCounter.getHits());
//        request.setAttribute("currentDate",new Date());
        return super.preHandle(request, response, handler);
    }
}
