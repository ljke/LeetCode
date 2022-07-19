/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * 22. 括号生成
 * https://leetcode.cn/problems/generate-parentheses/
 *
 * @author linjie
 * @version : GenerateParenthesis.java, v 0.1 2022年07月13日 9:09 上午 linjie Exp $
 */
public class GenerateParenthesis {

    /**
     * 暴力解法
     *
     * @param n
     * @return
     */
    public List<String> generateParenthesis_1(int n) {
        List<String> combinations = new ArrayList<>();
        // 存储所有字符
        char[] arr = new char[2 * n];
        generateAll(arr, 0, combinations);
        return combinations;
    }

    public void generateAll(char[] arr, int index, List<String> combinations) {
        if (index == arr.length) {
            if (check(arr)) {
                combinations.add(new String(arr));
            }
            return;
        }
        arr[index] = '(';
        generateAll(arr, index + 1, combinations);
        arr[index] = ')';
        generateAll(arr, index + 1, combinations);
    }

    /**
     * 判断是否匹配
     * 必然现有'(', 才有')'
     *
     * @param arr
     * @return
     */
    public boolean check(char[] arr) {
        int count = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == '(') {
                count++;
            } else if (arr[i] == ')') {
                count--;
            }
            if (count < 0) {
                return false;
            }
        }
        return count == 0;
    }

    /**
     * 回溯解法
     * 判断符合条件
     *
     * @param n
     * @return
     */
    public List<String> generateParenthesis_2(int n) {
        List<String> combinations = new ArrayList<>();
        generateAll(new StringBuffer(2 * n), 0, 0, n, combinations);
        return combinations;
    }

    public void generateAll(StringBuffer sb, int open, int close, int max, List<String> combinations) {
        if (sb.length() == 2 * max) {
            // 在添加时进行校验，所以无需check
            combinations.add(sb.toString());
            return;
        }
        // 分别对应添加左括号和右括号
        if (open < max) {
            sb.append('(');
            generateAll(sb, open + 1, close, max, combinations);
            sb.deleteCharAt(sb.length() - 1);
        }
        if (close < open) {
            sb.append(')');
            generateAll(sb, open, close + 1, max, combinations);
            sb.deleteCharAt(sb.length() - 1);
        }
    }



    ArrayList[] mem = new ArrayList[10];

    /**
     * 递归方式
     * 根据序列长度
     *
     * @param n
     * @return
     */
    public List<String> generateParenthesis(int n) {
        return generateAll(n, mem);
    }

    public List<String> generateAll(int n, ArrayList[] mem) {
        // 备忘录
        if (mem[n] != null) {
            return mem[n];
        }
        ArrayList<String> list = new ArrayList<>();
        if (n == 0) {
            list.add("");
        } else {
            for (int i = 0; i < n; i++) {
                // 所有可能情况可以分解为(A)B形式，遍历所有情况
                for (String left : generateAll(i, mem)) {
                    for (String right : generateAll(n - 1 - i, mem)) {
                        String ans = "(" + left + ")" + right;
                        list.add(ans);
                    }
                }
            }
        }
        mem[n] = list;
        return list;
    }
}