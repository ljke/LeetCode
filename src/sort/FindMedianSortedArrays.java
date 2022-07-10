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
    /**
     * 使用归并排序类似的思想
     * 每次从数组中选一个数
     * 时间复杂度O(m + n)
     *
     * @param nums1
     * @param nums2
     * @return
     */
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int len1 = nums1.length;
        int len2 = nums2.length;
        // 分奇偶
        boolean isOdd = ((len1 + len2) % 2 == 1);
        // 计数目标
        int target = isOdd ? ((len1 + len2) / 2 + 1) : ((len1 + len2) / 2);
        int count = 0;
        int i = 0, j = 0;
        int res = 0;
        // 依次选择每个数组中的较小值
        while (count < target) {
            if (i == len1) {
                res = nums2[j];
                j++;
            } else if (j == len2) {
                res = nums1[i];
                i++;
            } else if (nums1[i] < nums2[j]) {
                res = nums1[i];
                i++;
            } else {
                res = nums2[j];
                j++;
            }
            count++;
        }
        if (isOdd) {
            // 奇数情况下直接返回对应下标
            return res;
        } else {
            // 偶数情况下要和下一个数求平均
            int nextNum;
            if (i == len1) {
                nextNum = nums2[j];
            } else if(j == len2) {
                nextNum = nums1[i];
            } else{
                nextNum = Math.min(nums1[i], nums2[j]);
            }
            return ((double)res + nextNum) / 2;
        }
    }

    /**
     * 使用快排分区思想
     * 时间复杂度O(log(m + n))
     *
     * @param nums1
     * @param nums2
     * @return
     */
    public double findMedianSortedArrays_2(int[] nums1, int[] nums2) {
        int total = nums1.length + nums2.length;
        if (total % 2 == 1) {
            return findKth(nums1, 0, nums2, 0, total / 2 + 1);
        } else {
            return (findKth(nums1, 0, nums2, 0, total / 2) + findKth(nums1, 0, nums2, 0, total / 2 + 1)) / 2.0;
        }
    }

    public int findKth(int[] A, int i, int[] B, int j, int k) {
        // 永远保证A是较短的序列
        if (A.length - i > B.length - j) {
            return findKth(B, j, A, i, k);
        }
        // A已经到头了，返回B中的就可以了
        if (A.length == i) {
            return B[j + k - 1];
        }
        // 只取1个数，直接返回
        if (k == 1) {
            return Math.min(A[i], B[j]);
        }
        //分别比较2个部分的中点, 可以排除掉其中的一半
        int k1 = Math.min(A.length - i, k / 2);
        int k2 = k - k1;
        if (A[i + k1 - 1] == B[j + k2 - 1]) {
            return A[i + k1 - 1];
        } else if (A[i + k1 - 1] < B[j + k2 - 1]) {
            return findKth(A, i + k1, B, j, k - k1);
        } else {
            return findKth(A, i, B, j + k2, k - k2);
        }
    }
}