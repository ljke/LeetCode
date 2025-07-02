package enumeration;

import java.util.HashMap;
import java.util.Map;

public class MinimumDeletions {

    /**
     * 3085. 成为 K 特殊字符串需要删除的最少字符数
     * https://leetcode.cn/problems/minimum-deletions-to-make-string-k-special/description/
     *
     * @param word
     * @param k
     * @return
     */
    public int minimumDeletions(String word, int k) {
        Map<Character, Integer> cnt = new HashMap<>();
        for (char c : word.toCharArray()) {
            cnt.put(c, cnt.getOrDefault(c, 0) + 1);
        }
        int res = word.length();
        for (Integer n : cnt.values()) {
            int deleted = 0;
            // n : 将每个char当成最少的一个字母的次数
            for (Integer m : cnt.values()) {
                if (m < n) {
                    // 少于n：全部删除
                    deleted += m;
                } else if (m > (n + k)) {
                    // 多于n：差值超过k的删除
                    deleted += (m - n - k);
                }
            }
            res = Math.min(res, deleted);
        }
        return res;
    }

}
