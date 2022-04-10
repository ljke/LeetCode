/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2021 All Rights Reserved.
 */
package sort;

/**
 * 4. 寻找两个正序数组的中位数
 *
 * @author linjie
 * @version : FindMedianSortedArrays.java, v 0.1 2021年07月24日 11:53 下午 linjie Exp $
 */
public class FindMedianSortedArrays {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int len1 = nums1.length;
        int len2 = nums2.length;
        // 分奇偶
        boolean isOdd = ((len1 + len2) % 2 == 1);
        // 计数目标
        int target = isOdd ? ((len1 + len2) / 2 + 1) : ((len1 + len2) / 2);
        int count = 0;
        int i = 0, j = 0;
        while (count < target) {
//            if (i)
        }
        return 0d;
    }

}