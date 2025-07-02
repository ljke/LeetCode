package window;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FindAnagrams {

    /**
     * 438. 找到字符串中所有字母异位词
     * https://leetcode.cn/problems/find-all-anagrams-in-a-string/description/
     * 固定间隔的滑动窗口
     *
     * @param s
     * @param p
     * @return
     */
    public List<Integer> findAnagrams(String s, String p) {
        int sLen = s.length(), pLen = p.length();

        if (sLen < pLen) {
            return new ArrayList<>();
        }

        List<Integer> ans = new ArrayList<>();
        int[] pCount = new int[26];
        int[] sCount = new int[26];
        // 初始化第一次
        for (int i = 0; i < pLen; i++) {
            pCount[p.charAt(i) - 'a']++;
            sCount[s.charAt(i) - 'a']++;
        }
        if (Arrays.equals(pCount, sCount)) {
            ans.add(0);
        }
        // 固定间隔的窗口
        for (int i = 1; i <= (sLen - pLen); i++) {
            sCount[s.charAt(i - 1) - 'a']--;
            sCount[s.charAt(i + pLen - 1) - 'a']++;
            if (Arrays.equals(pCount, sCount)) {
                ans.add(i);
            }
        }

        return ans;
    }

}
