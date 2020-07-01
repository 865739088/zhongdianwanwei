package com.zhongdianwanwei.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class StringUtil {
    private StringUtil(){}

    public static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
    public static final String DATE_FORMAT_1 = "yyyyMMdd";

    private static final char[] characters = {
            'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z','0','1','2','3','4','5','6','7','8','9'
    };

    public static List<Byte> convert2ByteList(String source){
        List<Byte> dataList = new ArrayList<>();
        byte[] data = source.getBytes();
        for(int i=0; i<data.length; i++){
            dataList.add(data[i]);
        }
        return dataList;
    }

    public static String randomStr(int length){
        StringBuilder sb = new StringBuilder();
        Random r = new Random();
        for(int i=0; i<length; i++){
            int index = r.nextInt(characters.length);
            if(index % 5 == 0){
                sb.append(Character.toUpperCase(characters[index]));
            } else {
                sb.append(characters[index]);
            }
        }
        return sb.toString();
    }

    public static String convertToStr(Date date){
        return convertToStr(date, DEFAULT_DATE_FORMAT);
    }

    public static String convertToStr(Date date, String format){
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(date);
    }

    public static Date convertToDate(String time){
        return convertToDate(time, DEFAULT_DATE_FORMAT);
    }

    public static Date convertToDate(String time, String format){
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        try {
            return sdf.parse(time);
        } catch (ParseException e) {
            return null;
        }
    }
}
