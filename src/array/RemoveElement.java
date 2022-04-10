/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2021 All Rights Reserved.
 */
package array;

/**
 * 27. 移除元素
 * 双指针遍历
 *
 * @author linjie
 * @version : RemoveElement.java, v 0.1 2021年08月25日 9:20 上午 linjie Exp $
 */
public class RemoveElement {
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
}