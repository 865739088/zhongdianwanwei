package com.zhongdianwanwei.service;

import com.zhongdianwanwei.model.MenuOfTheDay;

import java.util.List;

public interface IMenuOfTheDayService {

    /**
     * 保存一个菜单
     * @param adaptDateTimeStr 今日菜单生效时间的字符串
     * @param dishIds 今日菜单中菜品的Id列表
     * @return
     */
    Boolean saveDailyMenu(String adaptDateTimeStr, Integer[] dishIds);

    /**
     * 查找Id对应的菜单
     * @param id
     * @return
     */
    MenuOfTheDay getMenuById(Integer id);

    /**
     * 查找菜单生效时间对应的菜单
     * @param adaptDateTimeStr 菜单生效时间
     * @return
     */
    MenuOfTheDay getMenuByAdaptTime(String adaptDateTimeStr);

    /**
     * 获取所有菜单列表
     * @return
     */
    List<MenuOfTheDay> listMenus(Integer pageIndex, Integer pageSize);

    /**
     * 修改一个菜单
     * @param id 修改数据的主键标识
     * @param adaptDateTimeStr 菜单生效时间
     * @param dishIds 菜单列表
     * @return
     */
    Boolean updateDailyMenu(Integer id, String adaptDateTimeStr, Integer[] dishIds);

}
