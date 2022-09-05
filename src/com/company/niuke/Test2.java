package com.company.niuke;

/**
 * BM68 矩阵的最小路径和
 * https://www.nowcoder.com/practice/7d21b6be4c6b429bb92d219341c4f8bb?tpId=295&tqId=1009012&ru=/exam/oj&qru=/ta/format-top101/question-ranking&sourceUrl=%2Fexam%2Foj
 *
 */
public class Test2 {
    public static void main(String[] args) {


    }

    public int minPathSum (int[][] matrix) {
        // write code here
        dfs(matrix, 0, 0, 0);
        return res;
    }

    int tmp = Integer.MAX_VALUE;
    static int res = Integer.MAX_VALUE;

    private void dfs(int[][] matrix, int i, int j, int target) {
        if (i == matrix.length - 1 && j == matrix[0].length - 1) {
            res = Math.min(res, tmp);
        }
        if (isTrue(matrix, i, j)) {
            dfs(matrix, i + 1, j, target + matrix[i][j]);
        }
        if (isTrue(matrix, i, j)) {
            dfs(matrix, i, j - 1, target + matrix[i][j]);
        }
    }

    private boolean isTrue(int[][] matrix, int i, int j) {
        if (i < 0 || j < 0 || i > matrix.length - 1 || j > matrix[0].length - 1) {
            return false;
        }
        return true;
    }
}
