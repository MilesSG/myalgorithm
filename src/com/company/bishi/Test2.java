package com.company.bishi;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 0831茄子科技笔试
 * 题目链接：https://drive.google.com/drive/folders/1hVW_dIC-a5pxf-IbAfZ2RXpTgIyldlgw
 * 说明： 这个方法一使用的是DFS 真实环境下是超时的
 */
public class Test2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        int[][] matrix = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                matrix[i][j] = scanner.nextInt();
            }
        }
        int[][] dp = new int[n][m];
        for (int[] dpRow : dp) {
            Arrays.fill(dpRow, Integer.MAX_VALUE);
        }
        dp[0][0] = matrix[0][0];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (i == 0 && j == 0) continue;
                if (i - 1 >= 0) {
                    dp[i][j] = Math.min(dp[i][j], dp[i - 1][j] + matrix[i][j]);
                }
                if (j - 1 >= 0) {
                    dp[i][j] = Math.min(dp[i][j], dp[i][j - 1] + matrix[i][j]);
                }
            }
        }
        System.out.println(dp[n - 1][m - 1]);
    }
}