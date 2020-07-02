package com.zhongdianwanwei.controller;


import com.zhongdianwanwei.model.User;
import com.zhongdianwanwei.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

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

    @PostMapping
    public int login(@RequestBody User user){

        User checkUser=userService.getUserByUserName(user.getUsername());
        if(checkUser==null){
            return -1;//用户账号不存在
        }else {
            if(checkUser.getPassword().equals(user.getPassword())){
                return 1;//登录信息正确，允许登陆
            }else {
                return 0;//密码错误
            }
        }
    }
}
