package com.zhongdianwanwei.service;

import com.zhongdianwanwei.model.Dish;
import com.zhongdianwanwei.model.MenuOfTheDay;
import com.zhongdianwanwei.util.ResponseMessage;

import java.util.List;

public interface IMenuOfTheDayService {

    /**
     * 保存一个菜单
     * @param adaptDateTimeStr 菜单生效时间的字符串
     * @param dishIDs 菜单中菜品的Id列表
     * @param dishCounts 菜单中菜品对应的数量列表
     * @return
     */
    ResponseMessage saveDailyMenu(String adaptDateTimeStr, Integer[] dishIDs, Integer[] dishCounts);

    /**
     * 查找Id对应的菜单
     * @param id
     * @return
     */
    ResponseMessage getMenuById(Integer id);

    /**
     * 查找菜单生效时间对应的菜单
     * @param adaptDateTimeStr 菜单生效时间
     * @return
     */
    //ResponseMessage getMenuByAdaptTime(String adaptDateTimeStr);
    MenuOfTheDay getMenuByAdaptTime(String adaptDateTimeStr);

    /**
     * 按照分页菜单列表
     * @returnD
     */
    ResponseMessage listMenus(Integer pageIndex, Integer pageSize);

    /**
     * 根据ID删除对应的菜单
     * @param id
     * @return
     */
    ResponseMessage removeMenuByID(Integer id);

    /**
     * 根据ID数组批量删除对应的菜单
     * @param ids
     * @return
     */
    ResponseMessage removeMenusByIDs(Integer[] ids);

    /**
     * 根据id修改菜单
     * @param id 修改数据的主键标识
     * @param adaptDateTimeStr 菜单生效时间
     * @param dishIDs 菜品列表
     * @param dishCounts 菜品数量列表
     * @return
     */
    ResponseMessage updateDailyMenu(Integer id,
                            String adaptDateTimeStr,
                            Integer[] dishIDs, Integer[] dishCounts);

}
