package com.zhongdianwanwei.util;

import com.alibaba.fastjson.JSONObject;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class ResponseUtil {


    //处理数据     返回前端json格式数据
    public static void  out(HttpServletResponse response, JSONObject jsonObject){
        response.setContentType("text/html;charset=UTF-8");
        try {
            PrintWriter writer=response.getWriter();
            writer.print(jsonObject.toJSONString());
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
