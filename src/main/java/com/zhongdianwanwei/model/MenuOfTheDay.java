package com.zhongdianwanwei.model;

import java.sql.Timestamp;
import java.util.List;

/**
 * demo
 * 每日菜单
 * @author : CaiYongcheng
 * @date : 2020-06-30 10:34
 **/
public class MenuOfTheDay {

    private Integer id;              //对应表主键
    private List<Dish> dishes;     //Java中的菜单列表
    private Timestamp createTime;    //菜单创建时间
    private Timestamp adaptTime;     //菜单生效时间
    private String dishesIds;       //对应表中的菜单列表

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<Dish> getDishes() {
        return dishes;
    }

    public void setDishes(List<Dish> dishes) {
        this.dishes = dishes;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public Timestamp getAdaptTime() {
        return adaptTime;
    }

    public void setAdaptTime(Timestamp adaptTime) {
        this.adaptTime = adaptTime;
    }

    public String getDishesIds() {
        return dishesIds;
    }

    public void setDishesIds(String dishesIds) {
        this.dishesIds = dishesIds;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("MenuOfTheDay{");
        sb.append("id=").append(id);
        sb.append(", dishes=").append(dishes);
        sb.append(", createTime=").append(createTime);
        sb.append(", adaptTime=").append(adaptTime);
        sb.append(", dishes_ids='").append(dishesIds).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
