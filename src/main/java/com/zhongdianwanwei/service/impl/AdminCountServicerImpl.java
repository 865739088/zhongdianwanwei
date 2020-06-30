package com.zhongdianwanwei.service.impl;

import com.zhongdianwanwei.model.AdminCount;
import com.zhongdianwanwei.service.IAdminCountService;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
@Component("admincountservice")
public class AdminCountServicerImpl implements IAdminCountService {
    @Override
    public List<AdminCount> getAdminCount(Date querydate) {
        return null;
    }
}
