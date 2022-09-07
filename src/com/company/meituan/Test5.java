package com.company.meituan;

import java.util.Scanner;

/**
 * 20220903 美团笔试
 * 第一题：
 * 题目描述
 * 乒乓球，被称为中国的“国球”，是一种世界流行的球类体育项目。一局比赛的获胜规则如下：
 * <p>
 * 当一方赢得至少11分，并且超过对方2分及以上时，获得该局的胜利。
 * <p>
 * 按照上述规则，小美和小团正在进行一局比赛，当前比赛尚未结束，此时小美的得分为a，小团的得分为b。小美想知道，在
 * <p>
 * 最理想的情况下，她至少还要得多少分才可以赢下这场比赛。
 * <p>
 * 输入描述
 * <p>
 * 输入两个整数a、b。a表示当前小美获得的分数，b表示小团的分数。0≤ a,b≤ 99.保证输入的比分合法，并且在该比分下比赛尚未结束。
 */
public class Test5 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int a = sc.nextInt();
        int b = sc.nextInt();
        System.out.println(getNum(a, b));
    }

    private static int getNum(int a, int b) {
        // a--小美
        // b--小团
        int res = 0;
        while (true) {
            if ((a + res >= b + 2) && (a >=11 || b >= 11)) {
                return res;
            } else {
                res++;
            }
        }

    }
}
