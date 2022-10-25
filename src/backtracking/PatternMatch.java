/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package backtracking;

/**
 * 10. 正则表达式匹配
 * https://leetcode.cn/problems/regular-expression-matching/
 *
 * @author linjie
 * @version : PatternMatch.java, v 0.1 2022年10月05日 4:49 下午 linjie Exp $
 */
public class PatternMatch {

    boolean isMatch = false;
    /**
     * 回溯解法
     *
     * @param s
     * @param p
     * @return
     */
    public boolean isMatch(String s, String p) {
        bt_match(0, 0, s, p);
        return isMatch;
    }

    private void bt_match(int si, int pi, String s, String p) {
        if (isMatch) {
            return;
        }
        if (pi == p.length()) {
            // 匹配到结尾
            if (si == s.length()) {
                isMatch = true;
            }
            return;
        }
        // 判断后面是否带*
        if (pi < p.length() - 1 && p.charAt(pi + 1) == '*') {
            // 0个字符单独考虑
            // p跳过2个
            bt_match(si, pi + 2, s, p);
            // 判断字符是否相等
            for (int i = si; i < s.length(); i++) {
                if ((p.charAt(pi) == s.charAt(i) || p.charAt(pi) == '.')) {
                    bt_match(i + 1, pi + 2, s, p);
                } else {
                    // 遇到不匹配需要跳出循环
                    break;
                }
            }
        } else if (si < s.length() && pi < p.length() && (p.charAt(pi) == s.charAt(si) || p.charAt(pi) == '.')){
            bt_match(si + 1, pi + 1, s, p);
        }
    }

    public static void main(String[] args) {
        String s = "a";
        String p = "ab*";
        PatternMatch pm = new PatternMatch();
        boolean result = pm.isMatch(s, p);
        System.out.println(result);
    }
}