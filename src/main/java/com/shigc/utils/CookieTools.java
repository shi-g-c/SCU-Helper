package com.shigc.utils;

public class CookieTools {
    /**
     * 获取cookie
     * @param str
     * @return
     */
    public static String getCookie(String str) {
        // JSESSIONID=********;domain=zhjw.scu.edu.cn;path=/
        String[] strs = str.split(";");
        String[] strs2 = strs[0].split("=");
        return strs2[1];
    }
}
