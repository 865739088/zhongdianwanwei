package com.zhongdianwanwei.service;


import com.zhongdianwanwei.model.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

// 用户Service
public interface IUserService {

    //查询全部用户信息
    List<User> getUsers();

    //根据用户账号查询用户信息
    User getUserByUserName(String userName);

    /**
     * 根据组号查询组员
     * @param groupId
     * @return
     */
    List<User> getUserByGroupId(int groupId);
}
