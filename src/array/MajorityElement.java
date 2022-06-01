/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package array;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 多数元素
 * https://leetcode.cn/problems/majority-element/
 *
 * @author linjie
 * @version : MajorityElement.java, v 0.1 2022年05月31日 11:48 下午 linjie Exp $
 */
public class MajorityElement {

    /**
     * 计数法，保存nums计数
     *
     * @param nums
     * @return
     */
    public int majorityElement1(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (!map.containsKey(nums[i])) {
                map.put(nums[i], 1);
            } else {
                Integer count = map.get(nums[i]);
                map.put(nums[i], count + 1);
            }
        }
        int mid = nums.length >> 1;
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (entry.getValue() > mid) {
                return entry.getKey();
            }
        }
        return 0;
    }

    /**
     * 排序法，中间元素必是众数
     *
     * @param nums
     * @return
     */
    public int majorityElement2(int[] nums) {
        Arrays.sort(nums);
        return nums[nums.length / 2];
    }


    /**
     * 摩尔投票法
     *
     * @param nums
     * @return
     */
    public int majorityElement3(int[] nums) {
        int count = 0;
        int candidate = nums[0];
        for (int i = 0; i < nums.length; i++) {
            if (count == 0) {
                candidate = nums[i];
            }
            if (candidate == nums[i]) {
                count++;
            } else {
                count--;
            }
        }
        return candidate;
    }

    /**
     * 分治法，分别统计子区间的众数
     *
     * @param nums
     * @return
     */
    public int majorityElement4(int[] nums) {
        return majorityElementRec(nums, 0, nums.length - 1);
    }

    public int majorityElementRec(int[] nums, int l, int r) {
        if (l == r) {
            return nums[l];
        }
        int mid = l + (r - l) / 2;
        int left = majorityElementRec(nums, l, mid);
        int right = majorityElementRec(nums, mid + 1, r);
        if (left == right) {
            return left;
        } else {
            int leftCount = count(left, nums, l, r);
            int rightCount = count(right, nums, l, r);
            return leftCount > rightCount ? left : right;
        }
    }

    public int count(int c, int[] nums, int l, int r) {
        int count = 0;
        for (int i = l; i <= r; i ++) {
            if (c == nums[i]) {
                count++;
            }
        }
        return count;
    }

}