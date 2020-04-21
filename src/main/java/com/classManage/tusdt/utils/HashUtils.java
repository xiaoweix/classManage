package com.classManage.tusdt.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Description:
 * Author: xxw
 * Date: 2020-04-13
 * Time: 17:54
 */
public class HashUtils {

    /**
     * Hash 加密
     * 参数 encName 为SHA-1 或 SHA-256
     * 区别只是加密的长度不同
     */
    public static String hashEncrypt(String str, String encName){
        MessageDigest md = null;
        String strDes = null;
        byte[] bt = str.getBytes();
        try {
            md = MessageDigest.getInstance(encName);
            md.update(bt);
            strDes = bytesHex(md.digest()); // to HexString
        } catch (NoSuchAlgorithmException e) {

            return null;
        }
        return strDes;
    }
    private static String bytesHex(byte[] bts) {
        StringBuffer strBuffer = new StringBuffer("");
        String tmp;
        for (int i = 0; i < bts.length; i++) {
            tmp = (Integer.toHexString(bts[i] & 0xFF));
            if (tmp.length() == 1) {
                strBuffer.append("0");
            }
            strBuffer.append(tmp);
        }
        return strBuffer.toString();
    }



    public static void main(String[] args) {

        String password1 = "xxw123";
        String password2 = "xxw124";
        HashUtils hashUtils = new HashUtils();
        System.out.println(hashUtils.hashEncrypt(password1,"SHA-256"));
        System.out.println(hashUtils.hashEncrypt(password2,"SHA-256"));

    }
}
