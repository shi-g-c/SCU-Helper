package com.shigc.utils;

import java.util.ArrayList;
import java.util.List;

public class PrintMenu {
    public static void printMainMenu() {
        // 清空控制台
        System.out.print("\033[H\033[2J");
        System.out.println("请选择你要进行的操作：");
        //将选修封装成二维数组，两属性位 id  课程名
        List<List<String>> choiceTable = new ArrayList<>();
        List<String> choice1 = new ArrayList<>();
        choice1.add("1");
        choice1.add("成绩查询计算");
        choiceTable.add(choice1);
        List<String> choice2 = new ArrayList<>();
        choice2.add("2");
        choice2.add("本学期课表查询");
        choiceTable.add(choice2);
        List<String> choice3 = new ArrayList<>();
        choice3.add("3");
        choice3.add("自动一键评教(开发中)");
        choiceTable.add(choice3);
        List<String> choice4 = new ArrayList<>();
        choice4.add("4");
        choice4.add("自动抢课(开发中)");
        choiceTable.add(choice4);
        List<String> choice5 = new ArrayList<>();
        choice5.add("5");
        choice5.add("联系开发者");
        choiceTable.add(choice5);
        List<String> choice6 = new ArrayList<>();
        choice6.add("6");
        choice6.add("退出");
        choiceTable.add(choice6);
        new PrintTable(choiceTable).printTable();
        System.out.println("请输入你的选择：");
    }

    public static void printChooseCourseMenu() {
        // 清空控制台
        System.out.print("\033[H\033[2J");
        System.out.println("请选择你要进行的操作：");
        List<List<String>> choiceTable = new ArrayList<>();
        List<String> choice1 = new ArrayList<>();
        choice1.add("1");
        choice1.add("查看选课结果");
        choiceTable.add(choice1);
        List<String> choice2 = new ArrayList<>();
        choice2.add("2");
        choice2.add("添加待抢课程");
        choiceTable.add(choice2);
        List<String> choice3 = new ArrayList<>();
        choice3.add("3");
        choice3.add("开始抢课");
        choiceTable.add(choice3);
        List<String> choice4 = new ArrayList<>();
        choice4.add("4");
        choice4.add("返回上页");
        choiceTable.add(choice4);
        new PrintTable(choiceTable).printTable();
        System.out.println("请输入你的选择：");
    }
}
