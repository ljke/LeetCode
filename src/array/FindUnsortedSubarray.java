/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package array;

import java.util.Arrays;

/**
 * 581. 最短无序连续子数组
 * https://leetcode.cn/problems/shortest-unsorted-continuous-subarray/
 *
 * @author linjie
 * @version : FindUnsortedSubarray.java, v 0.1 2022年08月20日 6:03 下午 linjie Exp $
 */
public class FindUnsortedSubarray {

    /**
     * 排序，然后和原数组对比
     *
     * @param nums
     * @return
     */
    public int findUnsortedSubarray1(int[] nums) {
        if (isSorted(nums)) {
            return 0;
        }
        // 获得有序列表
        int[] numsSorted = new int[nums.length];
        System.arraycopy(nums, 0, numsSorted, 0, nums.length);
        Arrays.sort(numsSorted);
        // 计算匹配的左边界和右边界
        int left = 0;
        while (nums[left] == numsSorted[left]) {
            left++;
        }
        int right = nums.length - 1;
        while (nums[right] == numsSorted[right]) {
            right--;
        }
        // 计算差值
        return right - left + 1;
    }

    public boolean isSorted(int[] nums) {
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] < nums[i - 1]) {
                return false;
            }
        }
        return true;
    }

    /**
     * 一次遍历
     * 根据有序区特性计算左右边界
     *
     * @param nums
     * @return
     */
    public int findUnsortedSubarray2(int[] nums) {
        int maxn = Integer.MIN_VALUE;
        int minn = Integer.MAX_VALUE;
        int left = -1;
        int right = -1;
        int len = nums.length;
        // 分为A，B，C三个区间
        for (int i = 0; i < len; i++) {
            // 确定右边界，从前往后遍历，最后一个不满足 nums[i] > maxn(C区条件)
            if (maxn > nums[i]) {
                right = i;
            } else {
                maxn = nums[i];
            }
            // 确定左边界，从后往前遍历，最后一个不满足 nums[i] < minn(A区条件)
            if (minn < nums[len - 1 - i]) {
                left = len - 1 - i;
            } else {
                minn = nums[len - 1 - i];
            }
        }
        return right == -1 ? 0 : right - left + 1;
    }
}