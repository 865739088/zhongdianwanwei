package com.zhongdianwanwei.model;

import java.util.Date;

/**
 * adminCount返回给前端Model
 * @author ：金鑫
 */
public class AdminCountVo {
    private Integer id;
    //用户编号
    private String userName;
    private String name;
    private Integer user_id;
    //是否加班
    private Integer if_overTime_type;
    private String if_overTime_lable;
    //加班选择类型（加班费、加班商品）
    private Integer overTime_type;
    private String overTime_lable;
    //创建时间
    private Date create_time;
    //组长是否同意
    private Integer if_agree_overTime;

    private String if_agree_overTime_label;

    public String getIf_agree_overTime_label() {
        return if_agree_overTime_label;
    }

    public void setIf_agree_overTime_label(String if_agree_overTime_label) {
        this.if_agree_overTime_label = if_agree_overTime_label;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getIf_overTime_lable() {
        return if_overTime_lable;
    }

    public void setIf_overTime_lable(String if_overTime_lable) {
        this.if_overTime_lable = if_overTime_lable;
    }

    public Integer getOverTime_type() {
        return overTime_type;
    }

    public void setOverTime_type(Integer overTime_type) {
        this.overTime_type = overTime_type;
    }

    public String getOverTime_lable() {
        return overTime_lable;
    }

    public void setOverTime_lable(String overTime_lable) {
        this.overTime_lable = overTime_lable;
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
