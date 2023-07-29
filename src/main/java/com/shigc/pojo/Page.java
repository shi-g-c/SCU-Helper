package com.shigc.pojo;


import com.gargoylesoftware.htmlunit.html.HtmlPage;

/**
 * 网页类
 */
public class Page {
    //登陆页面
    public static HtmlPage loginPage;
    //主页
    public static HtmlPage homePage;
    //Cookie
    public static String cookie;

    //定义状态，有三种状态，未登录，登陆失败，登陆成功
    public enum Status{
        NOT_LOGIN,LOGIN_FAIL,LOGIN_SUCCESS
    }
    //当前状态
    public static Status status = Status.NOT_LOGIN;
}
