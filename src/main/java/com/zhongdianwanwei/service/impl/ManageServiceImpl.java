package com.zhongdianwanwei.service.impl;

import com.zhongdianwanwei.dao.ManageMapper;
import com.zhongdianwanwei.model.Menu;
import com.zhongdianwanwei.service.IManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ManageServiceImpl implements IManageService {


    @Autowired
    private ManageMapper manageMapper;
    @Override
    public List<Menu> getMenus(int userId) {
        return manageMapper.getMenus(userId);
    }
}
