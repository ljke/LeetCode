package backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 131. 分割回文串
 * https://leetcode.cn/problems/palindrome-partitioning/description/
 *
 * @author : ljke
 * @date : Created in 12:01 2025/8/4
 */
public class Partition {

    boolean[][] f;
    List<List<String>> ret = new ArrayList<>();
    List<String> ans = new ArrayList<>();

    public List<List<String>> partition(String s) {
        // 动态规划判断s[i..j]是否是回文串
        int len = s.length();
        f = new boolean[len][len];
        for (int i = 0; i < len; i++) {
            Arrays.fill(f[i], true);
        }
        for(int i = len - 1; i >= 0; i--) {
            for(int j = i + 1; j < len; j++) {
                // 由于i依赖于i + 1，所以i需要从高位开始推导
                f[i][j] = f[i + 1][j - 1] && (s.charAt(i) == s.charAt(j));
            }
        }
        backtrace(s, 0);
        return ret;
    }

    public void backtrace(String s, int i) {
        if (i == s.length()) {
            //进行拷贝
            ret.add(new ArrayList<>(ans));
            return;
        }
        // 遍历从i开始可以组成的回文串，若i..j可以，则继续从j+1开始遍历
        for (int j = i; j < s.length(); j++) {
            if (f[i][j]) {
                ans.add(s.substring(i, j + 1));
                backtrace(s, j + 1);
                ans.remove(ans.size() - 1);
            }
        }
    }

}
