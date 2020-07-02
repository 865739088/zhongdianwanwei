package com.zhongdianwanwei.dao;

import com.zhongdianwanwei.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

//用户信息Mapper
@Component
@Mapper
public interface UserMapper {

    //查询全部用户信息
    List<User> getUsers();

    //根据用户账号查询用户信息
    User getUserByUserName(@Param("userName") String userName);

    /**
     * 根据组号查询组员
     * @param groupId
     * @return
     */
    List<User> getUserByGroupId(@Param("groupId") int groupId);

    //根据用户账号查询用户信息
    User getUserById(@Param("id") int id);


}
