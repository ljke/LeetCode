/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package DP;

/**
 * @author linjie
 * @version : TargetSumSolution.java, v 0.1 2022年08月20日 8:57 下午 linjie Exp $
 */
public class TargetSumSolution {

    /**
     * 494. 目标和
     * https://leetcode.cn/problems/target-sum/
     *
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
                // 选取 + 不选取
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

    /**
     * 416. 分割等和子集
     * https://leetcode.cn/problems/partition-equal-subset-sum/description/
     *
     * @param nums
     * @return
     */
    public boolean canPartition(int[] nums) {
        int len = nums.length;
        // 排除一些不可能情况
        if (len < 2) {
            return false;
        }
        int sum = 0;
        int maxNum = 0;
        for (int n : nums) {
            sum += n;
            maxNum = Math.max(maxNum, n);
        }
        if (sum % 2 != 0) {
            return false;
        }
        int target = sum / 2;
        if (maxNum > (sum / 2)) {
            return false;
        }
        // 转换成背包问题，dp[i][j]表示从数组[0,i]范围内选取的数和能否等于j
        boolean[][] dp = new boolean[len + 1][target + 1];
        dp[0][0] = true;
        for (int i = 1; i <= len; i++) {
            int num = nums[i - 1];
            for (int j = 0; j <= target; j++) {
                // 选取 + 不选取
                if (j >= num) {
                    dp[i][j] = dp[i - 1][j] | dp[i - 1][j - num];
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
        return dp[len][target];
    }


}