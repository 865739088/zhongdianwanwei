package com.zhongdianwanwei.service;

import com.zhongdianwanwei.model.AdminCount;

import java.util.Date;
import java.util.List;

public interface IAdminCountService {
    List<AdminCount> getAdminCount(Date querydate);
}
