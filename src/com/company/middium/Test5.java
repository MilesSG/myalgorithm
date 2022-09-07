package com.company.middium;

/**
 * 79. 单词搜索 再研
 */
public class Test5 {

    int[][] dir =
            {{1, 0},
                    {0, 1},
                    {-1, 0},
                    {0, -1}};

    public static void main(String[] args) {
        char[][] board = new char[3][4];
        board[0] = new char[]{'A', 'B', 'C', 'E'};
        board[1] = new char[]{'S', 'F', 'C', 'S'};
        board[2] = new char[]{'A', 'D', 'E', 'E'};

    }

    public boolean exist(char[][] board, String word) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                boolean[][] isVisited = new boolean[board.length][board[0].length];
                isVisited[i][j] = true;
                if (dfs(board, word, i, j, 0, isVisited)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean dfs(char[][] board, String word, int i, int j, int start, boolean[][] isVisited) {
        isVisited[i][j] = true;
        start++;
        if (word.length() - 1 == start) {
            return board[i][j] == word.charAt(start);
        }
        if (word.charAt(start) == board[i][j]) {
            start++;
        }
        // 搜索四个方向
        for (int k = 0; k < 4; k++) {
            int newX = i + dir[k][0];
            int newY = j + dir[k][1];
            if (isInRange(board, newX, newY) && !isVisited[newX][newY] && board[newX][newY] == word.charAt(start)) {
                isVisited[newX][newY] = true;
                if (dfs(board, word, i, j, start, isVisited)) {
                    return true;
                }
                isVisited[newX][newY] = false;
            }
        }
        return false;
    }

    private boolean isInRange(char[][] board,int i, int j) {
        return i >= 0 && j >= 0 && i <= board.length - 1 && j <= board[0].length - 1;
    }
}
