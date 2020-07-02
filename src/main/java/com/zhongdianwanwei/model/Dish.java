package com.zhongdianwanwei.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 * 菜品
 *
 * @author : CaiYongcheng
 * @date : 2020-06-30 10:22
 **/
public class Dish {

    private int id;             //对应表主键
    private String name;            //菜品名称
    private double price;       //菜品价格
    private String img;             //菜品图片路径
    private String mark;            //菜品描述
    private int count;          //指的是配置菜单中的菜品数量

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "Dish{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", img='" + img + '\'' +
                ", mark='" + mark + '\'' +
                ", count=" + count +
                '}';
    }
}
