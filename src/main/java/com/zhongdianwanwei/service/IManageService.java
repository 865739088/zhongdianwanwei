package com.zhongdianwanwei.service;

import com.zhongdianwanwei.model.Menu;

import java.util.List;

public interface IManageService {

    List<Menu> getMenus(int userId);
}
