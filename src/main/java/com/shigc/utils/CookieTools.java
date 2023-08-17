package com.shigc.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import netscape.javascript.JSObject;
import org.apache.commons.io.FileUtils;

import javax.swing.filechooser.FileSystemView;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

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

    /**
     * 读取 config.json 文件
     * @param item
     * @return
     */
    public static String readUserConfig(String item) {
        //获取当前绝对路径
        File directory = new File("");
        String path = directory.getAbsolutePath();    //得到的是C:/test/abc
        //user.config文件
        File file1 = new File(path + "/config.json");
        //将文件转换为字符串
        String json = null;
        try {
            json = FileUtils.readFileToString(file1, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
        JSONObject jsonObject = JSON.parseObject(json);
        return jsonObject.getString(item);
    }

    /**
     * 写入 config.json 文件
     * @param studentId
     * @param password
     */
    public static void setUserConfig(String studentId, String password) {
        //获取当前绝对路径
        File directory = new File("");
        String path = directory.getAbsolutePath();    //得到的是C:/test/abc
        //user.config文件
        File file1 = new File(path + "/config.json");
        //写入config.json文件
        String json = "{\"studentId\":\"" + studentId + "\",\"password\":\"" + password + "\"}";
        try {
            FileUtils.writeStringToFile(file1, json, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("已保存学号和密码至config.json, 下次可以自动登录");
    }
}
