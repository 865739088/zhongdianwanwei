package com.zhongdianwanwei.service;

import com.zhongdianwanwei.model.AdminCount;

import java.util.Date;
import java.util.List;

public interface IAdminCountService {
    List<AdminCount> getAllAdminCount();
    /**
     * 默认查询当日统计数据
     * @param page
     * @param counts
     * @return
     */
    List<AdminCount> getTodayAdminCount(int page,int counts);

    /**
     * 查询某一天数据
     * @param queryDay
     * @param page
     * @param counts
     * @return
     */
    List<AdminCount> getAdminCount(Date queryDay,int page,int counts);

    /**
     * 插入数据
     * @param adminCount
     * @return
     */
    int insertAdminCount(AdminCount adminCount);
}
