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

    public int countSubString(String s) {
        int n = s.length(), ans = 0;
        for (int i = 0; i < 2 * n - 1; i++) {
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