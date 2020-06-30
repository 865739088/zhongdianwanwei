package com.zhongdianwanwei.model;


//用户实体
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
    private int UserType;
    //用户组
    private int GroupId;

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

    public int getUserType() {
        return UserType;
    }

    public void setUserType(int userType) {
        UserType = userType;
    }

    public int getGroupId() {
        return GroupId;
    }

    public void setGroupId(int groupId) {
        GroupId = groupId;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", UserType=" + UserType +
                ", GroupId=" + GroupId +
                '}';
        //sdfghjkddddaxSADFG
    }
}
