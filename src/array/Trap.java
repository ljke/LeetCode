/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package array;

/**
 * 42. 接雨水
 * https://leetcode.cn/problems/trapping-rain-water/
 *
 * @author linjie
 * @version : Trap.java, v 0.1 2022年07月19日 2:53 下午 linjie Exp $
 */
public class Trap {

    /**
     * 推理 + 动态规划
     *
     * @param height
     * @return
     */
    public int trap(int[] height) {
        int n = height.length;
        int[] left_peak = new int[n];
        int[] right_peak = new int[n];

        // 左边最高的柱子
        for (int i = 1; i < n; i++) {
            left_peak[i] = Math.max(left_peak[i - 1], height[i - 1]);
        }

        // 右边最高的柱子
        for (int i = n - 2; i >= 0; i--) {
            right_peak[i] = Math.max(right_peak[i + 1], height[i + 1]);
        }

        // 计算面积
        int sum = 0;
        for (int i = 0; i < n; i++) {
            int h = Math.min(left_peak[i], right_peak[i]);
            if (height[i] < h) {
                sum += (h - height[i]);
            }
        }

        return sum;
    }
}