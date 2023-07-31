package com.shigc;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class Test {
    static {
        LoggerContext loggerContext = (LoggerContext) LoggerFactory.getILoggerFactory();
        List<Logger> loggerList = loggerContext.getLoggerList();
        loggerList.forEach(logger -> logger.setLevel(Level.OFF));
    }
    @org.junit.Test
    public void main() throws IOException {
        URL url = new URL("http://zhjw.scu.edu.cn/student/teachingAssessment/evaluation/queryAll?pageNum=1&pageSize=30&flag=js");
        HttpURLConnection conn = (HttpURLConnection)url.openConnection();
        //设置url的请求方式为POST
        conn.setRequestMethod("POST");
        //设置Cookie
        String cookie = "JSESSIONID=aaal01lyxXXYu8OpHUqKy; selectionBar=125803539";
        conn.setRequestProperty("Cookie", cookie);
        conn.setRequestProperty("Referer", "http://zhjw.scu.edu.cn/student/teachingEvaluation/newEvaluation/index");
        //获取Response响应体里的内容
        conn.connect();
        //从conn中获取响应的内容
        InputStream is = conn.getInputStream();
        // 封装输入流is，并指定字符集
        BufferedReader br = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
        // 存放数据
        StringBuffer sbf = new StringBuffer();
        String temp = null;
        while ((temp = br.readLine()) != null) {
            sbf.append(temp);
        }
        System.out.println(sbf);
    }



    public static void clearnCmd() {
        try {//使用命令的过程可能会出现失败，需要捕获异常
            //   Process process = Runtime.getRuntime().exec("cls");
            new ProcessBuilder("cmd", "/c", "cls")
                    // 将 ProcessBuilder 对象的输出管道和 Java 的进程进行关联，这个函数的返回值也是一个
                    // ProcessBuilder
                    .inheritIO()
                    // 开始执行 ProcessBuilder 中的命令
                    .start()
                    // 等待 ProcessBuilder 中的清屏命令执行完毕
                    // 如果不等待则会出现清屏代码后面的输出被清掉的情况
                    .waitFor(); // 清屏命令
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
