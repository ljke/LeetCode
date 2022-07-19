/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package array;

import java.util.HashSet;
import java.util.Set;

/**
 * 128. 最长连续序列
 * https://leetcode.cn/problems/longest-consecutive-sequence/
 *
 * @author linjie
 * @version : LongestConsecutive.java, v 0.1 2022年07月10日 9:26 下午 linjie Exp $
 */
public class LongestConsecutive {

    public int longestConsecutive(int[] nums) {
        // 先使用set去重
        Set<Integer> numSet = new HashSet<>();
        for(int n : nums) {
            numSet.add(n);
        }

        int longestCount = 0;
        // 遍历每个数字
        for(Integer i : numSet) {
            if (!numSet.contains(i - 1)) {
                // 如果是序列最小数字，进行处理
                int currentNum = i;
                int currentCount = 1;
                // 依次找下一个数
                while (numSet.contains(currentNum + 1)) {
                    currentNum++;
                    currentCount++;
                }
                // 比较最大值
                if (currentCount > longestCount) {
                    longestCount = currentCount;
                }
            }
        }

        return longestCount;
    }

}