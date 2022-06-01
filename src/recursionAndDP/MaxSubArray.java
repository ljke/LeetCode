/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package recursionAndDP;

/**
 * 最大子数组和
 * https://leetcode.cn/problems/maximum-subarray/
 *
 * @author linjie
 * @version : MaxSubArray.java, v 0.1 2022年05月31日 8:50 上午 linjie Exp $
 */
public class MaxSubArray {
    public class Status {
        int lSum, rSum, mSum, iSum;
        public Status(int lSum, int rSum, int mSum, int iSum) {
            this.lSum = lSum;
            this.rSum = rSum;
            this.mSum = mSum;
            this.iSum = iSum;
        }
    }

    /**
     * 分治法求解，线段树求解最长公共上升子序列问题的 pushUp 操作
     *
     * @param nums
     * @return
     */
    public int maxSubArray(int[] nums) {
        return getInfo(nums, 0, nums.length - 1).mSum;
    }

    private Status getInfo(int[] nums, int l, int r) {
        if (l == r) {
            return new Status(nums[l], nums[l], nums[l], nums[l]);
        }
        int m = (l + r) >> 1;
        Status lSub = getInfo(nums, l, m);
        Status rSub = getInfo(nums, m + 1, r);
        return pushUp(lSub, rSub);
    }

    private Status pushUp(Status lSub, Status rSub) {
        int iSum = lSub.iSum + rSub.iSum;
        int lSum = Math.max(lSub.lSum, lSub.iSum + rSub.lSum);
        int rSum = Math.max(rSub.rSum, rSub.iSum + lSub.rSum);
        int mSum = Math.max(Math.max(lSub.mSum, rSub.mSum), lSub.rSum + rSub.lSum);
        return new Status(lSum, rSum, mSum, iSum);
    }
}