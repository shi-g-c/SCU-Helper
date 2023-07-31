package com.shigc.function;

import com.shigc.pojo.Student;
import com.shigc.utils.PrintMeau;

import java.io.IOException;
import java.util.Scanner;

public class AllCase {
    /**
     * 打印成绩单
     * @throws IOException
     */
    public static void case1ShowScore() throws IOException {
        GetScores.getScores();
        System.out.println("正在打印本学期成绩单...");
        Student.showThisTermScores();
        System.out.println("正在打印所有成绩单...");
        Student.showAllScores();
        System.out.println("按任意键返回...");
        System.in.read();
    }

    /**
     * 打印课表
     * @throws IOException
     */
    public static void case2ShowCourse() throws IOException {
        GetThisTermCourse.getCourse();
        System.out.println("注意如果课程有多个教学地点，只显示第一个!!!");
        System.out.println("正在打印本学期课表...");
        Student.showThisTermCourses();
        System.out.println("按任意键返回...");
        System.in.read();
    }

    /**
     * 选课
     */
    public static void case4ChooseCourse() throws IOException {
        PrintMeau.printChooseCourseMeau();
        Scanner sc = new Scanner(System.in);
        int choice = 0;
        while(choice != 4) {
            try {
                choice = sc.nextInt();
            } catch (Exception e) {
                System.out.println("输入错误，请重新输入");
                continue;
            }
            switch (choice) {
                case 1 -> {
                    GetSelectedCourse.getCourse();
                    System.out.println("正在打印选课结果...");
                    Student.showSelectedCourses();
                    System.out.println("按任意键返回...");
                    System.in.read();
                }
                case 2 -> {
                    //TODO 添加待选课程
                }
                case 3 -> {
                    // TODO 选课
                }
                case 4 -> System.out.println("正在返回上页...");
                default -> System.out.println("输入错误，请重新输入");
            }
            PrintMeau.printChooseCourseMeau();
        }
    }
}
