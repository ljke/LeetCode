/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2021 All Rights Reserved.
 */
package window;

import java.util.HashSet;
import java.util.Set;

/**
 * 3. 无重复字符的最长子串
 * 滑动窗口 使用HashSet保存
 * @author linjie
 * @version : LengthOfLongestSubstring.java, v 0.1 2021年07月23日 1:01 上午 linjie Exp $
 */
public class LengthOfLongestSubstring {
    public int lengthOfLongestSubstring(String s) {
        Set<Character> appeared = new HashSet<>();
        int len = s.length();
        int left = 0, right = -1;
        int ans = 0;
        for (; left < len; left++) {
            if (left != 0) {
                appeared.remove(s.charAt(left - 1));
            }
            while ((right + 1 < len) && (!appeared.contains(s.charAt(right + 1)))) {
                appeared.add(s.charAt(right + 1));
                right++;
            }
            ans = Math.max(ans, right - left + 1);
        }
        return ans;
    }
}