package com.zhongdianwanwei.dao;

import com.zhongdianwanwei.model.AdminCount;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AdminCountMapper {

    List<AdminCount> getAdminCount();
}
