package com.zhongdianwanwei.service.impl;

import com.zhongdianwanwei.service.IMenuOfTheDayService;
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
 * @author : CaiYongcheng
 * @date : 2020-06-30 13:39
 **/
@Service
public class MenuOfTheDayServiceImpl implements IMenuOfTheDayService {

    @Autowired
    private DishesMapper dishesMapper;

    @Autowired
    private MenuOfTheDayMapper menuOfTheDayMapper;



    @Override
    public Boolean saveDailyMenu(String adaptDateTimeStr, Integer[] dishIds) {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime adaptDateTime = LocalDateTime.parse(adaptDateTimeStr);
        MenuOfTheDay menuOfTheDay = new MenuOfTheDay();
        StringBuffer dishIdsStr = new StringBuffer();
        List<Dish> dishes = new ArrayList<>();
        if (!checkAdaptDateTimeStr(now, adaptDateTime, null)) {
            return false;
        }
        if (!listDishes(dishIds, dishIdsStr, dishes)) {
            return false;
        }
        menuOfTheDay.setCreateTime(Timestamp.valueOf(now));
        menuOfTheDay.setAdaptTime(Timestamp.valueOf(adaptDateTime));
        menuOfTheDay.setDishes(dishes);
        menuOfTheDay.setDishesIds(dishIdsStr.toString());
        return menuOfTheDayMapper.insertDailyMenu(menuOfTheDay) > 0;
    }

    @Override
    public MenuOfTheDay getMenuById(Integer id) {
        return menuOfTheDayMapper.getMenuById(id);
    }

    @Override
    public MenuOfTheDay getMenuByAdaptTime(String adaptDateTimeStr) {
        return menuOfTheDayMapper.getMenuByApartDate(Timestamp.valueOf(adaptDateTimeStr));
    }

    @Override
    public List<MenuOfTheDay> listMenus(Integer pageIndex, Integer pageSize) {
        List<MenuOfTheDay> menuOfTheDays = menuOfTheDayMapper.listMenus((pageIndex - 1) * pageSize, pageSize);
        for (MenuOfTheDay menuOfTheDay : menuOfTheDays) {
            System.out.println(menuOfTheDay);
        }
        return menuOfTheDays;
    }

    @Override
    public Boolean updateDailyMenu(Integer id, String adaptDateTimeStr, Integer[] dishIds) {
        MenuOfTheDay menuOfTheDay = menuOfTheDayMapper.getMenuById(id);
        LocalDateTime adaptDateTime = LocalDateTime.parse(adaptDateTimeStr);
        StringBuffer dishIdsStr = new StringBuffer();
        List<Dish> dishes = new ArrayList<>();
        // 修改数据不存在
        if (menuOfTheDay == null) {
            return false;
        }
        if (!checkAdaptDateTimeStr(menuOfTheDay.getCreateTime().toLocalDateTime(),
                adaptDateTime, menuOfTheDay.getId())) {
            return false;
        }
        if (!listDishes(dishIds, dishIdsStr, dishes)) {
            return false;
        }
        menuOfTheDay.setAdaptTime(Timestamp.valueOf(adaptDateTime));
        menuOfTheDay.setDishes(dishes);
        menuOfTheDay.setDishesIds(dishIdsStr.toString());
        return menuOfTheDayMapper.updateMenu(menuOfTheDay) > 0 ? true : false;
    }


    /**
     * 检查添加或者修改的菜单生效时间是否合法 1菜单生效时间必须在创建时间之后。2数据库中不存在与生效时间同日的菜单列表
     * @param createDateTime 菜单创建时间
     * @param adaptDateTime  菜单生效时间
     * @param id  对应id
     * @return
     */
    private Boolean checkAdaptDateTimeStr(LocalDateTime createDateTime, LocalDateTime adaptDateTime, Integer id){
        // 菜单生效时间在创建时间之前
        if (adaptDateTime.isBefore(createDateTime)) {
            return false;
        }
        // 该配置时间的菜单已经存在
        MenuOfTheDay menuByApartDate = menuOfTheDayMapper.getMenuByApartDate(Timestamp.valueOf(adaptDateTime));
        if (menuByApartDate != null && (id == null || id != menuByApartDate.getId())) {
            return false;
        }
        return true;
    }

    /**
     * 根据餐品id列表获取菜品列表
     * @param dishIds
     * @param dishIdsStr
     * @param dishes
     * @return
     */
    private Boolean listDishes(Integer[] dishIds, StringBuffer dishIdsStr, List<Dish> dishes){
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
        return true;
    }
}
