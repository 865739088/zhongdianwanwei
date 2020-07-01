package com.zhongdianwanwei.controller;


import com.alibaba.fastjson.JSONObject;
import com.zhongdianwanwei.model.User;
import com.zhongdianwanwei.service.IUserService;
import com.zhongdianwanwei.util.ResponseUtil;
import com.zhongdianwanwei.util.ServletUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@Controller
public class UserController {


    @Autowired
    private IUserService userService;

    @GetMapping("/user")
    public String getUsers() {
        return userService.getUsers().toString();
    }


    @GetMapping
    public ModelAndView getHello(){
        return new ModelAndView("login");
    }

    @GetMapping("/index")
    public ModelAndView getIndex(){
        return new ModelAndView("index");
    }


    @RequestMapping("/login")
    public void login(@RequestParam String userName, @RequestParam String password){
        JSONObject jsonObject=new JSONObject();
        HttpServletRequest request=ServletUtil.getRequest();
        HttpServletResponse response=ServletUtil.getResponse();

        String msg=null;
        User checkUser=userService.getUserByUserName(userName);
        if(checkUser==null){
            msg="用户账号不存在";//用户账号不存在
        }else {
            if(checkUser.getPassword().equals(password)){
                msg= "登陆成功";//登录信息正确，允许登陆
                jsonObject.put("user",checkUser);
                Cookie currentUserName=new Cookie("userName",userName);
                Cookie currentName=new Cookie("name",checkUser.getName());

                //设置存活时间
                currentUserName.setMaxAge(60*60);
                currentName.setMaxAge(60*60);
                //设置全路径
                currentUserName.setPath("/");
                currentName.setPath("/");
                //响应请求 存入cookie
                response.addCookie(currentUserName);
                response.addCookie(currentName);
                HttpSession session=request.getSession();
                session.setAttribute("userName",userName);
                session.setAttribute("name",checkUser.getName());
            }else {
                msg= "密码错误";//密码错误
            }
        }
        jsonObject.put("msg",msg);
        //返回前端json格式数据
        ResponseUtil.out(ServletUtil.getResponse(),jsonObject);
    }
}
