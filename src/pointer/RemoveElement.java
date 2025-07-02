/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2021 All Rights Reserved.
 */
package pointer;

/**
 * 双指针遍历 移除元素
 * 原地修改
 *
 * @author linjie
 * @version : RemoveElement.java, v 0.1 2021年08月25日 9:20 上午 linjie Exp $
 */
public class RemoveElement {

    /**
     * 27.移除数据中特定值
     * https://leetcode-cn.com/problems/remove-element/
     *
     * @param nums
     * @param val
     * @return
     */
    public int removeElement(int[] nums, int val) {
        int n = nums.length;
        int left = 0, right = 0;
        while (right < n) {
            if (nums[right] != val) {
                nums[left] = nums[right];
                left++;
                right++;
            } else {
                right++;
            }
        }
        return left;
    }

    /**
     * 26. 删除有序数组中的重复项
     * https://leetcode.cn/problems/remove-duplicates-from-sorted-array/
     *
     * @param nums
     * @return
     */
    public int removeDuplicates(int[] nums) {
        int n = nums.length;
        if (n == 0) {
            return 0;
        }
        int left = 1, right = 1;
        while (right < n) {
            if (nums[right] != nums[right - 1]) {
                // 和上一个元素不相等，说明不是重复项
                nums[left] = nums[right];
                left++;
                right++;
            } else {
                right++;
            }
        }
        return left;
    }
}