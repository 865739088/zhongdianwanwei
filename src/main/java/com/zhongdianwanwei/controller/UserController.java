package com.zhongdianwanwei.controller;

import com.alibaba.fastjson.JSONObject;
import com.zhongdianwanwei.business.IUserService;
import com.zhongdianwanwei.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.lang.invoke.ConstantCallSite;
import java.util.zip.CheckedOutputStream;

@Controller
public class UserController {


    @Autowired
    private IUserService userService;

    @ResponseBody
    @RequestMapping("/user")
    public String getUsers(){
        return userService.getUsers().toString();
    }

    @RequestMapping("")
    public ModelAndView kk(){
        return new ModelAndView("index");
    }

    @RequestMapping("/login")
    public ModelAndView login(@RequestBody User user){
        //根据前端登录传递的用户账号查询数据库中此用户的信息
        String msg=null;
        User checkUser=userService.getUserByUserName(user.getUsername());
        System.out.println("登录用户信息："+user.toString());
        if(checkUser==null){
            msg= "-1";// 用户名不存在;
        }else {
            if(checkUser.getPassword().equals(user.getPassword())){
                msg= "index";//登录用户信息正确
            }else {
                msg= "0";//用户密码错误
            }
        }
        return new ModelAndView(msg);
    }

}
