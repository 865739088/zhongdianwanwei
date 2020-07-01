package com.zhongdianwanwei.controller;
/*
*  用于处理管理员统计请求controller
*  author：金鑫
 */
import com.zhongdianwanwei.model.AdminCount;
import com.zhongdianwanwei.service.IAdminCountService;
import io.swagger.annotations.Api;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
@Api("管理员统计Api")
@Controller
public class AdminCountCtroller {

    @Resource(name = "admincountservice")
    private IAdminCountService adminCountService;
    @RequestMapping(value = "/getTodayAdminCount",method = RequestMethod.GET)
    public String getTodayAdminCount(@RequestParam int page,@RequestParam int counts){
        try {
            List<AdminCount> result = adminCountService.getTodayAdminCount(page, counts);
            return result.toString();
        }catch (Exception e){
            e.printStackTrace();
            return "系统内部错误";
        }
    }
    @RequestMapping(value = "/getAdminCount",method = RequestMethod.GET)
    public String getAdminCount(@RequestParam Date date,@RequestParam int page,@RequestParam int counts){
        try {
            List<AdminCount> result = adminCountService.getAdminCount(date,page, counts);
            return result.toString();
        }catch (Exception e){
            e.printStackTrace();
            return "系统内部错误";
        }
    }
    @RequestMapping(value = "/toLogin",method = RequestMethod.GET)
    public String toLogin(){
        return "login";
    }


}
