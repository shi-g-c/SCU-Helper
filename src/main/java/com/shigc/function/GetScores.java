package com.shigc.function;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlTable;
import com.gargoylesoftware.htmlunit.html.HtmlTableRow;
import com.shigc.common.Const;
import com.shigc.pojo.Student;
import com.shigc.scores.ScoresCourse;
import com.shigc.utils.ClientTool;

import java.io.IOException;
import java.util.List;

public class GetScores {
    WebClient webClient;

    static GetScores getScores;
    GetScores() throws IOException {
        //设置webClient的相关参数
        webClient = ClientTool.getWebClient();
        System.out.println("正在查询本学期成绩...");
        getThisTermScores();
        System.out.println("本学期成绩查询完毕!");
        System.out.println("正在查询所有学期成绩...");
        getAllTermScores();
        System.out.println("所有学期成绩查询完毕!");

    }

    public static void getScores() throws IOException {
        if(getScores == null) {
            getScores = new GetScores();
        }
    }
    /**
     * 获取本学期分数
     * @throws IOException
     */
    public void getThisTermScores() throws IOException {

        //获取所有成绩的页面
        HtmlPage allScores = webClient.getPage(Const.THIS_TERM_SCORES_URL);

        //等待异步JS执行结束
        webClient.waitForBackgroundJavaScript(1000);

        //System.out.println(allScores.asXml());

        //获取成绩表格
        HtmlTable scoresTable = (HtmlTable) allScores.getByXPath(Const.THIS_TERM_SCORES_TABLE_XPATH).get(0);

        //获取所有的tr
        List<HtmlTableRow> trs = scoresTable.getRows();

        //成绩存入Student类
        Student.thisTermScoresCourses = ScoresCourse.getThisTermScoresCoursesByTable(trs);
    }


    /**
     * 获取所有学期的分数
     */
    public void getAllTermScores() throws IOException {
        //获取所有成绩的页面
        HtmlPage allScores = webClient.getPage(Const.ALL_TERM_SCORES_URL);

        //等待异步JS执行结束
        webClient.waitForBackgroundJavaScript(1000);

        //获取必修成绩表格
        HtmlTable RequiredScoresTable = (HtmlTable) allScores.getByXPath(Const.ALL_TERM_REQUIRED_SCORES_TABLE_XPATH).get(0);
        //获取所有的tr
        List<HtmlTableRow> trs = RequiredScoresTable.getRows();
        Student.allRequiredCourses = ScoresCourse.getAllScoresCoursesByTable(trs);

        //获取选修成绩表格
        HtmlTable ElectiveScoresTable = (HtmlTable) allScores.getByXPath(Const.ALL_TERM_ELECTIVE_SCORES_TABLE_XPATH).get(0);
        //获取所有的tr
        List<HtmlTableRow> trs1 = ElectiveScoresTable.getRows();
        Student.allElectiveCourses = ScoresCourse.getAllScoresCoursesByTable(trs1);

        //获取任选成绩表格
        HtmlTable OptionalScoresTable = (HtmlTable) allScores.getByXPath(Const.ALL_TERM_OPTIONAL_SCORES_TABLE_XPATH).get(0);
        //获取所有的tr
        List<HtmlTableRow> trs2 = OptionalScoresTable.getRows();
        //成绩存入Student类
        Student.allOptionalCourses = ScoresCourse.getAllScoresCoursesByTable(trs2);
    }
}
