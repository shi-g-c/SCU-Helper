package com.shigc;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.LoggerFactory;

import java.awt.*;
import java.io.*;
import java.math.BigInteger;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

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
        String cookie = "JSESSIONID=****; selectionBar=125803539";
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

    @org.junit.Test
    public void testLogin() throws IOException {
        //创建httpClient请求
        CloseableHttpClient httpClient = HttpClients.createDefault();

        String username = "****";
        String password = "****";

        //创建验证码的get连接对象
        HttpGet httpGetCaptcha = new HttpGet("http://zhjw.scu.edu.cn/img/captcha.jpg");
        //发送请求
        CloseableHttpResponse responseCaptcha = httpClient.execute(httpGetCaptcha);

        //获取状态码
        //int statusCode = responseCaptcha.getStatusLine().getStatusCode();
        //System.out.println("获取验证码图片的连接的状态码为：" + statusCode);

        //保存验证码图片
        HttpEntity entityCaptcha = responseCaptcha.getEntity();
        byte[] imageBytes = EntityUtils.toByteArray(entityCaptcha);
        //将byte转成.jpg文件保存到桌面
        String filePath = "E:\\下载\\验证码.gif"; // 保存路径

        FileOutputStream fos = new FileOutputStream(filePath);
        fos.write(imageBytes);

        //输入验证码
        String captcha;

        Scanner scanner = new Scanner(System.in);
//        System.out.println("请输入验证码：");
//        captcha = scanner.nextLine();


        httpClient.close();
        responseCaptcha.close();
    }

    @org.junit.Test
    public void getRandomValue() throws IOException, NoSuchAlgorithmException {
        //创建httpClient请求
        CloseableHttpClient httpClient = HttpClients.createDefault();
        //创建验证码的get连接对象
        HttpGet httpGetLoginPage = new HttpGet("http://zhjw.scu.edu.cn/login");
        //发送请求
        CloseableHttpResponse responseLoginPage = httpClient.execute(httpGetLoginPage);
        //获取状态码
        int statusCode = responseLoginPage.getStatusLine().getStatusCode();
        System.out.println("获取登录的状态码为：" + statusCode);
        //获取页面
        HttpEntity entity = responseLoginPage.getEntity();
        String string = EntityUtils.toString(entity);
        //获取到tokenValue
        String tokenValue = string.substring(string.indexOf("tokenValue") + 36, string.indexOf("tokenValue") + 80);
        //获取到双引号之间的值
        tokenValue = tokenValue.substring(tokenValue.indexOf("\"") + 1, tokenValue.lastIndexOf("\""));

        //获取到设置账号和密码
        String username = "****";
        String password = "****";

        //创建验证码的get连接对象
        HttpGet httpGetCaptcha = new HttpGet("http://zhjw.scu.edu.cn/img/captcha.jpg");
        //发送请求
        CloseableHttpResponse responseCaptcha = httpClient.execute(httpGetCaptcha);

        //获取状态码
        statusCode = responseCaptcha.getStatusLine().getStatusCode();
        System.out.println("获取验证码图片的连接的状态码为：" + statusCode);

        //保存验证码图片
        HttpEntity entityCaptcha = responseCaptcha.getEntity();
        byte[] imageBytes = EntityUtils.toByteArray(entityCaptcha);
        //将byte转成.jpg文件保存到桌面
        String filePath = "E:\\下载\\验证码.gif"; // 保存路径

        FileOutputStream fos = new FileOutputStream(filePath);
        fos.write(imageBytes);

        //打开验证码图片
        Runtime.getRuntime().exec("cmd /c start " + filePath);

        //输入验证码
        String captcha;

        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入验证码：");
        captcha = scanner.nextLine();

        //对密码进行加密
        MessageDigest md5 = MessageDigest.getInstance("md5");
        byte[] digest = md5.digest(password.getBytes());
        password = new BigInteger(1, digest).toString(16);
        //创建登录的post请求
        String url = "http://zhjw.scu.edu.cn/j_spring_security_check"
                + "?tokenValue=" + tokenValue
                + "&j_username=" + username
                + "&j_password=" + password
                + "&j_captcha=" + captcha;
        System.out.println(url);
        HttpPost httpPostLogin = new HttpPost(url);
        //发送请求
        CloseableHttpResponse responseLogin = httpClient.execute(httpPostLogin);
        //获取状态码
        statusCode = responseLogin.getStatusLine().getStatusCode();
        System.out.println("登录的状态码为：" + statusCode);
        //获取页面
        entity = responseLogin.getEntity();
        string = EntityUtils.toString(entity);
        System.out.println(string);
        //获取cookie
        Header[] headers = responseLogin.getHeaders("Cookie");
        for (Header header : headers) {
            String value = header.getValue();
            String cookie = value.substring(0, value.indexOf(";"));
            System.out.println(cookie);
        }

    }
    @org.junit.Test
    public void testOpenBrowser() throws IOException {
        System.out.println("正在打开浏览器...");
        // 使用默认浏览器打开网页
        Desktop desktop = Desktop.getDesktop();
        if (Desktop.isDesktopSupported()) {
            try {
                desktop.browse(new URI("https://github.com/shi-g-c/SCU-Helper"));
            } catch (IOException | URISyntaxException e) {
                e.printStackTrace();
            }
        }
    }


    @org.junit.Test
    public void testMd5() {
        String test = "1234";
        try {
            MessageDigest md5 = MessageDigest.getInstance("md5");
            byte[] digest = md5.digest(test.getBytes());
            String md5Str = new BigInteger(1, digest).toString(16);
            System.out.println(md5Str);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        //81dc9bdb52d04dc20036dbd8313ed055
    }

}
