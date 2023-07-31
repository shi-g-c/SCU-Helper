package com.shigc.pojo;

import com.shigc.course.Course;
import com.shigc.scores.CalculateGpaAndAvg;
import com.shigc.scores.ScoresCourse;

import java.util.List;

public class Student {
    // 学号
    public static String studentId;
    // 密码
    public static String password;


    // 本学期成绩课程列表
    public static List<ScoresCourse> thisTermScoresCourses;
    // 本学期必修课程加权绩点
    public static double thisTermRequiredGpa;
    // 本学期必修课程平均分
    public static double thisTermRequiredAvg;
    // 本学期全部课程加权绩点
    public static double thisTermAllGpa;
    // 本学期全部课程平均分
    public static double thisTermAllAvg;
    // 本学期已修学分
    public static double thisTermCreditSum;
    // 本学期必修学分
    public static double thisTermRequiredCreditSum;


    // 全部必修课程列表
    public static List<ScoresCourse> allRequiredCourses;
    // 全部选修课程列表
    public static List<ScoresCourse> allElectiveCourses;
    // 全部任选课程列表
    public static List<ScoresCourse> allOptionalCourses;
    // 全部必修课程加权绩点
    public static double allRequiredGpa;
    // 全部必修课程加权平均分
    public static double allRequiredAvg;
    // 全部课程加权绩点
    public static double allGpa;
    // 全部课程加权平均分
    public static double allAvg;
    // 全部已修学分
    public static double allCreditSum;
    // 全部必修学分
    public static double allRequiredCreditSum;


    // 本学期课表
    public static List<Course> thisTermCourses;

    // 已选课程列表
    public static List<Course> selectedCourses;


    // Cookie
    public static String cookie;



    public static void showThisTermScores() {
        ScoresCourse.printScoresCourses(thisTermScoresCourses);
        // 计算加权绩点和平均分
        if(thisTermRequiredGpa == 0) {
            CalculateGpaAndAvg.calculateThisTermGpaAndAvg();
        }
        //保留两位小数
        System.out.println("本学期必修课程加权绩点：" + String.format("%.2f", thisTermRequiredGpa));
        System.out.println("本学期必修课程平均分：" + String.format("%.2f", thisTermRequiredAvg));
        System.out.println("本学期全部课程加权绩点：" + String.format("%.2f", thisTermAllGpa));
        System.out.println("本学期全部课程平均分：" + String.format("%.2f", thisTermAllAvg));
        System.out.println("本学期已修学分：" + String.format("%.2f", thisTermCreditSum));
        System.out.println("本学期必修学分：" + String.format("%.2f", thisTermRequiredCreditSum));
    }

    public static void showAllScores() {
        ScoresCourse.printScoresCourses(allRequiredCourses);
        ScoresCourse.printScoresCourses(allElectiveCourses);
        ScoresCourse.printScoresCourses(allOptionalCourses);
        // 计算加权绩点和平均分
        if(allRequiredGpa == 0) {
            CalculateGpaAndAvg.calculateAllTermGpaAndAvg();
        }
        //保留两位小数
        System.out.println("全部必修课程加权绩点：" + String.format("%.2f", allRequiredGpa));
        System.out.println("全部必修课程平均分：" + String.format("%.2f", allRequiredAvg));
        System.out.println("全部课程加权绩点：" + String.format("%.2f", allGpa));
        System.out.println("全部课程平均分：" + String.format("%.2f", allAvg));
        System.out.println("全部已修学分：" + String.format("%.2f", allCreditSum));
        System.out.println("全部必修学分：" + String.format("%.2f", allRequiredCreditSum));
    }

    public static void showThisTermCourses() {
        Course.printThisTermCourses(thisTermCourses);
    }

    public static void showSelectedCourses() {
        Course.printThisTermCourses(selectedCourses);
    }
}
