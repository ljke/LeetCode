/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2021 All Rights Reserved.
 */
package string;

/**
 * @author linjie
 * @version : Palindrome.java, v 0.1 2021年08月20日 9:21 上午 linjie Exp $
 */
public class Palindrome {
    /**
     * 回文数
     * https://leetcode-cn.com/problems/palindrome-number/
     *
     * @param x
     * @return
     */
    public boolean isPalindromeNum(int x) {
        if (x < 0 || (x % 10 == 0 && x != 0)) {
            return false;
        }

        int reverseNum = 0;
        while (x > reverseNum) {
            reverseNum = reverseNum * 10 + (x % 10);
            x /= 10;
        }

        return x == reverseNum || x == (reverseNum / 10);
    }

    /**
     * 回文字符串
     * https://leetcode-cn.com/problems/valid-palindrome/
     *
     * @param s
     * @return
     */
    public boolean isPalindromeString(String s) {
        int n = s.length();
        int left = 0, right = n - 1;
        while (left < right) {
            while (left < right && !Character.isLetterOrDigit(s.charAt(left))) {
                left++;
            }
            while (left < right && !Character.isLetterOrDigit(s.charAt(right))) {
                right--;
            }
            if (left < right) {
                if (Character.toLowerCase(s.charAt(left)) != Character.toLowerCase(s.charAt(right))) {
                    return false;
                }
                left++;
                right--;
            }
        }
        return true;
    }

    /**
     * 回文子串
     * https://leetcode-cn.com/problems/palindromic-substrings/
     *
     * @param s
     * @return
     */
    public int countSubString(String s) {
        int n = s.length(), ans = 0;
        for (int i = 0; i < 2 * n - 1; i++) {
            // 遍历所有的回文串中心
            int left = i / 2, right = i / 2 + i % 2;
            while (left >= 0 && right < n && s.charAt(left) == s.charAt(right)) {
                ans++;
                left--;
                right++;
            }
        }
        return ans;
    }
}