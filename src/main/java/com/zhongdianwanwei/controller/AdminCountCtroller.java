
package com.zhongdianwanwei.controller;

/*
*  用于处理管理员统计请求controller
*  author：金鑫
 */

import com.alibaba.fastjson.JSONObject;
import com.zhongdianwanwei.dao.DishesMapper;
import com.zhongdianwanwei.dao.UserMapper;
import com.zhongdianwanwei.model.*;
import com.zhongdianwanwei.service.IAdminCountService;
import com.zhongdianwanwei.service.IUserService;
import com.zhongdianwanwei.util.ResponseUtil;
import com.zhongdianwanwei.util.ServletUtil;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private DishesMapper dishesMapper;

/**
     * 查询今日数据
     * @param page
     * @return
     */

    @RequestMapping(value = "/getTodayAdminCount",method = RequestMethod.GET)
    @ResponseBody

    public  List<AdminCountVo> getTodayAdminCount(@RequestParam int page){
        List<AdminCountVo> resultVo = new ArrayList<>();
        List<AdminCount> results=null;
        results = adminCountService.getTodayAdminCount(page, 5);
        for (AdminCount result:results){
            AdminCountVo adminCountVo = new AdminCountVo();
            User user = userMapper.getUserById(result.getUser_id());
            if(user!=null) {
                adminCountVo.setId(user.getId());
                adminCountVo.setUserName(user.getUsername());
                adminCountVo.setName(user.getName());
                adminCountVo.setIf_agree_overTime(result.getIf_agree_overTime());
                adminCountVo.setOverTime_type(result.getOverTime_type());
                adminCountVo.setUser_id(result.getUser_id());
                adminCountVo.setIf_overTime_type(result.getIf_overTime_type());
                adminCountVo.setCreate_time(result.getCreate_time());
                adminCountVo.setIf_overTime_lable(result.getIf_overTime_type() == 0 ? "不加班" : "加班");
                adminCountVo.setIf_agree_overTime_label(result.getIf_agree_overTime() == 0?"未同意":"已同意");
                if (adminCountVo.getOverTime_type() == 1) {
                    Dish dishes = dishesMapper.getDishes(adminCountVo.getOverTime_type());
                    adminCountVo.setOverTime_lable(dishes.getName());
                } else
                    adminCountVo.setOverTime_lable("未加班");
                resultVo.add(adminCountVo);
            }
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("todayList",resultVo);
        ResponseUtil.out(ServletUtil.getResponse(),jsonObject);
        return resultVo;
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
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("dayList",result);
        ResponseUtil.out(ServletUtil.getResponse(),jsonObject);
        return result;
    }

    @ResponseBody
    @RequestMapping(value = "/getAdminCountById",method = RequestMethod.GET)
    public  void getAdminCount(){
        HttpServletRequest request=ServletUtil.getRequest();
        HttpSession session = request.getSession();
        String userName = (String)session.getAttribute("userName");
        User user = userService.getUserByUserName(userName);
        List<AdminCount> list = adminCountService.getAdminCountById(user.getId());
        List<AdminCountVo> adminCountVoList = new ArrayList<>();
        for (AdminCount adminCount1 :list){
            AdminCountVo adminCountVo = new AdminCountVo();
            adminCountVo.setUser_id(adminCount1.getUser_id());
            adminCountVo.setName(user.getName());
            adminCountVo.setUserName(user.getUsername());
            adminCountVo.setCreate_time(adminCount1.getCreate_time());
            adminCountVo.setIf_agree_overTime_label(adminCount1.getIf_agree_overTime()==0?"未同意":"已同意");
            adminCountVoList.add(adminCountVo);
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("adminLists",adminCountVoList);
        ResponseUtil.out(ServletUtil.getResponse(),jsonObject);

    }
    @RequestMapping(value = "/toLogin",method = RequestMethod.GET)
    public String toLogin(){

        return "login.html";
    }

/**
     * 用户提交加班申请
     */

    @RequestMapping(value = "/SubmitOverTime",method = RequestMethod.GET)
    @ResponseBody
    public void userSubmitOverTime(@RequestParam int choose){
        HttpServletRequest request=ServletUtil.getRequest();
        HttpSession session = request.getSession();
        int userId = (int)session.getAttribute("userId");
//        String userName = (String)session.getAttribute("userName");
//        User user = userService.getUserByUserName(userName);
        AdminCount adminCount = new AdminCount();
        adminCount.setId(0);
        adminCount.setUser_id(userId);
        adminCount.setCreate_time(new Date());
        adminCount.setIf_overTime_type(1);
        adminCount.setIf_agree_overTime(0);
        adminCount.setOverTime_type(choose);
        String s = null;
        int i = adminCountService.insertAdminCount(adminCount);
        JSONObject jsonObject = new JSONObject();
        if (i>0){
            jsonObject.put("applyResult","申请成功");
             s= "申请成功";
        }else{
            jsonObject.put("applyResult","申请失败");
             s=  "申请失败";
        }
        ResponseUtil.out(ServletUtil.getResponse(),jsonObject);

    }
//    /**
//     * 用户提交所选菜单
//     * @param
//     * @return
//     */
//
//    @RequestMapping(value = "/userSubmitChoose",method = RequestMethod.GET)
//    @ResponseBody
//    public String userSubmitChoose(@RequestParam int choose){
//        AdminCount adminCount = new AdminCount();
//        HttpServletRequest request=ServletUtil.getRequest();
//        HttpSession session = request.getSession();
//        int userId = (int)session.getAttribute("userId");
//        adminCount.setUser_id(userId);
//        adminCount.setOverTime_type(choose);
//        int i = adminCountService.updateChooseById(adminCount);
//        String s = null;
//        JSONObject jsonObject = new JSONObject();
//        if (i>0){
//            jsonObject.put("userSubmit","提交成功");
//            s= "提交成功";
//        }else{
//            jsonObject.put("userSubmit","提交失败");
//            s=  "提交失败";
//        }
//        ResponseUtil.out(ServletUtil.getResponse(),jsonObject);
//        return s;
//    }

/**
     * 组长同意加班申请
     */

    @RequestMapping(value = "/leaderAgreeOverTime",method = RequestMethod.GET)
    @ResponseBody
    public String leaderAgreeOverTime(@RequestParam int userId){
        AdminCount adminCount = new AdminCount();
        adminCount.setUser_id(userId);
        adminCount.setIf_agree_overTime(1);
        int i = adminCountService.updateAdminCountById(adminCount);
        String s = null;
        JSONObject jsonObject = new JSONObject();
        if (i>0){
            jsonObject.put("agreeResult","已同意申请");
            s= "已同意申请";
        }else{
            jsonObject.put("agreeResult","操作失败");
            s=  "操作失败";
        }
        ResponseUtil.out(ServletUtil.getResponse(),jsonObject);
        return s;
    }



/**
     * 组长查看当前组内人员申请加班情况
     * @param
     * @return
     */

    @RequestMapping(value = "/leaderErgodicRequests",method = RequestMethod.GET)
    @ResponseBody
    public  List<MenbersVo> leaderErgodicRequests(){
        HttpServletRequest request=ServletUtil.getRequest();
        HttpSession session=request.getSession();
        String userName = (String) session.getAttribute("userName");
        User userByUserName = userService.getUserByUserName(userName);
        List<MenbersVo> list= new ArrayList<>();
        if (userByUserName!=null) {
            if (userByUserName.getUser_type()==1) {
                List<User> users = userService.getUserByGroupId(userByUserName.getGroup_id());
                for (User user : users) {
                    int id = user.getId();
                    List<AdminCount> adminCountById = adminCountService.getAdminCountById(id);
                    MenbersVo menbersVo = new MenbersVo();
                    menbersVo.setGroupId(user.getGroup_id());
                    menbersVo.setName(user.getName());
                    menbersVo.setUser_id(user.getId());
                    menbersVo.setCondition(adminCountById.get(0).getIf_agree_overTime()==0?"未同意":"已同意");
                    menbersVo.setTranslation("组员提交加班申请");
                    list.add(menbersVo);
                }
            }
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("leaderList",list);
        ResponseUtil.out(ServletUtil.getResponse(),jsonObject);
        return list;
    }

}

