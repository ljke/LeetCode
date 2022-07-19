/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package recursionAndDP;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 435. 无重叠区间
 * https://leetcode.cn/problems/non-overlapping-intervals/
 *
 * @author linjie
 * @version : EraseOverlapIntervals.java, v 0.1 2022年07月16日 4:45 下午 linjie Exp $
 */
public class EraseOverlapIntervals {

    /**
     * 动态规划
     *
     * @param intervals
     * @return
     */
    public int eraseOverlapIntervals1(int[][] intervals) {
        if (intervals.length == 0) {
            return 0;
        }
        // 先按左端点进行升序排序
        Arrays.sort(intervals, new Comparator<int[]>() {
            public int compare(int[] interval1, int[] interval2) {
                return interval1[0] - interval2[0];
            }
        });
        // 动态规划，计算当前最大不重叠的区间
        int n = intervals.length;
        // dp表示截止当前的最大不重叠区间区间数
        int[] dp = new int[n];
        Arrays.fill(dp, 1);
        int max = dp[0];
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                // 依次和之前的区间对比边界值，满足的情况取最大
                if (intervals[j][1] <= intervals[i][0]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            // 取最大值
            if (dp[i] > max) {
                max = dp[i];
            }
        }
        return n - max;
    }

    /**
     * 贪心算法
     *
     * @param intervals
     * @return
     */
    public int eraseOverlapIntervals2(int[][] intervals) {
        if (intervals.length == 0) {
            return 0;
        }
        // 先按右端点进行升序排序
        Arrays.sort(intervals, new Comparator<int[]>() {
            public int compare(int[] interval1, int[] interval2) {
                return interval1[1] - interval2[1];
            }
        });
        // 贪心算法, 每次都取最小的右边界
        int n = intervals.length;
        int right = intervals[0][1];
        int count = 1;
        // 遍历
        for(int i = 1; i < n; i++) {
            if (intervals[i][0] >= right) {
                count++;
                right = intervals[i][1];
            }
        }
        return n - count;
    }
}