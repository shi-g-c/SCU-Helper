package com.shigc.function;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlTable;
import com.gargoylesoftware.htmlunit.html.HtmlTableRow;
import com.shigc.common.Const;
import com.shigc.course.Course;
import com.shigc.pojo.Student;
import com.shigc.utils.ClientTool;

import java.io.IOException;
import java.util.List;

public class GetThisTermCourse {

    static WebClient webClient;

    private static GetThisTermCourse getThisTermCourse;

    public static void getCourse() throws IOException {
        if(getThisTermCourse == null) {
            getThisTermCourse = new GetThisTermCourse();
        }
    }

    GetThisTermCourse() throws IOException {
        //设置webClient的相关参数
        webClient = ClientTool.getWebClient();
        System.out.println("正在查询本学期课表...");
        getThisTermCourse();
        System.out.println("本学期课表查询完毕!");
    }


    public void getThisTermCourse() throws IOException {
        //获取所有课程的页面
        HtmlPage allCoures = webClient.getPage("http://zhjw.scu.edu.cn/student/courseSelect/thisSemesterCurriculum/index");

        //等待异步JS执行结束
        webClient.waitForBackgroundJavaScript(1000);

        //System.out.println(allCoures.asXml());

        //获取课程表格
        HtmlTable courseTable = (HtmlTable) allCoures.getByXPath("/html/body/div[4]/div[2]/div[2]/div/div/div/div[3]/div[1]/div/table").get(0);

        //获取所有的tr
        List<HtmlTableRow> trs = courseTable.getRows();

        //课程存入Student类
        Student.thisTermCourses = Course.getThisTermCoursesByTable(trs);
    }
}
