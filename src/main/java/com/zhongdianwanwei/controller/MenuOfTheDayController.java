package com.zhongdianwanwei.controller;

import com.zhongdianwanwei.service.IMenuOfTheDayBusiness;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.regex.Pattern;

/**
 * demo
 * 每日菜单的控制层
 *
 * @author : CaiYongcheng
 * @date : 2020-06-30 14:31
 **/
@RestController
public class MenuOfTheDayController {

    @Autowired
    private IMenuOfTheDayBusiness menuOfTheDayBusiness;

    private Boolean checkDateTime(String adaptDateTimeStr){
        if (adaptDateTimeStr == null
                || (adaptDateTimeStr = adaptDateTimeStr.trim()).length() != "yyyy-MM-dd hh:mm:dd".length()){
            return false;
        }
        if (!Pattern.matches("\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}",adaptDateTimeStr)) {
            return false;
        }
        // 2012-12-12 12:12:12

        int[] monthDay = {31,28,31,30,31,30,31,31,30,31,30,31};
        int year = Integer.parseInt(adaptDateTimeStr.substring(0,4));
        int month = Integer.parseInt(adaptDateTimeStr.substring(5,7));
        int day = Integer.parseInt(adaptDateTimeStr.substring(8,10));
        int hour = Integer.parseInt(adaptDateTimeStr.substring(11,13));
        int minute = Integer.parseInt(adaptDateTimeStr.substring(14,16));
        int second = Integer.parseInt(adaptDateTimeStr.substring(17,19));
        if (year < 2020){
            return false;
        }
        if (month > 12){
            return false;
        }
        if (year % 400 == 0 || (year % 4 == 0 && year % 100 != 0)){
            monthDay[1]=29;
        }
        if (day > monthDay[month-1]){
            return false;
        }
        if (hour > 24){
            return false;
        }
        if (second > 60 || minute > 60){
            return false;
        }
        return true;
    }

    @PostMapping("/createMenuOfTheDay")
    public Boolean createDailyMenu(String adaptDateTimeStr,
                                   @RequestParam("ids")Integer[] dishIds){

        if (!checkDateTime(adaptDateTimeStr)){
            return false;
        }
        if (dishIds == null || dishIds.length < 0){
            return false;
        }
        adaptDateTimeStr = adaptDateTimeStr.substring(0,10)+'T'+adaptDateTimeStr.substring(11,adaptDateTimeStr.length());
        return menuOfTheDayBusiness.saveDailyMenu(adaptDateTimeStr, dishIds);
    }

}
