package com.zhongdianwanwei.controller;

import com.alibaba.fastjson.JSONObject;
import com.zhongdianwanwei.model.Dish;
import com.zhongdianwanwei.service.IDishService;
import com.zhongdianwanwei.util.ResponseUtil;
import com.zhongdianwanwei.util.ServletUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.management.remote.JMXServerErrorException;
import java.util.List;

@Controller
public class DishesController {

    @Autowired
    private IDishService dishService;

    @ResponseBody
    @RequestMapping("/getdishes")
    public void getDishes(@RequestParam String name){
        JSONObject jsonObject=new JSONObject();

        List<Dish> dishes=dishService.getDishes(name);

        jsonObject.put("dishes",dishes);
        ResponseUtil.out(ServletUtil.getResponse(),jsonObject);
    }
}
