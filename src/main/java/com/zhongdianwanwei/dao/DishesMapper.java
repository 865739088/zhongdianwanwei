package com.zhongdianwanwei.dao;

import com.zhongdianwanwei.model.Dish;

import java.util.List;

public interface DishesMapper {

    /**
     * 根据id查找菜品信息
     * @param id
     * @return
     */
    Dish getDishes(Integer id);

    /**
     * 根据菜品名称查找菜品信息
     * @param name
     * @return
     */
    List<Dish> getDishes(String name);
    /**
     * 根据id列表查找菜品信息
     * @param IDs
     * @return
     */
    List<Dish> getDishesByIDs(Integer[] IDs);

    /**
     * 统计ID列表中的菜品有多少是存在表中的
     * @param IDs
     * @return
     */
    Integer countDishesByIDs(Integer[] IDs);

}
