package array;

import java.util.Arrays;

/**
 * 利用数组的有序性简化计算
 */
public class Orderliness {

    /**
     * 2294. 划分数组使最大差为 K
     * https://leetcode.cn/problems/partition-array-such-that-maximum-difference-is-k/description/
     *
     * @param nums
     * @param k
     * @return
     */
    public int partitionArray(int[] nums, int k) {
        // 排序 遍历只需要考虑划分区间内差值小于k
        Arrays.sort(nums);
        int count = 1;
        int min = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if ((nums[i] - min) > k) {
                count++;
                min = nums[i];
            }
        }
        return count;
    }


    /**
     * 2966. 划分数组并满足最大差限制
     * https://leetcode.cn/problems/divide-array-into-arrays-with-max-difference/description/
     *
     * @param nums
     * @param k
     * @return
     */
    public int[][] divideArray(int[] nums, int k) {
        // 贪心 先排序 将最接近的数分成一组
        Arrays.sort(nums);
        int n = nums.length;
        int[][] res = new int[n / 3][3];

        for (int i = 0; i < n / 3; i++) {
            if (nums[i * 3 + 2] - nums[i * 3] > k) {
                return new int[0][0];
            }
            res[i][0] = nums[i * 3];
            res[i][1] = nums[i * 3 + 1];
            res[i][2] = nums[i * 3 + 2];
        }
        return res;
    }

}
