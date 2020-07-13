package com.zhongdianwanwei.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.List;

public class MD5Util {


    public static void main(String[] args) {
        String password = "123456";
        String salt = StringUtil.randomStr(20);
        System.out.println(salt);
        String encrypt = encode(password, salt);
        System.out.println(encrypt);
    }


    public static String encode(String original, String security){
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] data = generateData(original, security);
//            byte[] data = (original + security).getBytes();
            byte[] encrypt = md.digest(data);
            return Base64.getEncoder().encodeToString(encrypt);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static byte[] generateData(String original, String security){
        List<Byte> dataList = StringUtil.convert2ByteList(original);
        dataList.addAll(StringUtil.convert2ByteList(security));
        for(int i=0; i<dataList.size(); i++){
            for(int j=dataList.size()-1; j>i; j--){
                Byte b = dataList.get(i);
                dataList.set(i, dataList.get(j));
                dataList.set(j, b);
            }
        }
        byte[] data = new byte[dataList.size()];
        for(int i=0; i<data.length; i++){
            data[i] = dataList.get(i).byteValue();
        }
        return data;
    }
}
