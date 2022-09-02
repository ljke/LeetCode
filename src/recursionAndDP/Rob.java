/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package recursionAndDP;

/**
 * 198. 打家劫舍
 * https://leetcode.cn/problems/house-robber/
 *
 * @author linjie
 * @version : Rob.java, v 0.1 2022年08月21日 1:25 下午 linjie Exp $
 */
public class Rob {

    /**
     * 动态规划
     * 考虑每个房子，有偷和不偷两个选择
     *
     * @param nums
     * @return
     */
    public int rob(int[] nums) {
        int length = nums.length;
        if (length == 0) {
            return 0;
        }
        if (length == 1) {
            return nums[0];
        }
        int[] dp = new int[length];
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);
        for (int i = 2; i < length; i++) {
            dp[i] = Math.max(dp[i - 1], nums[i] + dp[i - 2]);
        }
        return dp[length - 1];
    }
}