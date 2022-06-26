/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package recursionAndDP;

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
        // 可以设置为[m + 1][n + 1] 哨兵优化减少边界条件判断
        int[][] dp = new int[m][n];
        // 初始化边界值
        // 第0行 a[0] 和 b[0...j] 的编辑距离
        for (int j = 0; j < n; j++) {
            if (a[0] == b[j]) {
                dp[0][j] = j;
            } else if (j != 0) {
                dp[0][j] = dp[0][j - 1] + 1;
            } else {
                dp[0][j] = 1;
            }
        }
        // 第0列 a[0...i] 和 b[0] 的编辑距离
        for (int i = 0; i < m; i++) {
            if (b[0] == a[i]) {
                dp[i][0] = i;
            } else if (i != 0) {
                dp[i][0] = dp[i - 1][0] + 1;
            } else {
                dp[i][0] = 1;
            }
        }
        // 更新dp
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (a[i] == b[j]) {
                    dp[i][j] = Math.min(Math.min(dp[i - 1][j - 1], dp[i - 1][j] + 1), dp[i][j - 1] + 1);
                } else {
                    dp[i][j] = Math.min(Math.min(dp[i - 1][j - 1] + 1, dp[i - 1][j] + 1), dp[i][j - 1] + 1);
                }
            }
        }
        // 得到最优解
        return dp[m - 1][n - 1];
    }

    /**
     * 1143. 最长公共子序列
     * https://leetcode.cn/problems/longest-common-subsequence/
     *
     * @param text1
     * @param text2
     * @return
     */
    public int longestCommonSubsequence(String text1, String text2) {
        int m = text1.length(), n = text2.length();
        // 哨兵优化减少判断，第0行/列相当于和空字符串相比，所以等于0
        int[][] dp = new int[m + 1][n + 1];
        for (int i = 1; i <= m; i++) {
            char c1 = text1.charAt(i - 1);
            for (int j = 1; j <= n; j++) {
                char c2 = text2.charAt(j - 1);
                if (c1 == c2) {
                    // 相等直接+1
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    // 不相等根据子问题推导
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[m][n];
    }

}