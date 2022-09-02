/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package recursionAndDP;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 139. 单词拆分
 * https://leetcode.cn/problems/word-break/
 *
 * @author linjie
 * @version : WordBreak.java, v 0.1 2022年08月21日 1:21 下午 linjie Exp $
 */
public class WordBreak {
    /**
     * 动态规划思想，从前往后遍历
     * 将子字符串分为前后部分，前部分用dp，后部分用保存的set判断
     *
     * @param s
     * @param wordDict
     * @return
     */
    public boolean wordBreak(String s, List<String> wordDict) {
        Set<String> wordDictSet = new HashSet<>(wordDict);
        boolean[] dp = new boolean[s.length() + 1];
        dp[0] = true;
        for (int i = 1; i <= s.length(); i++) {
            for (int j = 0; j < i; j++) {
                if (dp[j] && wordDictSet.contains(s.substring(j, i))) {
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[s.length()];
    }
}