package com.company.bishi;

import java.util.Scanner;

/** 0831茄子科技笔试
 * 题目链接：https://drive.google.com/drive/folders/1hVW_dIC-a5pxf-IbAfZ2RXpTgIyldlgw
 * 说明： 这个方法一使用的是DFS 真实环境下是超时的
 */

public class Test1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        int[][] matrix = new int[n][m];
        for (int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                matrix[i][j] = scanner.nextInt();
            }
        }
        dfs(matrix, 0, 0, 0);
        System.out.println(res);
    }

    static int res = Integer.MAX_VALUE;

    private static void dfs(int[][] matrix, int i, int j, int sum) {
        if (i == matrix.length - 1 && j == matrix[0].length - 1) {
            res = Math.min(res, sum);
        }
        if (isValid(matrix, i + 1, j)) {
            dfs(matrix, i + 1, j, sum + matrix[i][j]);
        }
        if (isValid(matrix, i, j + 1)) {
            dfs(matrix, i, j + 1, sum + matrix[i][j]);
        }
    }

    private static boolean isValid(int[][] matrix, int i, int j) {
        return (i >= 0 && i < matrix.length) && (j >= 0 && j < matrix[0].length);
    }
}
