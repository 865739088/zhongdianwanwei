package com.zhongdianwanwei.dao;

import com.zhongdianwanwei.model.AdminCount;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
@Component
@Mapper
public interface AdminCountMapper {

    List<AdminCount>  getAdminCountById(@Param("id") int id);

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
    int insertAdminCount(@Param("user_id")int user_id, @Param("if_overTime_type")int if_overTime_type, @Param("overTime_type")int overTime_type, @Param("create_time") Date create_time, @Param("if_agree_overTime")int if_agree_overTime);

    /**
     * 修改数据
     * @param
     * @return
     */
    int updateAdminCountById(@Param("user_id")int user_id,@Param("if_agree_overTime")int if_agree_overTime);

    int updateChooseById(@Param("user_id")int user_id,@Param("overTime_type")int overTime_type);

}
