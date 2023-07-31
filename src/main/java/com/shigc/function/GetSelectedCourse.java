package com.shigc.function;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlTable;
import com.gargoylesoftware.htmlunit.html.HtmlTableRow;
import com.shigc.course.Course;
import com.shigc.pojo.Student;
import com.shigc.utils.ClientTool;

import java.io.IOException;

import java.util.List;

public class GetSelectedCourse {

    WebClient webClient;

    static GetSelectedCourse getSelectedCourse;

    public static void getCourse() throws IOException {
        if(getSelectedCourse == null) {
            getSelectedCourse = new GetSelectedCourse();
        }
    }

    GetSelectedCourse() throws IOException {
        //设置webClient的相关参数
        webClient = ClientTool.getWebClient();

        System.out.println("正在查询选课结果...");

        getSelectedCourse();

        System.out.println("选课结果查询完毕!");
    }

    public void getSelectedCourse() throws IOException {
        //获取选课结果页面
        HtmlPage selectedCoursePage = webClient.getPage("http://zhjw.scu.edu.cn/student/courseSelect/courseSelectResult/index");

        //等待异步JS执行结束
        webClient.waitForBackgroundJavaScript(1000);

        //获取成绩表格
        HtmlTable selectedCourseTable = (HtmlTable) selectedCoursePage.getByXPath("/html/body/div[4]/div[2]/div[2]/div/div/div/div[3]/div[1]/div/table").get(0);

        //获取所有的trs
        List<HtmlTableRow> trs = selectedCourseTable.getRows();

        //选课结果存入Student类
        Student.selectedCourses = Course.getSelectedCoursesByTable(trs);

    }


}
