/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2021 All Rights Reserved.
 */
package window;

import java.util.Arrays;

/**
 * 1838. 最高频元素的频数
 * https://leetcode-cn.com/problems/frequency-of-the-most-frequent-element/
 *
 * 基于两个推论：
 * 1. 操作后的最高频元素必定可以是数组中已有的某一个元素。
 * 2. 优先操作距离目标值最近的（小于目标值的）元素。
 *
 * 排序后，使用滑动窗口找出不超过操作次数k的最优解
 * 右边界为目标最高频元素，左右边界之差为最高频数
 * 右边界增加：+ (nums[r] - nums[r - 1]) * (r - l)
 * 左边界增加：- (nums[r] - nums[l])
 * @author linjie
 * @version : MaxFrequency.java, v 0.1 2021年07月19日 11:11 下午 linjie Exp $
 */
public class MaxFrequency {
    public int maxFrequency(int[] nums, int k) {
        Arrays.sort(nums);
        int n = nums.length;
        long preSum = 0;
        int l = 0, ret = 1;
        for (int r = 1; r < n; r++) {
            preSum += (long) (nums[r] - nums[r - 1]) * (r - l);
            while(preSum > k) {
                preSum -= nums[r] - nums[l];
                l++;
            }
            ret = Math.max(ret, r - l + 1);
        }
        return ret;
    }

}