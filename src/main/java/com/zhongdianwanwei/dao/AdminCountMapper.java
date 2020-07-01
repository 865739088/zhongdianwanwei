package com.zhongdianwanwei.dao;

import com.zhongdianwanwei.model.AdminCount;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
@Mapper
public interface AdminCountMapper {

    /**
     * 默认统计今日数据，分页展示
     */
    @Select("select * from adminCount where to_days(create_time) = to_days(now()) limit #{page},#{counts}")
    List<AdminCount> getTodayAdminCount(int page,int counts);

    /**
     * 根据日期查询某一天数据，分页展示
     */
    @Select("select * from adminCount where ( datediff ( create_time , #{queryDay} ) = 0 ) limit #{page},#{counts}")
    List<AdminCount> getAdminCount(String queryDay,int page,int counts);
    /**
     * 插入数据
     */
    @Insert("insert into adminCount (id,user_id,if_overTime_type,overTime_type,create_time,if_agree_overTime) values (#{id},#{userId},#{isOverTime},#{overTimeType},#{createTime},#{isAgree}")
    int insertAdminCount(AdminCount adminCount);

}
