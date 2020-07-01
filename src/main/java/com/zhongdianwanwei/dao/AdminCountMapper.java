package com.zhongdianwanwei.dao;

import com.zhongdianwanwei.model.AdminCount;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
@Mapper
public interface AdminCountMapper {

    AdminCount  getAdminCountById(@Param("id") int id);

    /**
     * 默认统计今日数据，分页展示
     */
    List<AdminCount> getTodayAdminCount(@Param("page") int page, @Param("counts")int counts);

    /**
     * 根据日期查询某一天数据，分页展示
     */
    List<AdminCount> getAdminCount(@Param("queryDay")String queryDay,@Param("page") int page,@Param("counts") int counts);
    /**
     * 插入数据
     */
    int insertAdminCount(@Param("adminCount") AdminCount adminCount);

    /**
     * 修改数据
     * @param adminCount
     * @return
     */
    int updateAdminCountById(@Param("adminCount")AdminCount adminCount);

}
