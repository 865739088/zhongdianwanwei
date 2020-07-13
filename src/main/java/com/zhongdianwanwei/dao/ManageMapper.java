package com.zhongdianwanwei.dao;

import com.zhongdianwanwei.model.Menu;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ManageMapper {


    /**
     * 获取当前用户拥有的菜单
     * @param userId
     * @return
     */
    List<Menu> getMenus(int userId);
}
