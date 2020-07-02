package com.zhongdianwanwei.intercepter;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 拦截器
 */
@Component
public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

//        //登录拦截的业务逻辑
//        System.out.println("-------登录请求拦截器--------------");
//        System.out.println(request.getRequestURI().toString());
//        Object user = request.getSession().getAttribute("userName");
//        System.out.println(user);
//        if (user == null) {
//            request.getRequestDispatcher("/order").forward(request,response);
//            return false;
//        } else {
//            return true;
//        }
        return true;
    }
}
