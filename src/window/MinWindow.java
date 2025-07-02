package window;

import java.util.HashMap;
import java.util.Map;

public class MinWindow {

    Map<Character, Integer> tCount = new HashMap<>();
    Map<Character, Integer> sCount = new HashMap<>();

    /**
     * 76. 最小覆盖子串
     * https://leetcode.cn/problems/minimum-window-substring/description/
     *
     * @param s
     * @param t
     * @return
     */
    public String minWindow(String s, String t) {
        for (int i = 0; i < t.length(); i++) {
            char c = t.charAt(i);
            Integer cnt = tCount.getOrDefault(c, 0);
            tCount.put(c, ++cnt);
        }

        int left = 0, right = 0;
        int ansL = -1, ansR = -1, ansLen = Integer.MAX_VALUE;

        while (right < s.length()) {
            // 扩大右边界
            char c = s.charAt(right);
            Integer cnt = sCount.getOrDefault(c, 0);
            sCount.put(c, ++cnt);

            // 校验是否满足缩小窗口的条件
            while (check() && left <= right) {
                // 维护结果
                if (right - left + 1 < ansLen) {
                    ansLen = right - left + 1;
                    ansL = left;
                    ansR = right;
                }
                // 缩小左边界
                char a = s.charAt(left);
                Integer b = sCount.getOrDefault(a, 0);
                sCount.put(a, --b);
                left++;
            }
            right++;
        }

        return ansL == -1 ? "" : s.substring(ansL, ansR + 1);
    }

    public boolean check() {
        for (Map.Entry<Character, Integer> e : tCount.entrySet()) {
            Integer cnt = sCount.getOrDefault(e.getKey(), 0);
            if (cnt < e.getValue()) {
                return false;
            }
        }
        return true;
    }
}
