package com.zhongdianwanwei.controller;

import com.zhongdianwanwei.service.IUserService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
@Api("用户相关Api")
@RestController
public class UserController {


    @Autowired
    private IUserService userService;

    @GetMapping("/user")
    public String getUsers(){
        return userService.getUsers().toString();
    }
    @GetMapping("/hello")
    public ModelAndView getHello(){
        return new ModelAndView("index");
    }
}
