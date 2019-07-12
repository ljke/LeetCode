package array;

import static java.lang.Math.max;
import static java.lang.Math.min;

/**
 * 152. 乘积最大子序列
 * https://leetcode-cn.com/problems/maximum-product-subarray/
 */
public class MaxProduct {
    /**
     * 动态规划思想：
     * 每个新加入的数字，最大值要么是当前最大值*新数，要么是当前最小值（负数）*新数（负数），要么是当前值
     *
     * @param nums
     * @return
     */
    public static int maxProduct(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int maxValue = nums[0], minValue = nums[0], result = nums[0];
        int tmp; //使用一个临时变量保存上次的最大值，因为最大值会被覆盖
        for (int i = 1; i < nums.length; i++) {
            tmp = maxValue;
            maxValue = max(maxValue * nums[i], max(minValue * nums[i], nums[i]));
            minValue = min(tmp * nums[i], min(minValue * nums[i], nums[i]));
            if (maxValue > result) {
                result = maxValue;
            }
        }
        return result;
    }
}
