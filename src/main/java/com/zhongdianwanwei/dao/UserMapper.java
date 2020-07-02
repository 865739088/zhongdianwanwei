package com.zhongdianwanwei.dao;

import com.zhongdianwanwei.model.AdminCount;
import com.zhongdianwanwei.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

//用户信息Mapper
@Mapper
public interface UserMapper {

    //查询全部用户信息
    List<User> getUsers();

    //根据用户账号查询用户信息
    User getUserByUserName(@Param("userName") String userName);

}
