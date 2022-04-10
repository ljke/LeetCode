/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2021 All Rights Reserved.
 */
package array;

/**
 * 560. 和为K的子数组
 * https://leetcode-cn.com/problems/subarray-sum-equals-k/
 * 使用前缀和 preSum[i] = 前i个数之和
 * preSum[i] - preSum[j] = (j, i]数组的和
 * @author linjie
 * @version : SubArraySum.java, v 0.1 2021年07月21日 1:43 上午 linjie Exp $
 */
public class SubArraySum {
    public int subarraySum(int[] nums, int k) {
        //提前计算前缀和
        int n = nums.length;
        int[] preSum = new int[n + 1];
        preSum[0] = 0;
        for (int i = 0; i < n; i++) {
            preSum[i + 1] = preSum[i] + nums[i];
        }
        int ans = 0;
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j < i; j++) {
                if (preSum[i] - preSum[j] == k) {
                    ans++;
                }
            }
        }
        return ans;
    }
}