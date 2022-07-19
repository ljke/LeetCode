/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package recursionAndDP;

/**
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
     * 53. 最大子数组和
     * https://leetcode.cn/problems/maximum-subarray/
     *
     * 分治法求解，线段树求解最长公共上升子序列问题的 pushUp 操作
     *
     * @param nums
     * @return
     */
    public int maxSubArrayRecur(int[] nums) {
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


    /**
     * 53. 最大子数组和
     * https://leetcode.cn/problems/maximum-subarray/
     *
     * 基础版dp求解
     *
     * @param nums
     * @return
     */
    public int maxSubArray(int[] nums) {
        int[] dp = new int[nums.length];
        // 先计算子问题dp，以第i个数结尾的
        dp[0] = nums[0];
        for(int i = 1; i < nums.length; i++) {
            dp[i] = Math.max(dp[i - 1] + nums[i], nums[i]);
        }
        // 取最大值
        int maxW = Integer.MIN_VALUE;
        for(int i = 0; i < nums.length; i++) {
            maxW = Math.max(dp[i], maxW);
        }
        return maxW;
    }

    /**
     * 优化版dp求解
     *
     * @param nums
     * @return
     */
    public int maxSubArrayOpti(int[] nums) {
        int pre = nums[0];
        int maxW = nums[0];
        for(int i = 1; i < nums.length; i++) {
            // 只需要使用前一个状态即可
            pre = Math.max(pre + nums[i], nums[i]);
            // 同时计算最大值
            maxW = Math.max(maxW, pre);
        }
        return maxW;
    }


    /**
     * 300. 最长递增子序列
     * https://leetcode.cn/problems/longest-increasing-subsequence/
     * 动态规划，时间复杂度O(n^2)
     *
     * @param nums
     * @return
     */
    public int lengthOfLIS1(int[] nums) {
        // dp表示以i结尾的子序列的最大长度
        int[] dp = new int[nums.length];
        dp[0] = 1;
        int maxV = 1;
        for(int i = 1; i < nums.length; i++) {
            int x = 0;
            // 与前面的dp比较取最大值，条件是必须大于对应位置的值
            for(int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    x = Math.max(x, dp[j]);
                }
            }
            dp[i] = x + 1;
            // 同时计算最优解
            maxV = Math.max(dp[i], maxV);
        }
        return maxV;
    }

    /**
     * 贪心+二分查找
     * 贪心体现在每次都保存最小的末尾取值
     * 时间复杂度O(nlogn)
     *
     * @param nums
     * @return
     */
    public int lengthOfLIS2(int[] nums) {
        int len = 1;
        int n = nums.length;
        if (n == 0) {
            return 0;
        }
        // d表示长度为i的递增序列的末尾最小取值
        int[] d = new int[n + 1];
        d[len] = nums[0];
        // 依次遍历每一个数
        for (int i = 1; i < n; i++) {
            if (nums[i] > d[len]) {
                // 大于情况作为序列元素添加
                len++;
                d[len] = nums[i];
            }
            // 可证明d是单调递增的，所以可以用二分查找
            // 查找最后一个小于nums[i]的元素位置
            int l = 1, r = len, pos = 0;
            while (l <= r) {
                int mid = l + (r - l) / 2;
                if (d[mid] < nums[i]) {
                    if (mid == len || d[mid + 1] >= nums[i]) {
                        pos = mid;
                        break;
                    } else {
                        l = mid + 1;
                    }
                } else {
                    r = mid - 1;
                }
            }
            d[pos + 1] = nums[i];
        }
        return len;
    }
}