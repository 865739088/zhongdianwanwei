package com.zhongdianwanwei.service.impl;

import com.zhongdianwanwei.dao.AdminCountMapper;
import com.zhongdianwanwei.model.AdminCount;
import com.zhongdianwanwei.service.IAdminCountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
@Component("admincountservice")
public class AdminCountServicerImpl implements IAdminCountService {
    @Autowired
    private AdminCountMapper adminCountMapper;

    @Override
    public List<AdminCount> getTodayAdminCount(int page, int counts) {
        return adminCountMapper.getTodayAdminCount(page, counts);
    }

    @Override
    public List<AdminCount> getAdminCount(Date queryDay, int page, int counts) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-DD hh-mm-ss");
        String format = simpleDateFormat.format(queryDay);
        return adminCountMapper.getAdminCount(format.substring(0,9),page,counts);
    }

    @Override
    public int insertAdminCount(AdminCount adminCount) {
        return adminCountMapper.insertAdminCount(adminCount);
    }
}
