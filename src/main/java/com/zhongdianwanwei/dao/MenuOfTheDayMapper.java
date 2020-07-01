package com.zhongdianwanwei.dao;

import com.zhongdianwanwei.model.MenuOfTheDay;
import org.apache.ibatis.annotations.Param;

import java.sql.Timestamp;
import java.util.List;

public interface MenuOfTheDayMapper {

    /**
     * 创建今日菜单
     * @param menu 今日菜单信息
     * @return 影响的表条数
     */
    Integer insertDailyMenu(MenuOfTheDay menu);

    /**
     * 根据MenuId查找一个菜单
     * @param id 需要查找的菜单Id
     * @return 查找出的Menu
     */
    MenuOfTheDay getMenuById(Integer id);

    /**
     * 根据菜单生效时间查找出那一天生效的菜单
     * @param adaptDate 菜单生效时间
     * @return 在这一天生效的菜单
     */
    MenuOfTheDay getMenuByApartDate(Timestamp adaptDate);

    /**
     * 按照分页查询菜单集合
     * @return
     */
    List<MenuOfTheDay> listMenus(@Param("offset") Integer offset,
                                 @Param("rowSize") Integer rowSize);

    /**
     * 统计表中菜单ID在参数IDs数组中的数量
     * @param IDs
     * @return
     */
    Integer countMenusByIDs(Integer[] IDs);

    /**
     * 根据MenuId删除一个菜单
     * @param menuID 需要删除的菜单Id
     * @return 影响的表条数
     */
    Integer deleteMenuByID(Integer menuID);

    /**
     * 根据ID数组删除对应菜单
     * @param menuIDs
     * @return
     */
    Integer deleteMenusByIDs(Integer[] menuIDs);

    /**
     * 根据MenuId修改一个菜单
     * @param menuOfTheDay 修改后的菜单信息
     * @return 影响的表条数
     */
    Integer updateMenu(MenuOfTheDay menuOfTheDay);


}
