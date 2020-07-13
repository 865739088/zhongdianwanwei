package com.zhongdianwanwei.controller;

import com.alibaba.fastjson.JSONObject;
import com.zhongdianwanwei.model.Menu;
import com.zhongdianwanwei.service.IManageService;
import com.zhongdianwanwei.util.ResponseUtil;
import com.zhongdianwanwei.util.ServletUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class ManageController {


    @Autowired
    private IManageService manageService;


    @ResponseBody
    @RequestMapping("/getmenus")
    public void getMenus(){

        HttpServletRequest request=ServletUtil.getRequest();
        HttpSession session=request.getSession();
        int userId= (int) session.getAttribute("userId");

        List<Menu> list=manageService.getMenus(userId);
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("menus",list);
        ResponseUtil.out(ServletUtil.getResponse(),jsonObject);



    }
}
