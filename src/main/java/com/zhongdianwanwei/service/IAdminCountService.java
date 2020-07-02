package com.zhongdianwanwei.service;

import com.zhongdianwanwei.model.AdminCount;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.util.List;

public interface IAdminCountService {
    AdminCount  getAdminCountById(int id);
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
    List<AdminCount> getAdminCount(String queryDay,int page,int counts);

    /**
     * 插入数据
     * @param adminCount
     * @return
     */
    int insertAdminCount(AdminCount adminCount);

    /**
     * 修改数据
     * @param adminCount
     * @return
     */
    int updateAdminCountById(AdminCount adminCount);
}
