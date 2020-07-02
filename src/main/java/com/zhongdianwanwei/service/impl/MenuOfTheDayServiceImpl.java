package com.zhongdianwanwei.service.impl;

import com.zhongdianwanwei.dao.DishesMapper;
import com.zhongdianwanwei.dao.MenuOfTheDayMapper;
import com.zhongdianwanwei.model.Dish;
import com.zhongdianwanwei.model.MenuOfTheDay;
import com.zhongdianwanwei.service.IMenuOfTheDayService;
import com.zhongdianwanwei.util.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
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
    public ResponseMessage saveDailyMenu(String adaptDateTimeStr, Integer[] dishIDs, Integer[] dishCounts) {
        adaptDateTimeStr=adaptDateTimeStr.substring(0,adaptDateTimeStr.length()-1);
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime adaptDateTime = LocalDateTime.parse(adaptDateTimeStr);
        MenuOfTheDay menuOfTheDay = new MenuOfTheDay();
        StringBuffer dishIdsStr = new StringBuffer();
        StringBuffer dishCountsStr = new StringBuffer();
        if (!checkAdaptDateTimeStr(now, adaptDateTime, null)) {
            return ResponseMessage.newErrorInstance("已存在配置过的今日菜单");
        }
        if (!listDishes(dishIDs, dishIdsStr, dishCounts, dishCountsStr)) {
            return ResponseMessage.newErrorInstance("所选菜品部分不存在，请刷新页面");
        }
        menuOfTheDay.setCreateTime(Timestamp.valueOf(now));
        menuOfTheDay.setAdaptTime(Timestamp.valueOf(adaptDateTime));
        menuOfTheDay.setDishesIds(dishIdsStr.toString());
        menuOfTheDay.setDishesCounts(dishCountsStr.toString());
        return menuOfTheDayMapper.insertDailyMenu(menuOfTheDay) > 0
                ? ResponseMessage.newOkInstance(null)
                : ResponseMessage.newErrorInstance("配置菜单失败");
    }

    @Override
    public ResponseMessage getMenuById(Integer id) {
        MenuOfTheDay menu = menuOfTheDayMapper.getMenuById(id);
        if(menu != null){
            menu.setDishes(getDishesOfMenu(menu));
        }
        return ResponseMessage.newOkInstance(menu);
    }

    @Override
    public ResponseMessage getMenuByAdaptTime(String adaptDateTimeStr) {
        adaptDateTimeStr=adaptDateTimeStr.substring(0,19);
        adaptDateTimeStr=adaptDateTimeStr.replace("T"," ");
        System.out.println(adaptDateTimeStr);
        MenuOfTheDay menu =
                menuOfTheDayMapper.getMenuByApartDate(Timestamp.valueOf(adaptDateTimeStr));
        if(menu != null){
            menu.setDishes(getDishesOfMenu(menu));
        }
        return ResponseMessage.newOkInstance(menu);
    }

    @Override
    public ResponseMessage listMenus(Integer pageIndex, Integer pageSize) {
        return ResponseMessage.newOkInstance(
                menuOfTheDayMapper.listMenus((pageIndex - 1) * pageSize, pageSize)
        );
    }

    @Override
    public ResponseMessage removeMenuByID(Integer id) {
        if (id == null) {
            return ResponseMessage.newErrorInstance("id不能为空");
        }
        return menuOfTheDayMapper.deleteMenuByID(id) > 0
                ? ResponseMessage.newOkInstance(null)
                : ResponseMessage.newErrorInstance("删除失败");
    }

    @Override
    public ResponseMessage removeMenusByIDs(Integer[] ids) {
        if (ids == null || ids.length < 1) {
            return ResponseMessage.newErrorInstance("id不能为空");
        }
        if (menuOfTheDayMapper.countMenusByIDs(ids) != ids.length) {
            return ResponseMessage.newErrorInstance("部分id不存在");
        }
        menuOfTheDayMapper.deleteMenusByIDs(ids);
        return ResponseMessage.newOkInstance(null);
    }

    @Override
    public ResponseMessage updateDailyMenu(Integer id, String adaptDateTimeStr, Integer[] dishIDs, Integer[] dishCounts) {
        MenuOfTheDay menuOfTheDay = menuOfTheDayMapper.getMenuById(id);
        LocalDateTime adaptDateTime = LocalDateTime.parse(adaptDateTimeStr);
        StringBuffer dishIdsStr = new StringBuffer();
        StringBuffer dishCountsStr = new StringBuffer();

        // 修改数据不存在
        if (menuOfTheDay == null) {
            return ResponseMessage.newErrorInstance("修改数据不存在");
        }
        // 检查生效时间是否在创建时间之后 并且数据库中不存在已配置时间的菜单
        if (!checkAdaptDateTimeStr(menuOfTheDay.getCreateTime().toLocalDateTime(),
                adaptDateTime, menuOfTheDay.getId())) {
            return ResponseMessage.newErrorInstance("生效日期格式错误或者已经存在该日期的配置菜单");
        }
        // 检查菜品是否都存在
        if (!listDishes(dishIDs, dishIdsStr, dishCounts, dishCountsStr)) {
            return ResponseMessage.newErrorInstance("有不存在的菜品");
        }
        menuOfTheDay.setAdaptTime(Timestamp.valueOf(adaptDateTime));
        menuOfTheDay.setDishesIds(dishIdsStr.toString());
        menuOfTheDay.setDishesCounts(dishCountsStr.toString());
        return menuOfTheDayMapper.updateMenu(menuOfTheDay) > 0
                ? ResponseMessage.newOkInstance(null)
                : ResponseMessage.newErrorInstance("修改失败");
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
     * 根据菜品id数组列表获取菜品列表以及数量列表
     * @param dishIDs 菜品id数组
     * @param dishIDsStr 需要生成的表中菜品id列表字符串
     * @param dishCounts  菜品数量数组
     * @param dishCountsStr 需要生成的表中菜品数量列表字符串
     * @return
     */
    private Boolean listDishes(Integer[] dishIDs, StringBuffer dishIDsStr,
                               Integer[] dishCounts, StringBuffer dishCountsStr){
        Integer counts = dishesMapper.countDishesByIDs(dishIDs);
        //表明存在查不到的菜品
        if (counts != dishIDs.length){
            return false;
        }
        for (int i=0; i<dishIDs.length; ++i){
            dishIDsStr.append(dishIDs[i]).append(',');
            dishCountsStr.append(dishCounts[i]).append(',');
        }
        dishIDsStr.deleteCharAt(dishIDsStr.length()-1);
        dishCountsStr.deleteCharAt(dishCountsStr.length()-1);
        return true;
    }

    /**
     * 根据menu中的id字符串列表与count字符串列表生成对应的菜品列表
     * @param menuOfTheDay
     * @return
     */
    private List<Dish> getDishesOfMenu(MenuOfTheDay menuOfTheDay){
        if (menuOfTheDay == null){
            return null;
        }
        String[] dishIDsStrArray = menuOfTheDay.getDishesIds().split(",");
        String[] dishCountsStrArray = menuOfTheDay.getDishesCounts().split(",");
        Integer[] dishIDs = strArrayToIntArray(dishIDsStrArray);
        Integer[] dishCoounts = strArrayToIntArray(dishCountsStrArray);
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i=0; i<dishIDs.length; ++i){
            map.put(dishIDs[i], dishCoounts[i]);
        }
        List<Dish> dishes = dishesMapper.getDishesByIDs(dishIDs);
        for (int i=0; i<dishes.size(); i++){
            Dish dish = dishes.get(i);
            dish.setCount(map.get(dish.getId()));
        }
        return dishes;
    }

    /**
     * 将字符串数组转化为数字数组
     * @param strs
     * @return
     */
    private Integer[] strArrayToIntArray(String[] strs){
        if (strs == null || strs.length < 1){
            return null;
        }
        Integer[] ints = new Integer[strs.length];
        for (int i=0; i<ints.length; ++i) {
            ints[i] = Integer.valueOf(strs[i]);
        }
        return ints;
    }
}
