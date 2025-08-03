package array;

/**
 * @author : ljke
 * @date : Created in 15:07 2025/7/17
 */
public class Rotate {

    /**
     * 189. 轮转数组
     * https://leetcode.cn/problems/rotate-array/description/
     *
     * @param nums
     * @param k
     */
    public void rotate(int[] nums, int k) {
        // 整个数组反转
        reverse(nums, 0, nums.length - 1);
        // 区间反转 得到的就是轮转后的结果
        k = k % nums.length;
        reverse(nums, 0, k - 1);
        reverse(nums, k, nums.length - 1);
    }

    public void reverse(int[] nums, int i, int j) {
        while (i < j) {
            int tmp = nums[i];
            nums[i] = nums[j];
            nums[j] = tmp;
            i++;
            j--;
        }
    }

}
