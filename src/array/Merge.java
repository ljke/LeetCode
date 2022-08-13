/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 56. 合并区间
 * https://leetcode.cn/problems/merge-intervals/
 *
 * @author linjie
 * @version : Merge.java, v 0.1 2022年08月03日 8:21 上午 linjie Exp $
 */
public class Merge {
    public int[][] merge(int[][] intervals) {
        if (intervals.length == 0) {
            return new int[0][2];
        }
        // 按左边界排序
        Arrays.sort(intervals, (interval1, interval2) -> interval1[0] - interval2[0]);
        List<int[]> res = new ArrayList<>();
        // 遍历处理，按右边界大小
        for (int i = 0; i < intervals.length; i++) {
            if (res.size() == 0 || res.get(res.size() - 1)[1] < intervals[i][0]) {
                res.add(intervals[i]);
            } else {
                res.get(res.size() - 1)[1] = Math.max(res.get(res.size() - 1)[1], intervals[i][1]);
            }
        }
        return res.toArray(new int[res.size()][]);
    }
}