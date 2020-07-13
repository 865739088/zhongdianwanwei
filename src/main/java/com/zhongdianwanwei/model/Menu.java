package com.zhongdianwanwei.model;

public class Menu {

    private int id;//编号
    private String name;//菜单名称
    private String router;//路由

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

    public String getRouter() {
        return router;
    }

    public void setRouter(String router) {
        this.router = router;
    }
}
