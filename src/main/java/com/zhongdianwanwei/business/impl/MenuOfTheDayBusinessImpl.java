package com.zhongdianwanwei.business.impl;

import com.zhongdianwanwei.business.IMenuOfTheDayBusiness;
import com.zhongdianwanwei.dao.DishesMapper;
import com.zhongdianwanwei.dao.MenuOfTheDayMapper;
import com.zhongdianwanwei.model.Dish;
import com.zhongdianwanwei.model.MenuOfTheDay;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * demo
 * 每日菜单业务的实现类
 *
 * @author : CaiYongcheng
 * @date : 2020-06-30 13:39
 **/
@Service
public class MenuOfTheDayBusinessImpl implements IMenuOfTheDayBusiness {

    @Autowired
    private DishesMapper dishesMapper;

    @Autowired
    private MenuOfTheDayMapper menuOfTheDayMapper;

    @Override
    public Boolean saveDailyMenu(String adaptDateTimeStr, Integer[] dishIds) {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime adaptDateTime = LocalDateTime.parse(adaptDateTimeStr);
        MenuOfTheDay menuOfTheDay = new MenuOfTheDay();
        // 菜单生效时间不能在当前时间之前
        if (!now.isBefore(adaptDateTime)) {
            return false;
        }
        // 根据菜品id依次查找菜品
        StringBuffer dishIdsStr = new StringBuffer();
        List<Dish> dishes = new ArrayList<>();
        for (Integer dishId : dishIds) {
            Dish dish = dishesMapper.getDishes(dishId);
            // dish==null 表明查找失败
            if(dish == null){
                return false;
            }
            dishIdsStr.append(dishId).append(',');
            dishes.add(dish);
        }
        if(dishIdsStr.length()>0){
            dishIdsStr.deleteCharAt(dishIdsStr.length()-1);
        }
        menuOfTheDay.setCreateTime(Timestamp.valueOf(now));
        menuOfTheDay.setAdaptTime(Timestamp.valueOf(adaptDateTime));
        menuOfTheDay.setDishes(dishes);
        menuOfTheDay.setDishesIds(dishIdsStr.toString());
        System.out.println(menuOfTheDay.toString());
        return menuOfTheDayMapper.insertDailyMenu(menuOfTheDay) > 0;
    }
}
