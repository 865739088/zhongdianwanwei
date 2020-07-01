package com.zhongdianwanwei.dao;

import com.zhongdianwanwei.model.MenuOfTheDay;

public interface MenuOfTheDayMapper {

    /**
     * 创建今日菜单
     * @param menu 今日菜单信息
     * @return 影响的表条数
     */
    Integer insertDailyMenu(MenuOfTheDay menu);

    /**
     * 根据MenuId删除一个菜单
     * @param menuId 需要删除的菜单Id
     * @return 影响的表条数
     */
    Integer deleteMenu(Integer menuId);

    /**
     * 根据MenuId查找一个菜单
     * @param menuId 需要查找的菜单Id
     * @return 查找出的Menu
     */
    MenuOfTheDayMapper getMenu(Integer menuId);

    /**
     * 根据MenuId修改一个菜单
     * @param m 修改后的菜单信息
     * @return 影响的表条数
     */
    Integer updateMenu(MenuOfTheDayMapper m);

}
