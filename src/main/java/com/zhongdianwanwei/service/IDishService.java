package com.zhongdianwanwei.service;


import com.zhongdianwanwei.model.Dish;

import java.util.List;

public interface IDishService {


    /**
     *
     * @param name
     * @return
     */
    List<Dish> getDishes(String name);
}
