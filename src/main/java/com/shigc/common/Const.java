package com.shigc.common;

public class Const {

    //教务系统登陆url
    public static String LOGIN_URL = "http://zhjw.scu.edu.cn/login";

    //教务系统登陆表单xpath
    public static String LOGIN_FROM_XPATH = "/html/body/div/div/form[1]";

    //教务系统登陆提交的表单xpath
    public static String LOGIN_SUBMIT_XPATH = "/html/body/div/div/form[1]/input[6]";

    //本学期成绩url
    public static String THIS_TERM_SCORES_URL = "http://zhjw.scu.edu.cn/student/integratedQuery/scoreQuery/allTermScores/index";

    //本学期成绩表格xpath
    public static String THIS_TERM_SCORES_TABLE_XPATH = "/html/body/div[4]/div[2]/div[2]/div/div/div[3]/div[1]/table";

    //所有学期成绩url
    public static String ALL_TERM_SCORES_URL = "http://zhjw.scu.edu.cn/student/integratedQuery/scoreQuery/coursePropertyScores/index";

    //所有学期必修成绩表格xpath
    public static String ALL_TERM_REQUIRED_SCORES_TABLE_XPATH = "/html/body/div[4]/div[2]/div[2]/div/div/div[2]/div/div/div/div/div/div/div[1]/div/div/table";

    //所有学期选修成绩表格xpath
    public static String ALL_TERM_ELECTIVE_SCORES_TABLE_XPATH = "/html/body/div[4]/div[2]/div[2]/div/div/div[2]/div/div/div/div/div/div/div[2]/div/div/table";

    //所有学期任选成绩表格的xpath
    public static String ALL_TERM_OPTIONAL_SCORES_TABLE_XPATH = "/html/body/div[4]/div[2]/div[2]/div/div/div[2]/div/div/div/div/div/div/div[3]/div/div/table";
}
