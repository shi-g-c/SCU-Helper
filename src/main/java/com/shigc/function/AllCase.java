package com.shigc.function;

import com.shigc.pojo.Student;

import java.io.IOException;

public class AllCase {
    public static void case1ShowScore() throws IOException {
        GetScores.getScores();
        System.out.println("正在打印本学期成绩单...");
        Student.showThisTermScores();
        System.out.println("正在打印所有成绩单...");
        Student.showAllScores();
        System.out.println("按任意键返回...");
        System.in.read();
    }

    public static void case2ShowCourse() throws IOException {
        GetThisTermCourse.getCourse();
        System.out.println("注意如果课程有多个教学地点，只显示第一个!!!");
        System.out.println("正在打印本学期课表...");
        Student.showThisTermCourses();
        System.out.println("按任意键返回...");
        System.in.read();
    }
}
