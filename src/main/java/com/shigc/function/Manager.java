package com.shigc.function;


import com.shigc.pojo.Student;

import java.io.IOException;
import java.util.Scanner;

/**
 * 流程管理类
 */
public class Manager {

    public void start() throws IOException {
        System.out.println("欢迎使用四川大学教务系统助手v1.0");
        System.out.println("请先登陆教务系统");
        Login login = new Login();
        login.loginSCU();
        System.out.println("登陆成功");
        int choice = 0;
        Scanner sc = new Scanner(System.in);
        while(choice != 5) {
            // 清空控制台
            System.out.print("\033[H\033[2J");
            System.out.println("请选择你要进行的操作：");
            System.out.println("1.成绩查询计算");
            System.out.println("2.课表查询(开发中)");
            System.out.println("3.自动一键评教(开发中)");
            System.out.println("4.自动抢课(开发中)");
            System.out.println("5.退出(开发中)");
            System.out.println("请输入你的选择：");
            choice = sc.nextInt();
            switch (choice) {
                case 1:
                    GetScores getScores = GetScores.getScores();
                    System.out.println("正在查询本学期成绩...");
                    Student.showThisTermScores();
                    System.out.println("正在查询所有学期成绩...");
                    Student.showAllScores();
                    System.out.println("按任意键返回...");
                    System.in.read();
                    break;
                default:
                    System.out.println("输入错误，请重新输入");
                    break;
            }
        }
    }
}
