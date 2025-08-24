/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2021 All Rights Reserved.
 */
package stack;

import java.util.*;

/**
 * @author linjie
 * @version : ValidBrackets.java, v 0.1 2021年07月26日 12:20 上午 linjie Exp $
 */
public class ValidBrackets {

    /**
     * 20. 有效的括号
     * 使用栈进行匹配判断
     *
     * @param s
     * @return
     */
    public boolean isValid(String s) {
        // 长度为奇数必然不匹配
        if (s.length() % 2 == 1) {
            return false;
        }
        // 右括号和左括号映射
        Map<Character, Character> map = new HashMap<Character, Character>() {
            {
                put(')', '(');
                put(']', '[');
                put('}', '{');
            }
        };
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            // 左括号入栈，右括号匹配
            if (map.containsKey(c)) {
                if (stack.isEmpty() || stack.peek() != map.get(c)) {
                    return false;
                } else {
                    stack.pop();
                }
            } else {
                stack.push(c);
            }
        }
        return stack.isEmpty();
    }

    /**
     * 32. 最长有效括号
     * https://leetcode.cn/problems/longest-valid-parentheses/description/
     *
     * @param s
     * @return
     */
    public int longestValidParentheses(String s) {
        int maxAns = 0;
        // 栈底保存 最后一个没有被匹配的右括号的下标
        Stack<Integer> stack = new Stack<>();
        // 哨兵统一处理
        stack.push(-1);
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(') {
                stack.push(i);
            } else {
                //匹配一个左括号
                stack.pop();
                if (stack.isEmpty()) {
                    // 补充栈底
                    stack.push(i);
                } else {
                    // 说明匹配正确，计算最长有效长度
                    maxAns = Math.max(maxAns, i - stack.peek());
                }
            }
        }
        return maxAns;
    }
}