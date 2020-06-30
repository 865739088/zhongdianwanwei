package com.zhongdianwanwei.service.impl;

import com.zhongdianwanwei.service.IUserService;
import com.zhongdianwanwei.dao.UserMapper;
import com.zhongdianwanwei.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

//用户服务实现
@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<User> getUsers() {
        return userMapper.getUsers();
    }
}
