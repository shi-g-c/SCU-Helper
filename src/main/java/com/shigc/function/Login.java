package com.shigc.function;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.*;
import com.shigc.common.Const;
import com.shigc.pojo.Page;
import com.shigc.pojo.Student;
import com.shigc.utils.ClientTool;
import com.shigc.utils.CookieTools;

import javax.swing.filechooser.FileSystemView;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Login {
    //获取WebClient对象
    WebClient webClient = ClientTool.getWebClient();

    /**
     * 登陆四川大学教务系统
     */
    public void loginSCU() throws IOException {

        /*
          由于教务系统的部分资源的请求的url是动态的，所以需要模拟浏览器的行为，获取到动态的url，而不是
          直接模拟请求
         */


        //当前状态没有登录
        Page.status = Page.Status.NOT_LOGIN;

        Scanner sc = new Scanner(System.in);

        //获取当前绝对路径
        File directory = new File("");
        String path = directory.getAbsolutePath();    //得到的是C:/test/abc
        //检查是否有user.config文件
        File file1 = new File(path + "/config.json");
        if(file1.exists()){
            System.out.println("正在读取账号信息...");
            //如果有config.json文件，直接读取文件中的学号和密码
            Student.studentId = CookieTools.readUserConfig("studentId");
            Student.password = CookieTools.readUserConfig("password");
        } else {
            //输入学号和密码
            System.out.println("请输入学号：");
            Student.studentId = sc.next();
            System.out.println("请输入密码：");
            Student.password = sc.next();
            System.out.println("是否保存账号密码？(y/n)");
            String yn = sc.next();
            if(yn.equals("y")){
                //保存账号密码
                CookieTools.setUserConfig(Student.studentId, Student.password);
            }
        }

        while(true){
            try {
                //获取登陆页面
                Page.loginPage = webClient.getPage(Const.LOGIN_URL);
                //获取登陆表单元素
                HtmlForm form = (HtmlForm) Page.loginPage.getByXPath(Const.LOGIN_FROM_XPATH).get(0);

                //用户名input
                HtmlTextInput username = (HtmlTextInput) form.getElementsByAttribute("input", "name", "j_username").get(0);

                //密码input
                HtmlPasswordInput password = (HtmlPasswordInput) form.getElementsByAttribute("input", "name", "j_password").get(0);

                //设置input的value
                username.setValueAttribute(Student.studentId);
                password.setValueAttribute(Student.password);

                //获取验证码图片
                HtmlImage verify_img = (HtmlImage) Page.loginPage.getElementById("captchaImg");

                //当前用户桌面路径
                File desktopDir = FileSystemView.getFileSystemView().getHomeDirectory();
                String desktopPath = desktopDir.getAbsolutePath();

                //新建验证码图片在桌面
                File file = new File(desktopPath + "\\check" + ".gif");

                //保存验证码图片
                verify_img.saveAs(file);

                //用户输入验证码
                String code;
                System.out.println("请输入验证码，如果需要重新获取验证码，请输入 1 并回车：");
                code = sc.next();

                if (code.equals("1")) {
                    continue;
                }

                System.out.println("正在登陆, 请等待1-3秒...");

                //定位验证码输入框
                HtmlTextInput verify_code = form.getInputByName("j_captcha");
                //填入用户输入的验证码
                verify_code.setValueAttribute(code);

                //点击登陆，获取到登陆后的页面
                Page.homePage = ((DomElement) Page.loginPage.getByXPath(Const.LOGIN_SUBMIT_XPATH).get(0)).click();
                //等待js执行
                webClient.waitForBackgroundJavaScript(1000);
                //判断是否登陆成功
                // 获取到登陆后的页面的title
                String title = Page.homePage.getTitleText();
                if (title.equals("登录")) {
                    System.out.println("登陆失败，请重新登陆");
                    continue;
                }
                System.out.println("登陆成功");
                Thread.sleep(1000);
                //获取到cookie值
                Page.cookie = CookieTools.getCookie(webClient.getCookieManager().getCookie("JSESSIONID").toString());
                return;
                } catch(Exception e){
                    System.out.println("登陆失败，请重新登陆");
                }
            }
    }
}
