package sort;

public class BucketSort {
    /**
     * 41. 缺失的第一个正数
     * https://leetcode-cn.com/problems/first-missing-positive/
     * 使用桶排序的思路，即 “一个萝卜一个坑”
     * 索引为 i 的位置上应该存放的数字是 i + 1
     */
    public int firstMissingPositive(int[] nums) {
        int len = nums.length;
        //桶排序调整位置
        for (int i = 0; i < len; i++) {
            while (nums[i] != (i + 1)) {
                //数字不属于范围时跳过
                //注意[1, 1]这种情况，换过来的数如果是相等的也退出
                if (nums[i] <= 0 || nums[i] > len || nums[i] == nums[nums[i] - 1]) {
                    break;
                }
                //将数字交换到合适的位置i <-> (nums[i] - 1)
                int tmp = nums[nums[i] - 1];
                nums[nums[i] - 1] = nums[i];
                nums[i] = tmp;
            }
        }
        //遍历一遍找出缺失的整数
        for (int i = 0; i < len; i++) {
            if (nums[i] != (i + 1)) {
                return i + 1;
            }
        }
        return len + 1;
    }
}
