package DP;

/**
 * @author : ljke
 * @date : Created in 21:18 2025/8/17
 */
public class longestCommonSubsequence {
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
