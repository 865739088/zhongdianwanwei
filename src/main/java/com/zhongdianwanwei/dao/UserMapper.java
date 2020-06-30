package com.zhongdianwanwei.dao;

import com.zhongdianwanwei.model.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

//用户信息Mapper
@Mapper
public interface UserMapper {

    //查询全部用户信息
    List<User> getUsers();
}
