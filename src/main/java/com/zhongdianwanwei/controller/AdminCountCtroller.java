package com.zhongdianwanwei.controller;
/*
*  用于处理管理员统计请求controller
*  author：金鑫
 */
import com.zhongdianwanwei.model.AdminCount;
import com.zhongdianwanwei.model.User;
import com.zhongdianwanwei.service.IAdminCountService;
import com.zhongdianwanwei.service.IUserService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Api("管理员统计Api")
@Controller
public class AdminCountCtroller {

    @Resource(name = "admincountservice")
    private IAdminCountService adminCountService;
    @Autowired
    private IUserService userService;

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
     * 用户提交所选菜单
     * @param userId
     * @return
     */
    @RequestMapping(value = "/userSubmitChoose")
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

    /**
     * 组长查看当前组内人员申请加班情况
     * @param userId
     * @param groupId
     * @return
     */
    @RequestMapping(value = "/leaderErgodicRequests")
    @ResponseBody
    public  List<AdminCount> leaderErgodicRequests(@RequestParam int userId,@RequestParam int groupId){
        List<User> users = userService.getUserByGroupId(groupId);
        List<AdminCount> list= new ArrayList<>();
        for (User user:users){
            int id = user.getId();
            AdminCount adminCountById = adminCountService.getAdminCountById(id);
            list.add(adminCountById);
        }
        return list;
    }

}
