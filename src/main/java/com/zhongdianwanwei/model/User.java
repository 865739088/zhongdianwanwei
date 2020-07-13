package com.zhongdianwanwei.model;


/**
 * 用户信息
 */
public class User {
    //编号
    private int id;
    //登录账号
    private String username;
    //登录密码
    private String password;
    //用户昵称
    private String name;
    //用户类型
    private int user_type;
    //用户组
    private int group_id;
    //随机盐
    private String salt;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getUser_type() {
        return user_type;
    }

    public void setUser_type(int user_type) {
        this.user_type = user_type;
    }

    public int getGroup_id() {
        return group_id;
    }

    public void setGroup_id(int group_id) {
        this.group_id = group_id;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", user_type=" + user_type +
                ", group_id=" + group_id +
                ", salt='" + salt + '\'' +
                '}';
    }
}
