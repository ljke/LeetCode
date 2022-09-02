/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package array;

/**
 * 287. 寻找重复数
 * https://leetcode.cn/problems/find-the-duplicate-number/
 *
 * @author linjie
 * @version : FindDuplicate.java, v 0.1 2022年08月21日 10:23 上午 linjie Exp $
 */
public class FindDuplicate {
    /**
     * 二分查找法
     * 原理：1～(target-1)的数满足count<=i, target~n的数满足count>i, 且满足单调性
     *
     * @param nums
     * @return
     */
    public int findDuplicate1(int[] nums) {
        int n = nums.length;
        int l = 1, r = n - 1, ans = -1;
        while (l <= r) {
            int mid = l + (r - l) / 2;
            // 计算当前count
            int count = 0;
            for (int i = 0; i < n; i++) {
                if (nums[i] <= mid) {
                    count++;
                }
            }
            if (count <= mid) {
                l = mid + 1;
            } else {
                r = mid - 1;
                ans = mid;
            }
        }
        return ans;
    }

    /**
     * 快慢指针
     * 以i -> nums[i]为边构造图，因为存在重复nums[i]，所以存在环
     * 找出环的入口位置
     *
     * @param nums
     * @return
     */
    public int findDuplicate2(int[] nums) {
        int slow = 0, fast = 0;
        do {
            slow = nums[slow];
            fast = nums[nums[fast]];
        } while (slow != fast);
        slow = 0;
        while (fast != slow) {
            slow = nums[slow];
            fast = nums[fast];
        }
        return fast;
    }
}