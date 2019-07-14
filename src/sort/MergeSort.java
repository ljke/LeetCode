package sort;

public class MergeSort {
    /**
     * 88. 合并两个有序数组
     * https://leetcode-cn.com/problems/merge-sorted-array/
     * 双指针，从后往前填充
     */
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int p1 = m - 1;
        int p2 = n - 1;
        int p3 = m + n - 1;
        while (p1 >= 0 && p2 >= 0) {
            nums1[p3--] = nums1[p1] >= nums2[p2] ? nums1[p1--] : nums2[p2--]; //选择小的数放到nums1尾部
        }
        if (p2 >= 0) { //如果nums2没有选完，需要将它放到nums1最前面
            System.arraycopy(nums2, 0, nums1, 0, p2 + 1);
        }
    }
}
