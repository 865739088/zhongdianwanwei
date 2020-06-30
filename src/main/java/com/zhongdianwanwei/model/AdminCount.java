package com.zhongdianwanwei.model;

import java.util.Date;

//用于管理员做统计model
/*
*@author:金鑫
*/
public class AdminCount {
    //表内自增id
    private Integer id;
    //用户编号
    private Integer userId;
    //是否加班
    private Integer isOverTime;
    //加班选择类型（加班费、加班商品）
    private Integer overTimeType;
    //创建时间
    private Date createTime;
    //组长是否同意
    private Integer isAgree;

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

    public Integer getIsOverTime() {
        return isOverTime;
    }

    public void setIsOverTime(Integer isOverTime) {
        this.isOverTime = isOverTime;
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

    public Integer getIsAgree() {
        return isAgree;
    }

    public void setIsAgree(Integer isAgree) {
        this.isAgree = isAgree;
    }
}
