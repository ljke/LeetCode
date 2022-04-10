/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2021 All Rights Reserved.
 */
package stack;

import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * 20. 有效的括号
 * 使用栈进行匹配判断
 *
 * @author linjie
 * @version : ValidBrackets.java, v 0.1 2021年07月26日 12:20 上午 linjie Exp $
 */
public class ValidBrackets {
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
        Deque<Character> stack = new LinkedList<>();
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
}