/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package array;

import java.util.Stack;

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
    public int trap1(int[] height) {
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

    public int trap2(int[] height) {
        int ans = 0;
        // 单调栈，栈底 -> 栈顶 单调递减
        Stack<Integer> stack = new Stack<>();
        int n = height.length;
        for (int i = 0; i < n; i++) {
            // 违反单调的元素依次出栈
            while (!stack.isEmpty() && height[i] > height[stack.peek()]) {
                // 需要两个元素
                int top = stack.pop();
                if (stack.isEmpty()) {
                    break;
                }
                int left = stack.peek();
                // 计算和前一个柱子的面积
                int currWidth = i - left - 1;
                int currHeight = Math.min(height[i], height[left]) - height[top];
                ans += currWidth * currHeight;
            }
            stack.push(i);
        }
        return ans;
    }
}