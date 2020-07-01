package com.zhongdianwanwei.model;


import java.sql.Date;

//用于管理员做统计model
/*
*@author:金鑫
*/
public class AdminCount {
    //表内自增id
    private int id;
    //用户编号
    private int user_id;
    //是否加班
    private int if_overTime_type;
    //加班选择类型（加班费、加班商品）
    private int overTime_type;
    //创建时间
    private Date create_time;
    //组长是否同意
    private int if_agree_overTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getIf_overTime_type() {
        return if_overTime_type;
    }

    public void setIf_overTime_type(int if_overTime_type) {
        this.if_overTime_type = if_overTime_type;
    }

    public int getOverTime_type() {
        return overTime_type;
    }

    public void setOverTime_type(int overTime_type) {
        this.overTime_type = overTime_type;
    }

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }

    public int getIf_agree_overTime() {
        return if_agree_overTime;
    }

    public void setIf_agree_overTime(int if_agree_overTime) {
        this.if_agree_overTime = if_agree_overTime;
    }
}
