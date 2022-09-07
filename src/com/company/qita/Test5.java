package com.company.qita;

import org.testng.annotations.Test;

/**
 * 面试题13. 机器人的运动范围
 */
public class Test5 {
    public static void main(String[] args) {
        Test5 test5 = new Test5();
        System.out.println(test5.movingCount(2, 3, 1));
    }

    public int movingCount(int m, int n, int k) {
        int[][] visited = new int[m][n];
        dfs(m, n, k, 0, 0, visited);
        int count = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (visited[i][j] == -1) {
                    count++;
                }
            }
        }
        return count;
    }

    private void dfs(int m, int n, int k, int i, int j, int[][] visited) {
        if (i < 0 || j < 0 || i >= m || j >= n || (getSum(i) + getSum(j)) > k || visited[i][j] == -1) {
            return;
        }
        visited[i][j] = -1;
        dfs(m, n, k, i + 1, j, visited);
        dfs(m, n, k, i - 1, j, visited);
        dfs(m, n, k, i, j + 1, visited);
        dfs(m, n, k, i, j - 1, visited);
    }

    // 根据给定的坐标获得每一位加起来的和，比如横坐标：32 则获得3+2=5
    private int getSum(int num) {
        int count = 0;
        while (num > 0) {
            count += num % 10;
            num = num / 10;
        }
        return count;
    }
}
