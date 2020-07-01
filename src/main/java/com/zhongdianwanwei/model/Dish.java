package com.zhongdianwanwei.model;

import java.math.BigDecimal;

/**
 * 菜品
 *
 * @author : CaiYongcheng
 * @date : 2020-06-30 10:22
 **/
public class Dish {

    private Integer id;             //对应表主键
    private String name;            //菜品名称
    private BigDecimal price;       //菜品价格
    private String img;             //菜品图片路径
    private String mark;            //菜品描述

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
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

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Dishes{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append(", price=").append(price);
        sb.append(", img='").append(img).append('\'');
        sb.append(", mark='").append(mark).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
