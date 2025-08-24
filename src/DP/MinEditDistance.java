/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package DP;

/**
 * @author linjie
 * @version : MinEditDistance.java, v 0.1 2022年06月25日 6:04 下午 linjie Exp $
 */
public class MinEditDistance {

    /**
     * 72. 编辑距离
     * https://leetcode.cn/problems/edit-distance/
     *
     * @param word1
     * @param word2
     * @return
     */
    public int minDistance(String word1, String word2) {
        char[] a = word1.toCharArray();
        char[] b = word2.toCharArray();
        int m = a.length;
        int n = b.length;
        // 处理特殊情况，因为后面返回值为dp[m - 1][n - 1]
        if (m * n == 0) {
            return m + n;
        }
        // 哨兵优化减少边界条件判断
        int[][] dp = new int[m + 1][n + 1];
        // 初始化边界值
        // 第0列 a[0...i] 和 空字符串 的编辑距离
        for (int i = 0; i <= m; i++) {
            dp[i][0] = i;
        }
        // 第0行 空字符串 和 b[0...j] 的编辑距离
        for (int j = 0; j <= n; j++) {
            dp[0][j] = j;
        }

        // 更新dp
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (a[i- 1] == b[j - 1]) {
                    dp[i][j] = Math.min(Math.min(dp[i - 1][j - 1], dp[i - 1][j] + 1), dp[i][j - 1] + 1);
                } else {
                    dp[i][j] = Math.min(Math.min(dp[i - 1][j - 1] + 1, dp[i - 1][j] + 1), dp[i][j - 1] + 1);
                }
            }
        }
        // 得到最优解
        return dp[m][n];
    }

}