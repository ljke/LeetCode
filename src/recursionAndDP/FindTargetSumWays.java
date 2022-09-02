/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package recursionAndDP;

/**
 * 494. 目标和
 * https://leetcode.cn/problems/target-sum/
 *
 * @author linjie
 * @version : FindTargetSumWays.java, v 0.1 2022年08月20日 8:57 下午 linjie Exp $
 */
public class FindTargetSumWays {

    /**
     * 动态规划
     *
     * @param nums
     * @param target
     * @return
     */
    public int findTargetSumWays1(int[] nums, int target) {
        // 统计总和
        int sum = 0;
        for (int n : nums) {
            sum += n;
        }
        int diff = sum - target;
        if (diff < 0 || diff % 2 != 0) {
            return 0;
        }
        int n = nums.length;
        int neg = (sum - target) / 2;
        // 问题转换为n个数和等于neg
        // dp表示使用前i个数和为j的方案数
        int[][] dp = new int[n + 1][neg + 1];
        dp[0][0] = 1;
        for (int i = 1; i <= n; i++) {
            int num = nums[i - 1];
            for(int j = 0; j <= neg; j++) {
                dp[i][j] = dp[i - 1][j];
                if (j >= num) {
                    dp[i][j] += dp[i - 1][j - num];
                }
            }
        }
        return dp[n][neg];
    }

    int count = 0;

    /**
     * 回溯解法
     *
     * @param nums
     * @param target
     * @return
     */
    public int findTargetSumWays2(int[] nums, int target) {
        backtrace(nums, target, 0, 0);
        return count;
    }

    public void backtrace(int[] nums, int target, int index, int sum) {
        if (index == nums.length) {
            if (sum == target) {
                count++;
            }
            return;
        }
        // 两种情况
        backtrace(nums, target, index + 1, sum + nums[index]);
        backtrace(nums, target, index + 1, sum - nums[index]);
    }


}