/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package array;

import java.util.Arrays;
import java.util.Stack;

/**
 * 739. 每日温度
 * https://leetcode.cn/problems/daily-temperatures/
 *
 * @author linjie
 * @version : DailyTemperatures.java, v 0.1 2022年08月13日 6:04 下午 linjie Exp $
 */
public class DailyTemperatures {

    /**
     * 暴力法
     * 核心实现是保存每个temp第一次出现的下标
     * 从后往前进行更新
     *
     * @param temperatures
     * @return
     */
    public int[] dailyTemperatures1(int[] temperatures) {
        int length = temperatures.length;
        int[] ans = new int[length];
        int[] next = new int[101];
        Arrays.fill(next, Integer.MAX_VALUE);
        // 从后往前遍历，因为只和后面的元素比较
        for (int i = length - 1; i >= 0; i--) {
            int warmIndex = Integer.MAX_VALUE;
            // 找第一个大于当前temp的位置
            for (int j = temperatures[i] + 1; j <= 100; j++) {
                if (next[j] < warmIndex) {
                    warmIndex = next[j];
                }
            }
            // 当前值有效
            if (warmIndex < Integer.MAX_VALUE) {
                ans[i] = warmIndex - i;
            }
            // 更新当前temp位置，因为是从后往前遍历，所以一定小
            next[temperatures[i]] = i;
        }
        return ans;
    }

    /**
     * 单调栈法
     *
     * @param temperatures
     * @return
     */
    public int[] dailyTemperatures2(int[] temperatures) {
        int length = temperatures.length;
        int[] ans = new int[length];
        // 单调栈，栈底 -> 栈顶 单调递减
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < length; i++) {
            int temp = temperatures[i];
            // 违反单调的栈顶元素依次出栈
            while (!stack.isEmpty() && temp > temperatures[stack.peek()]) {
                int index = stack.pop();
                ans[index] = i - index;
            }
            stack.push(i);
        }
        return ans;
    }
}