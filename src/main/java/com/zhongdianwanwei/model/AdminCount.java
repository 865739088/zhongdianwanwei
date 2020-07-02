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
    private Integer user_id;
    //是否加班
    private Integer if_overTime_type;
    //加班选择类型（加班费、加班商品）
    private Integer overTime_type;
    //创建时间
    private Date create_time;
    //组长是否同意
    private Integer if_agree_overTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public Integer getIf_overTime_type() {
        return if_overTime_type;
    }

    public void setIf_overTime_type(Integer if_overTime_type) {
        this.if_overTime_type = if_overTime_type;
    }

    public Integer getOverTime_type() {
        return overTime_type;
    }

    public void setOverTime_type(Integer overTime_type) {
        this.overTime_type = overTime_type;
    }

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }

    public Integer getIf_agree_overTime() {
        return if_agree_overTime;
    }

    public void setIf_agree_overTime(Integer if_agree_overTime) {
        this.if_agree_overTime = if_agree_overTime;
    }
}
