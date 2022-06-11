/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package backtracking;

import java.util.*;

/**
 * 八皇后问题
 * https://leetcode.cn/problems/eight-queens-lcci/
 *
 * @author linjie
 * @version : NQueens.java, v 0.1 2022年06月04日 4:42 下午 linjie Exp $
 */
public class NQueens {
    /**
     * 解法1：使用set记录保存的皇后位置，判断是否重复
     *
     * @param n
     * @return
     */
    public List<List<String>> solveNQueens1(int n) {
        // 保存每行的皇后位置
        int[] queens = new int[n];
        Arrays.fill(queens, -1);
        List<List<String>> solutions = new ArrayList<>();
        // 记录占用的行
        Set<Integer> columns = new HashSet<>();
        // 记录占用的左对角线，规律：行下标与列下标之差为i
        Set<Integer> diagonals1 = new HashSet<>();
        // 记录占用的右对角线，规律：行下标与列下标之和为i
        Set<Integer> diagonals2 = new HashSet<>();
        solve1(solutions, queens, n, 0, columns, diagonals1, diagonals2);
        return solutions;
    }

    public void solve1(List<List<String>> solutions, int[] queens, int n, int row, Set<Integer> columns, Set<Integer> diagonals1, Set<Integer> diagonals2) {
        if (row == n) {
            solutions.add(printBoard(queens, n));
        } else {
            for (int i = 0; i < n; i++) {
                if (columns.contains(i)) {
                    continue;
                }
                if (diagonals1.contains(row - i)) {
                    continue;
                }
                if (diagonals2.contains(row + i)) {
                    continue;
                }
                queens[row] = i;
                columns.add(i);
                diagonals1.add(row - i);
                diagonals2.add(row + i);
                solve1(solutions, queens, n, row + 1, columns, diagonals1, diagonals2);
                queens[row] = -1;
                columns.remove(i);
                diagonals1.remove(row - i);
                diagonals2.remove(row + i);
            }
        }
    }

    public List<String> printBoard(int[] queens, int n) {
        List<String> boards = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            char[] row = new char[n];
            Arrays.fill(row, '.');
            row[queens[i]] = 'Q';
            boards.add(new String(row));
        }
        return boards;
    }

    /**
     * 解法2：不记录录保存的皇后位置，直接向上遍历判断是否有冲突
     * 斜对角线和上一行相比
     *
     * @param n
     * @return
     */
    public List<List<String>> solveNQueens2(int n) {
        // 保存每一行皇后的位置
        int[] queens = new int[n];
        Arrays.fill(queens, -1);
        List<List<String>> solutions = new ArrayList<>();
        solve2(solutions, queens, n, 0);
        return solutions;
    }

    private void solve2(List<List<String>> solutions, int[] queens, int n, int row) {
        if (row == n) {
            solutions.add(printBoard(queens, n));
        } else {
            for (int i = 0; i < n; i++) {
                if (checkOK(row, i, queens, n)) {
                    queens[row] = i;
                    solve2(solutions, queens, n, row + 1);
                    queens[row] = -1;
                }
            }
        }
    }

    private boolean checkOK(int row, int column, int[] queens, int n) {
        int leftUp = column - 1, rightUp = column + 1;
        // 向上遍历
        for (int i = row - 1; i >= 0; i--) {
            if (queens[i] == column) {
                return false;
            }
            if (leftUp >= 0) {
                if (queens[i] == leftUp) {
                    return false;
                }
            }
            if (rightUp < n) {
                if (queens[i] == rightUp) {
                    return false;
                }
            }
            leftUp--;
            rightUp++;
        }
        return true;
    }
}