package com.shigc.course;

import com.gargoylesoftware.htmlunit.html.HtmlTableRow;
import com.shigc.scores.ScoresCourse;
import com.shigc.utils.PrintTable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * 课程类
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Course {
    /*
     * 课表上的课程类
     */
    String courseName; // 课程名
    String courseTeacher; // 课程老师
    String courseCredit; // 课程学分
    String courseType; // 课程类型
    String courseClassroom; // 课程教室
    String courseTime; // 课程时间

    public static List<Course>  getThisTermCoursesByTable(List<HtmlTableRow> trs) {
        List<Course> courses = new ArrayList<>();
        // 从第一行开始遍历
        for (int i = 1; i < trs.size(); i++) {
            //忽略掉有多个时间段的课程trs
            if(trs.get(i).getCells().size() < 5) {
                continue;
            }
            List<String> courseInfo = new ArrayList<>();
            for (int j = 0; j < trs.get(i).getCells().size(); j++) {
                courseInfo.add(trs.get(i).getCell(j).getTextContent());
            }
            Course course = new Course();
            course.setCourseName(courseInfo.get(1));
            course.setCourseTeacher(courseInfo.get(9));
            course.setCourseCredit(courseInfo.get(5));
            course.setCourseType(courseInfo.get(6));
            course.setCourseClassroom(courseInfo.get(15));
            course.setCourseTime(courseInfo.get(14));
            courses.add(course);
        }
        return courses;
    }

    /**
     * 打印课程表
     */
    public static void printThisTermCourses(List<Course> courses) {
        List<List<String>> table = new ArrayList<>();
        List<String> title = new ArrayList<>();
        title.add("名称");
        title.add("老师");
        title.add("学分");
        title.add("类型");
        title.add("教室");
        title.add("时间");
        table.add(title);
        for (Course course : courses) {
            List<String> row = new ArrayList<>();
            row.add(course.getCourseName());
            row.add(course.getCourseTeacher());
            row.add(course.getCourseCredit());
            row.add(course.getCourseType());
            row.add(course.getCourseClassroom());
            row.add(course.getCourseTime());
            table.add(row);
        }
        new PrintTable(table).printTable();
    }

    public static List<Course> getSelectedCoursesByTable(List<HtmlTableRow> trs) {
        List<Course> courses = new ArrayList<>();
        // 从第一行开始遍历
        for (int i = 1; i < trs.size(); i++) {
            //忽略掉有多个时间段的课程trs
            if(trs.get(i).getCells().size() < 5) {
                continue;
            }
            List<String> courseInfo = new ArrayList<>();
            for (int j = 0; j < trs.get(i).getCells().size(); j++) {
                courseInfo.add(trs.get(i).getCell(j).getTextContent());
            }
            Course course = new Course();
            course.setCourseName(courseInfo.get(1));
            course.setCourseTeacher(courseInfo.get(9));
            course.setCourseCredit(courseInfo.get(5));
            course.setCourseType(courseInfo.get(6));
            course.setCourseClassroom(courseInfo.get(13));
            course.setCourseTime(courseInfo.get(14));
            courses.add(course);
        }
        return courses;
    }
}
