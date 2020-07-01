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

    /**
     * 查询今日数据
     * @param page
     * @return
     */
    @RequestMapping(value = "/getTodayAdminCount",method = RequestMethod.GET)
    @ResponseBody
    public  List<AdminCount> getTodayAdminCount(@RequestParam int page){
        List<AdminCount> result=null;
        result = adminCountService.getTodayAdminCount(page, 5);
        return result;
    }

    /**
     * 查询所有数据
     * @return
     */
    @RequestMapping(value = "/getAllAdminCount",method = RequestMethod.GET)
    @ResponseBody
    public  List<AdminCount> getAllAdminCount(){
        List<AdminCount> result=null;
        result = adminCountService.getAllAdminCount();
        return result;
    }

    /**
     * 查询某一天统计数据
     * @param date
     * @param page
     * @param counts
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getAdminCount",method = RequestMethod.GET)
    public  List<AdminCount> getAdminCount(@RequestParam Date date, @RequestParam int page, @RequestParam int counts){
        List<AdminCount> result=null;
        result = adminCountService.getAdminCount(date.toString(),page, counts);
        return result;
    }
    @RequestMapping(value = "/toLogin",method = RequestMethod.GET)
    public String toLogin(){
        return "login";
    }

    /**
     * 用户提交加班申请
     */
    @RequestMapping(value = "/SubmitOverTime")
    @ResponseBody
    public String userSubmitOverTime(@RequestParam int userId){
        AdminCount adminCount = new AdminCount();
        adminCount.setUser_id(userId);
        adminCount.setCreate_time(new Date());
        adminCount.setIf_overTime_type(1);
        adminCount.setIf_agree_overTime(0);
        adminCount.setOverTime_type(0);
        int i = adminCountService.insertAdminCount(adminCount);
        if (i>0){
            return "申请成功";
        }else
            return "申请失败";
    }
    /**
     * 组长同意加班申请
     */
    @RequestMapping(value = "/leaderAgreeOverTime")
    @ResponseBody
    public String leaderAgreeOverTime(@RequestParam int userId){
        AdminCount adminCount = new AdminCount();
        adminCount.setUser_id(userId);
        adminCount.setIf_agree_overTime(1);
        int i = adminCountService.updateAdminCountById(adminCount);
        if (i>0){
            return "已同意申请";
        }else
            return "操作失败";
    }

    /**
     * 用户提交所选加班类型
     * @param userId
     * @return
     */
    @RequestMapping(value = "/leaderAgreeOverTime")
    @ResponseBody
    public String userSubmitChoose(@RequestParam int userId,@RequestParam int choose){
        AdminCount adminCount = new AdminCount();
        adminCount.setUser_id(userId);
        adminCount.setOverTime_type(choose);
        int i = adminCountService.updateAdminCountById(adminCount);
        if (i>0){
            return "提交成功";
        }else
            return "提交失败";
    }
}
