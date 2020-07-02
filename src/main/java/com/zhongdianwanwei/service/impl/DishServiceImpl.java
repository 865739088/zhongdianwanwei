package com.zhongdianwanwei.service.impl;

import com.zhongdianwanwei.dao.DishesMapper;
import com.zhongdianwanwei.model.Dish;
import com.zhongdianwanwei.service.IDishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DishServiceImpl implements IDishService {

    @Autowired
    private DishesMapper dishesMapper;
    @Override
    public List<Dish> getDishes(String name) {

        return dishesMapper.getDishes(name);
    }
}
