package com.zhongdianwanwei.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
//controller层编写Demo
@Controller
public class DemoCtronller {
    @PostMapping("/url")
    public void demoMethod(@RequestParam String demo){

    }
}
