/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package backtracking;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 17. 电话号码的字母组合
 * https://leetcode.cn/problems/letter-combinations-of-a-phone-number/
 * 使用回溯进行处理
 *
 * @author linjie
 * @version : LetterCombinations.java, v 0.1 2022年07月10日 9:09 下午 linjie Exp $
 */
public class LetterCombinations {

    public List<String> letterCombinations(String digits) {
        List<String> combinations = new ArrayList<>();
        if (digits.length() == 0) {
            return combinations;
        }
        // 构建字典
        Map<Character, String> phoneMap = new HashMap<Character, String>() {
            {
                put('2', "abc");
                put('3', "def");
                put('4', "ghi");
                put('5', "jkl");
                put('6', "mno");
                put('7', "pqrs");
                put('8', "tuv");
                put('9', "wxyz");
            }
        };
        backtrace(combinations, phoneMap, digits, 0, new StringBuffer());
        return combinations;
    }

    public void backtrace(List<String> combinations, Map<Character, String> phoneMap, String digits, int index, StringBuffer sb) {
        // 到达尾部，进行保存
        if (index == digits.length()) {
            combinations.add(sb.toString());
            return;
        }
        Character c = digits.charAt(index);
        String letters = phoneMap.get(c);
        // 依次遍历所有可能，这里所有情况都是有效的
        for(int i = 0; i < letters.length(); i++) {
            char x = letters.charAt(i);
            // 处理和还原
            sb.append(x);
            backtrace(combinations, phoneMap, digits, index + 1, sb);
            sb.deleteCharAt(index);
        }
    }

}