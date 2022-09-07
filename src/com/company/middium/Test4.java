package com.company.middium;

/**
 * 79. 单词搜索
 */
public class Test4 {
    public static void main(String[] args) {
        char[][] board = new char[3][4];
        board[0] = new char[]{'A', 'B', 'C', 'E'};
        board[1] = new char[]{'S', 'F', 'C', 'S'};
        board[2] = new char[]{'A', 'D', 'E', 'E'};
        Test4 test4 = new Test4();
        System.out.println(test4.exist(board, "ABCCED"));
    }

    int row, col;
    int[][] dir = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};

    public boolean exist(char[][] board, String word) {
        row = board.length;
        col = row == 0 ? 0 : board[0].length;

        for (int i = 0; i < row; i++)
            for (int j = 0; j < col; j++)
                if (board[i][j] == word.charAt(0)) {
                    boolean[][] visited = new boolean[row][col];
                    visited[i][j] = true;
                    if (backtrack(board, word, 0, i, j, visited))
                        return true;
                }
        return false;
    }

    private boolean backtrack(char[][] board, String word, int progress, int i, int j, boolean[][] visited) {
        //if(progress == word.length())       return true;
        if (progress == word.length() - 1) return word.charAt(progress) == board[i][j];

        if (word.charAt(progress) == board[i][j]) {
            progress++;


            for (int k = 0; k < 4; k++) {
                int newX = i + dir[k][0];
                int newY = j + dir[k][1];

                if (isInRange(newX, newY) && !visited[newX][newY] && board[newX][newY] == word.charAt(progress)) {
                    visited[newX][newY] = true;

                    if (backtrack(board, word, progress, newX, newY, visited))
                        return true;

                    visited[newX][newY] = false;
                }
            }
        }

        return false;
    }

    private boolean isInRange(int i, int j) {
        return i >= 0 && j >= 0 && i < row && j < col;
    }
}
