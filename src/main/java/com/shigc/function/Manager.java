package com.shigc.function;

import com.shigc.utils.PrintMeau;

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
        int choice = 0;
        Scanner sc = new Scanner(System.in);
        while(choice != 5) {
            //打印菜单
            PrintMeau.printMeau();
            try {
                choice = sc.nextInt();
            } catch (Exception e) {
                System.out.println("输入错误，请重新输入");
                continue;
            }
            switch (choice) {
                case 1 -> AllCase.case1ShowScore();
                case 2 -> AllCase.case2ShowCourse();
                case 3 -> {
                    System.out.println("自动一键评教功能开发中...");
                    System.out.println("按任意键返回...");
                    System.in.read();
                }
                case 4 -> {
                    System.out.println("自动抢课功能开发中...");
                    System.out.println("按任意键返回...");
                    System.in.read();
                }
                case 5 -> System.out.println("正在退出...");
                default -> System.out.println("输入错误，请重新输入");
            }
        }
    }
}
