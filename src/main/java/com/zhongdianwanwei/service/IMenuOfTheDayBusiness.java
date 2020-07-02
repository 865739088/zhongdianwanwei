package com.zhongdianwanwei.service;

public interface IMenuOfTheDayBusiness {

    /**
     *
     * @param adaptDateTimeStr 今日菜单生效时间的字符串
     * @param dishIds 今日菜单中菜品的Id列表
     * @return
     */
    Boolean saveDailyMenu(String adaptDateTimeStr, Integer[] dishIds);

}
