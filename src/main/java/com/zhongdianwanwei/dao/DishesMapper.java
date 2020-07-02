package com.zhongdianwanwei.dao;

import com.zhongdianwanwei.model.Dish;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;
@Mapper
@Component
public interface DishesMapper {

    /**
     * 根据id查找菜品信息
     * @param id
     * @return
     */
    Dish getDishes(Integer id);

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
