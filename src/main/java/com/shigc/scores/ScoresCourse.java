package com.shigc.scores;

import com.gargoylesoftware.htmlunit.html.HtmlTableRow;
import com.shigc.utils.PrintTable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * 课程类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ScoresCourse {

    Integer id; // id
    String courseChineseName; // 课程中文名
    Double courseCredit; // 课程学分
    String courseType; // 课程类型
    Integer courseScore; // 课程成绩


    /**
     * 重写toString方法
     */
    @Override
    public String toString() {
        return "Course{" +
                "\nid=" + id +
                ", \ncourseChineseName=" + courseChineseName +
                ", \ncourseCredit=" + courseCredit +
                ", \ncourseType=" + courseType +
                ", \ncourseScore=" + courseScore +
                "\n}\n";
    }

    /**
     * 从本学期成绩table中获取课程列表
     * @param rows
     */
    public static List<ScoresCourse> getThisTermScoresCoursesByTable(List<HtmlTableRow> rows) {
        List<ScoresCourse> courses = new ArrayList<>();
        // 从第一行开始遍历
        for (int i = 1; i < rows.size(); i++) {
            List<String> courseInfo = new ArrayList<>();
            for (int j = 0; j < rows.get(i).getCells().size(); j++) {
                courseInfo.add(rows.get(i).getCell(j).getTextContent());
            }
            ScoresCourse scoresCourse = new ScoresCourse();
            scoresCourse.setId(Integer.parseInt(courseInfo.get(0)));
            scoresCourse.setCourseChineseName(courseInfo.get(3));
            scoresCourse.setCourseCredit(Double.parseDouble(courseInfo.get(5)));
            scoresCourse.setCourseType(courseInfo.get(7));
            scoresCourse.setCourseScore(Integer.parseInt(courseInfo.get(9)));
            courses.add(scoresCourse);
        }
        return courses;
    }

    /**
     * 从所有学期成绩table中获取课程列表
     * @param rows
     */
    public static List<ScoresCourse> getAllScoresCoursesByTable(List<HtmlTableRow> rows) {
        List<ScoresCourse> courses = new ArrayList<>();
        // 从第一行开始遍历
        for (int i = 1; i < rows.size(); i++) {
            List<String> courseInfo = new ArrayList<>();
            for (int j = 0; j < rows.get(i).getCells().size(); j++) {
                courseInfo.add(rows.get(i).getCell(j).getTextContent());
            }
            ScoresCourse scoresCourse = new ScoresCourse();
            scoresCourse.setId(i);
            scoresCourse.setCourseChineseName(courseInfo.get(1));
            scoresCourse.setCourseType(courseInfo.get(2));
            scoresCourse.setCourseCredit(Double.parseDouble(courseInfo.get(3)));
            // 由于编程实战课程没有数字成绩，所以给定一个默认值，对于最后结果影响忽略不计
            if(scoresCourse.getCourseChineseName().equals("编程实战")) {
                scoresCourse.setCourseScore(90);
            } else {
                scoresCourse.setCourseScore((int) Double.parseDouble(courseInfo.get(4)));
            }
            courses.add(scoresCourse);
        }
        return courses;
    }


    /**
     * 计算绩点
     * @param score
     */
    public static double getGpa(double score) {
        if(score >= 90) {
            return 4.0;
        } else if(score >= 85) {
            return 3.7;
        } else if(score >= 80) {
            return 3.3;
        } else if(score >= 76) {
            return 3.0;
        } else if(score >= 73) {
            return 2.7;
        } else if(score >= 70) {
            return 2.3;
        } else if(score >= 66) {
            return 2.0;
        } else if(score >= 63) {
            return 1.7;
        } else if(score >= 61) {
            return 1.3;
        } else if(score == 60) {
            return 1.0;
        } else {
            return 0;
        }
    }
    /**
     * 打印课程列表
     */
    public static void printScoresCourses(List<ScoresCourse> scoresCourses) {
        List<List<String>> table = new ArrayList<>();
        List<String> title = new ArrayList<>();
        title.add("id");
        title.add("类型");
        title.add("学分");
        title.add("成绩");
        title.add("课程名");
        table.add(title);
        for (ScoresCourse scoresCourse : scoresCourses) {
            List<String> row = new ArrayList<>();
            row.add(scoresCourse.getId().toString());
            row.add(scoresCourse.getCourseType());
            row.add(scoresCourse.getCourseCredit().toString());
            row.add(scoresCourse.getCourseScore().toString());
            row.add(scoresCourse.getCourseChineseName());
            table.add(row);
        }
        new PrintTable(table).printTable();
    }
}


