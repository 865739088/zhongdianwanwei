package com.zhongdianwanwei.controller;
/*
*  用于处理管理员统计请求controller
*  author：金鑫
 */
import com.zhongdianwanwei.model.AdminCount;
import com.zhongdianwanwei.service.IAdminCountService;
import io.swagger.annotations.Api;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
@Api("管理员统计Api")
@Controller
public class AdminCountCtroller {

    @Resource(name = "admincountservice")
    private IAdminCountService adminCountService;
    @RequestMapping(value = "/getTodayAdminCount",method = RequestMethod.GET)
    @ResponseBody
    public  List<AdminCount> getTodayAdminCount(@RequestParam int page){
        List<AdminCount> result=null;
        result = adminCountService.getTodayAdminCount(page, 5);
        return result;
    }
    @RequestMapping(value = "/getAllAdminCount",method = RequestMethod.GET)
    @ResponseBody
    public  List<AdminCount> getAllAdminCount(){
        List<AdminCount> result=null;
        result = adminCountService.getAllAdminCount();
        return result;
    }
    @ResponseBody
    @RequestMapping(value = "/getAdminCount",method = RequestMethod.GET)
    public  List<AdminCount> getAdminCount(@RequestParam Date date,@RequestParam int page,@RequestParam int counts){
        List<AdminCount> result=null;
        result = adminCountService.getAdminCount(date,page, counts);
        return result;
    }
    @RequestMapping(value = "/toLogin",method = RequestMethod.GET)
    public String toLogin(){
        return "login";
    }


}
