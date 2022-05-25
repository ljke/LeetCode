/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package array;

/**
 * 盛最多水的容器
 * https://leetcode.cn/problems/container-with-most-water/
 *
 * @author linjie
 * @version : MaxArea.java, v 0.1 2022年05月26日 12:11 上午 linjie Exp $
 */
public class MaxArea {
    public int maxArea(int[] height) {
        int max = 0;
        int l = 0, r = height.length - 1;
        while(l < r) {
            int area = Math.min(height[l], height[r]) * (r - l);
            max = Math.max(max, area);
            // 排除调当前小的这个边界
            if (height[l] <= height[r]) {
                l++;
            } else {
                r--;
            }
        }
        return max;
    }
}