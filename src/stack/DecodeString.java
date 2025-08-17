package stack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

/**
 * 394. 字符串解码
 * https://leetcode.cn/problems/decode-string/description/
 *
 * @author : ljke
 * @date : Created in 14:34 2025/8/16
 */
public class DecodeString {

    /**
     * 全局变量保存遍历位置
     * 在子函数中也会修改
     */
    int ptr;

    public String decodeString(String s) {
        Stack<String> stack = new Stack<>();
        List<String> strList = new ArrayList<>();
        ptr = 0;
        while(ptr < s.length()) {
            char c = s.charAt(ptr);
            if (Character.isDigit(c)) {
                // 次数作为完整字符串保存
                // ptr在子函数中维护
                stack.push(getDigit(s));
            } else if (c == '[' || Character.isLetter(c)) {
                // 需要保存"["，用来分割数字和字符串
                stack.push(String.valueOf(c));
                ptr++;
            } else if (c == ']') {
                strList.clear();
                while (!"[".equals(stack.peek())) {
                    strList.add(stack.pop());
                }
                Collections.reverse(strList);
                // 移除"["
                stack.pop();
                // 下一个元素是次数
                int count = Integer.parseInt(stack.pop());
                stack.push(getString(strList, count));
                ptr++;
            }
        }
        strList.clear();
        while (!stack.isEmpty()) {
            strList.add(stack.pop());
        }
        Collections.reverse(strList);
        return getString(strList, 1);
    }

    private String getDigit(String s) {
        StringBuilder sb = new StringBuilder();
        while (Character.isDigit(s.charAt(ptr))) {
            sb.append(s.charAt(ptr));
            ptr++;
        }
        return sb.toString();
    }

    private String getString(List<String> strList, int count) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < count; i++) {
            for (String s : strList) {
                sb.append(s);
            }
        }
        return sb.toString();
    }

}
