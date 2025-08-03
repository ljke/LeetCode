package backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : ljke
 * @date : Created in 00:41 2025/8/3
 */
public class SolveSudoku {

    private boolean[][] row = new boolean[9][9];

    private boolean[][] column = new boolean[9][9];

    private boolean [][][] block = new boolean[3][3][9];

    private List<int[]> spaces = new ArrayList<>();

    /**
     * 37. 解数独
     * https://leetcode.cn/problems/sudoku-solver/description/
     *
     * @param board
     */
    public void solveSudoku(char[][] board) {
        // 初始化问题空间
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == '.') {
                    spaces.add(new int[]{i, j});
                } else {
                    int num = board[i][j] - '0' - 1;
                    row[i][num] = true;
                    column[j][num] = true;
                    block[i / 3][j / 3][num] = true;
                }
            }
        }
        backtrace(board, 0);
    }

    public boolean backtrace(char[][] board, int idx) {
        if (idx == spaces.size()) {
            // 所有位置都已填充
            return true;
        }
        int[] space = spaces.get(idx);
        int i = space[0], j = space[1];
        for (int num = 0; num < 9; num++) {
            if (row[i][num] || column[j][num] || block[i / 3][j / 3][num]) {
                // 跳过非法情况
                continue;
            }
            // 设值
            board[i][j] = (char)('0' + num + 1);
            row[i][num] = true;
            column[j][num] = true;
            block[i / 3][j / 3][num] = true;
            if (backtrace(board, idx + 1)) {
                // 此处需要返回，避免后续撤销操作
                return true;
            }
            // 撤销
            row[i][num] = false;
            column[j][num] = false;
            block[i / 3][j / 3][num] = false;
        }
        return false;
    }

}
