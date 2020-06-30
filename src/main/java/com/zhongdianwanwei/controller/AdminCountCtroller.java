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
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
@Api("管理员统计Api")
@Controller
public class AdminCountCtroller {

    @Resource(name = "admincountservice")
    private IAdminCountService adminCountService;
    @PostMapping("/admincount")
    public String getAdminCount(@RequestParam Date queryTime){
        try {
            List<AdminCount> result = adminCountService.getAdminCount(queryTime);
            return result.toString();
        }catch (Exception e){
            e.printStackTrace();
            return "系统内部错误";
        }
    }

}
