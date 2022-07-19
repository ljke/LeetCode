/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package recursionAndDP;

/**
 * 62. 不同路径
 * https://leetcode.cn/problems/unique-paths/
 *
 * @author linjie
 * @version : UniquePaths.java, v 0.1 2022年07月17日 12:32 上午 linjie Exp $
 */
public class UniquePaths {

    /**
     * 动态规划
     *
     * @param m
     * @param n
     * @return
     */
    public int uniquePaths(int m, int n) {
        int[][] f = new int[m][n];
        // 处理边界条件
        for (int i = 0; i < m; i++) {
            f[i][0] = 1;
        }
        for (int j = 0; j < n; j++) {
            f[0][j] = 1;
        }
        // 填表
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                f[i][j] = f[i - 1][j] + f[i][j - 1];
            }
        }
        return f[m - 1][n - 1];
    }
}