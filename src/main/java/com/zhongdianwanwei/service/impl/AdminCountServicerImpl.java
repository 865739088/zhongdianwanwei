package com.zhongdianwanwei.service.impl;

import com.zhongdianwanwei.dao.AdminCountMapper;
import com.zhongdianwanwei.model.AdminCount;
import com.zhongdianwanwei.service.IAdminCountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.sql.Date;
import java.util.List;
@Component("admincountservice")
public class AdminCountServicerImpl implements IAdminCountService {
    @Autowired
    private AdminCountMapper adminCountMapper;

    @Override
    public List<AdminCount> getAllAdminCount() {
        return adminCountMapper.getAllAdminCount();
    }

    @Override
    public List<AdminCount> getTodayAdminCount(int page, int counts) {
        List<AdminCount> todayAdminCount = adminCountMapper.getTodayAdminCount(page, counts);
        return todayAdminCount;
    }

    @Override
    public List<AdminCount> getAdminCount(String queryDay, int page, int counts) {
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-DD hh-mm-ss");
//        String format = simpleDateFormat.format(queryDay);
        String date = queryDay.substring(0, 10);
        return adminCountMapper.getAdminCount(date,page,counts);
    }

    @Override
    public int insertAdminCount(AdminCount adminCount) {
        return adminCountMapper.insertAdminCount(adminCount);
    }

    @Override
    public int updateAdminCountById(AdminCount adminCount) {
        return adminCountMapper.updateAdminCountById(adminCount);
    }
}
