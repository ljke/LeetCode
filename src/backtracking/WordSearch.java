/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package backtracking;

/**
 * 79. 单词搜索
 * https://leetcode.cn/problems/word-search/
 *
 * @author linjie
 * @version : WordSearch.java, v 0.1 2022年10月06日 6:56 下午 linjie Exp $
 */
public class WordSearch {
    public boolean exist(char[][] board, String word) {
        int h = board.length, w = board[0].length;
        boolean[][] visited = new boolean[h][w];
        // 以每个点作为起点
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                boolean res = check(board, visited, i, j, word, 0);
                if (res) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean check(char[][] board, boolean[][] visited, int i, int j, String word, int k) {
        // 退出条件
        if (board[i][j] != word.charAt(k)) {
            return false;
        } else if (k == (word.length() - 1)) {
            return true;
        }
        // 更新visited
        visited[i][j] = true;
        boolean res = false;
        int[][] directions = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        int h = board.length, w = board[0].length;
        for (int[] d : directions) {
            int newi = i + d[0];
            int newj = j + d[1];
            // 判断条件
            if ((newi >= 0 && newi < h) && (newj >= 0 && newj < w) && !visited[newi][newj]) {
                res = check(board, visited, newi, newj, word, k + 1);
                if (res) {
                    break;
                }
            }
        }
        // 恢复visited
        visited[i][j] = false;
        return res;
    }
}