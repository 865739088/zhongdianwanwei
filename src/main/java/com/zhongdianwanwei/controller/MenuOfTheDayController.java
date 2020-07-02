package com.zhongdianwanwei.controller;

import com.zhongdianwanwei.model.MenuOfTheDay;
import com.zhongdianwanwei.service.IMenuOfTheDayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.regex.Pattern;

/**
 * demo
 * 每日菜单的控制层
 *
 * @author : CaiYongcheng
 * @date : 2020-06-30 14:31
 **/
@RestController
@RequestMapping(value = "/MenuOfTheDay")
public class MenuOfTheDayController {

    @Autowired
    private IMenuOfTheDayService menuOfTheDayService;

    /**
     * 创建一个菜单
     * @param adaptDateTimeStr 菜单生效的时间
     * @param dishIds 希望菜单包含的菜品ID列表
     * @param dishCounts 菜单包含的菜品数量列表
     * @return
     */
    @PostMapping
    public Boolean createDailyMenu(@RequestParam("adaptTime") String adaptDateTimeStr,
                                   @RequestParam("ids")Integer[] dishIds,
                                   @RequestParam("counts")Integer[] dishCounts){

        if (!checkDateTime(adaptDateTimeStr)){
            return false;
        }
        if (dishIds == null || dishIds.length < 0){
            return false;
        }
        if (dishCounts == null || dishCounts.length < 0){
            return false;
        }
        if (dishCounts.length != dishIds.length){
            return false;
        }
        adaptDateTimeStr = adaptDateTimeStr.substring(0,10)+'T'+adaptDateTimeStr.substring(11,adaptDateTimeStr.length());
        return menuOfTheDayService.saveDailyMenu(adaptDateTimeStr, dishIds, dishCounts);
    }

    /**
     * 根据ID查找菜单
     * @param id 菜单id
     * @return
     */
    @GetMapping(value = "/{id}")
    public MenuOfTheDay getMenu(@PathVariable(name = "id")Integer id){
        return menuOfTheDayService.getMenuById(id);
    }

    /**
     * 根据生效日期查找菜单
     * @param adaptDateTimeStr 菜单生效日期
     * @return
     */
    @GetMapping
    public MenuOfTheDay getMenu(@RequestParam("adaptTime") String adaptDateTimeStr){
        return menuOfTheDayService.getMenuByAdaptTime(adaptDateTimeStr);
    }

    /**
     * 返回分页查询的结果
     * @param pageIndex
     * @param pageSize
     * @return
     */
    @GetMapping(value = "/list/{pageSize}/{pageIndex}")
    public List<MenuOfTheDay> listMenu(@PathVariable(name = "pageIndex") Integer pageIndex,
                                       @PathVariable(name = "pageSize") Integer pageSize){
        if ((pageIndex < 1 || pageSize < 0)) {
            return null;
        }
        return menuOfTheDayService.listMenus(pageIndex, pageSize);
    }

    /**
     * 根据id删除一个菜单
     * @param id 需要删除的菜单主键
     * @return
     */
    @DeleteMapping(value = "/{id}")
    public Boolean removeMenu(@PathVariable(name = "id") Integer id){
        if (id == null) {
            return false;
        }
        return menuOfTheDayService.removeMenuByID(id);
    }

    /**
     * 根据ID数组批量删除菜单
     * @param ids
     * @return
     */
    @DeleteMapping
    public Boolean removeMenus(Integer[] ids){
        if (ids == null || ids.length < 1) {
            return false;
        }
        return menuOfTheDayService.removeMenusByIDs(ids);
    }

    /**
     * 修改一个菜单
     * @param id
     * @param adaptDateTimeStr 希望菜单生效的时间
     * @param dishIds 希望菜单包含的菜品id列表
     * @return
     */
    @PutMapping(value = "/{id}")
    public Boolean updateDailyMenu(@PathVariable(name = "id") Integer id,
                                   @RequestParam(value = "adaptTime") String adaptDateTimeStr,
                                   @RequestParam(value = "ids")Integer[] dishIds,
                                   @RequestParam(value = "counts")Integer[] dishCounts){
        System.out.println(id);
        if (id == null) {
            return false;
        }
        if (!checkDateTime(adaptDateTimeStr)){
            return false;
        }
        if (dishIds == null || dishIds.length < 1){
            return false;
        }
        if (dishCounts == null || dishCounts.length < 1){
            return false;
        }
        adaptDateTimeStr = adaptDateTimeStr.substring(0,10)+'T'+adaptDateTimeStr.substring(11,adaptDateTimeStr.length());
        System.out.println(adaptDateTimeStr);
        return menuOfTheDayService.updateDailyMenu(id, adaptDateTimeStr, dishIds, dishCounts);
    }

    /**
     * 检查日期字符串是否合法
     * @param adaptDateTimeStr
     * @return
     */
    private Boolean checkDateTime(String adaptDateTimeStr){
        if (adaptDateTimeStr == null
                || (adaptDateTimeStr = adaptDateTimeStr.trim()).length() != "yyyy-MM-dd hh:mm:dd".length()){
            return false;
        }
        if (!Pattern.matches("\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}",adaptDateTimeStr)) {
            return false;
        }
        // 2012-12-12 12:12:12

        int[] monthDay = {31,28,31,30,31,30,31,31,30,31,30,31};
        int year = Integer.parseInt(adaptDateTimeStr.substring(0,4));
        int month = Integer.parseInt(adaptDateTimeStr.substring(5,7));
        int day = Integer.parseInt(adaptDateTimeStr.substring(8,10));
        int hour = Integer.parseInt(adaptDateTimeStr.substring(11,13));
        int minute = Integer.parseInt(adaptDateTimeStr.substring(14,16));
        int second = Integer.parseInt(adaptDateTimeStr.substring(17,19));
        if (year < 2020){
            return false;
        }
        if (month > 12){
            return false;
        }
        if (year % 400 == 0 || (year % 4 == 0 && year % 100 != 0)){
            monthDay[1]=29;
        }
        if (day > monthDay[month-1]){
            return false;
        }
        if (hour > 24){
            return false;
        }
        if (second > 60 || minute > 60){
            return false;
        }
        return true;
    }

}
