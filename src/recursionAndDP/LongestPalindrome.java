/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package recursionAndDP;

/**
 * 5. 最长回文子串
 * https://leetcode.cn/problems/longest-palindromic-substring/
 * 动态规划解法
 *
 * @author linjie
 * @version : LongestPalindrome.java, v 0.1 2022年07月10日 8:36 下午 linjie Exp $
 */
public class LongestPalindrome {
    public String longestPalindrome(String s) {
        int len = s.length();
        if (len < 2) {
            return s;
        }

        // 保存结果
        int begin = 0;
        int maxLen = 1;
        // 表示[i...j]是否回文串
        boolean[][] dp = new boolean[len][len];
        // 单字符情况
        for(int i = 0; i < len; i++) {
            dp[i][i] = true;
        }
        char[] arr = s.toCharArray();
        // 遍历字符串长度
        for(int L = 2; L <= len; L++) {
            // 遍历字符串开头i
            for(int i = 0; i <= len - L; i++) {
                // 字符串结尾j
                int j = i + L - 1;
                // 比较是否回文串
                if (arr[i] != arr[j]) {
                    dp[i][j] = false;
                } else {
                    if (L <= 3) {
                        // 3个字符内必可以
                        dp[i][j] = true;
                    } else {
                        // 取决于子串
                        dp[i][j] = dp[i + 1][j - 1];
                    }
                }
                // 保存最大值
                if (dp[i][j] && L > maxLen) {
                    begin = i;
                    maxLen = L;
                }
            }
        }
        return s.substring(begin, begin + maxLen);
    }
}