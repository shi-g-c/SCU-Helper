package com.shigc.scores;

import com.shigc.pojo.Student;

public class CalculateGpaAndAvg {
    public static void calculateThisTermGpaAndAvg() {
        //本学期必修学分总和
        double thisTermRequiredCreditSum = 0;
        //本学期必修加权成绩总和，即  得分 * 学分
        double thisTermRequiredScoreWeightSum = 0;
        //本学期必修加权绩点总和，即  绩点 * 学分
        double thisTermRequiredCreditWeightSum = 0;


        //本学期总学分
        double thisTermAllCreditSum = 0;
        //本学期全部加权成绩总和，即  得分 * 学分
        double thisTermAllScoreWeightSum = 0;
        //本学期全部加权绩点总和，即  绩点 * 学分
        double thisTermAllScoresWeightSum= 0;

        for (ScoresCourse scoresCourse : Student.thisTermScoresCourses) {
            //如果是必修课
            if(scoresCourse.getCourseType().equals("必修")) {
                thisTermRequiredCreditSum += scoresCourse.getCourseCredit();
                thisTermRequiredScoreWeightSum += scoresCourse.getCourseScore() * scoresCourse.getCourseCredit();
                thisTermRequiredCreditWeightSum += ScoresCourse.getGpa(scoresCourse.getCourseScore()) * scoresCourse.getCourseCredit();
            }
            thisTermAllCreditSum += scoresCourse.getCourseCredit();
            thisTermAllScoreWeightSum += scoresCourse.getCourseScore() * scoresCourse.getCourseCredit();
            thisTermAllScoresWeightSum += ScoresCourse.getGpa(scoresCourse.getCourseScore()) * scoresCourse.getCourseCredit();
        }

        Student.thisTermRequiredGpa = thisTermRequiredCreditWeightSum / thisTermRequiredCreditSum;
        Student.thisTermRequiredAvg = thisTermRequiredScoreWeightSum / thisTermRequiredCreditSum;
        Student.thisTermAllGpa = thisTermAllScoresWeightSum / thisTermAllCreditSum;
        Student.thisTermAllAvg = thisTermAllScoreWeightSum / thisTermAllCreditSum;
        Student.thisTermCreditSum = thisTermAllCreditSum;
        Student.thisTermRequiredCreditSum = thisTermRequiredCreditSum;
    }

    public static void calculateAllTermGpaAndAvg() {

        //全部必修学分总和
        double allRequiredCreditSum = 0;
        //全部必修加权成绩总和，即  得分 * 学分
        double allRequiredScoreWeightSum = 0;
        //全部必修加权绩点总和，即  绩点 * 学分
        double allRequiredCreditWeightSum = 0;

        //全部总学分
        double AllCreditSum = 0;
        //全部加权成绩总和，即  得分 * 学分
        double AllScoreWeightSum = 0;
        //全部加权绩点总和，即  绩点 * 学分
        double AllScoresWeightSum= 0;

        //计算全部必修课程加权绩点和平均分
        for (ScoresCourse scoresCourse : Student.allRequiredCourses) {
            allRequiredCreditSum += scoresCourse.getCourseCredit();
            allRequiredScoreWeightSum += scoresCourse.getCourseScore() * scoresCourse.getCourseCredit();
            allRequiredCreditWeightSum += ScoresCourse.getGpa(scoresCourse.getCourseScore()) * scoresCourse.getCourseCredit();

            AllCreditSum += scoresCourse.getCourseCredit();
            AllScoreWeightSum += scoresCourse.getCourseScore() * scoresCourse.getCourseCredit();
            AllScoresWeightSum += ScoresCourse.getGpa(scoresCourse.getCourseScore()) * scoresCourse.getCourseCredit();
        }
        //计算全部选修课程加权绩点和平均分
        for (ScoresCourse scoresCourse : Student.allElectiveCourses) {
            AllCreditSum += scoresCourse.getCourseCredit();
            AllScoreWeightSum += scoresCourse.getCourseScore() * scoresCourse.getCourseCredit();
            AllScoresWeightSum += ScoresCourse.getGpa(scoresCourse.getCourseScore()) * scoresCourse.getCourseCredit();
        }
        //计算全部任选课程加权绩点和平均分
        for (ScoresCourse scoresCourse : Student.allOptionalCourses) {
            AllCreditSum += scoresCourse.getCourseCredit();
            AllScoreWeightSum += scoresCourse.getCourseScore() * scoresCourse.getCourseCredit();
            AllScoresWeightSum += ScoresCourse.getGpa(scoresCourse.getCourseScore()) * scoresCourse.getCourseCredit();
        }

        Student.allRequiredGpa = allRequiredCreditWeightSum / allRequiredCreditSum;
        Student.allRequiredAvg = allRequiredScoreWeightSum / allRequiredCreditSum;
        Student.allGpa = AllScoresWeightSum / AllCreditSum;
        Student.allAvg = AllScoreWeightSum / AllCreditSum;
        Student.allCreditSum = AllCreditSum;
        Student.allRequiredCreditSum = allRequiredCreditSum;
    }
}
