package com.zhongdianwanwei.model;

import java.util.Date;

//用于管理员做统计model
/*
*@author:金鑫
*/
public class AdminCount {
    //表内自增id
    private int id;
    //用户编号
    private int userId;
    //是否加班
    private int ifOverTimeType;
    //加班选择类型（加班费、加班商品）
    private int overTimeType;
    //创建时间
    private Date createTime;
    //组长是否同意
    private int ifAgreeOverTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getIfOverTimeType() {
        return ifOverTimeType;
    }

    public void setIfOverTimeType(Integer ifOverTimeType) {
        this.ifOverTimeType = ifOverTimeType;
    }

    public Integer getOverTimeType() {
        return overTimeType;
    }

    public void setOverTimeType(Integer overTimeType) {
        this.overTimeType = overTimeType;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getIfAgreeOverTime() {
        return ifAgreeOverTime;
    }

    public void setIfAgreeOverTime(Integer ifAgreeOverTime) {
        this.ifAgreeOverTime = ifAgreeOverTime;
    }
}
