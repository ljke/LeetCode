/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2021 All Rights Reserved.
 */
package array;

/**
 * 前缀和相关
 *
 * @author linjie
 * @version : PreSum.java, v 0.1 2021年07月21日 1:43 上午 linjie Exp $
 */
public class PreSum {

    /**
     * 303. 区域和检索 - 数组不可变
     * https://leetcode.cn/problems/range-sum-query-immutable/description/
     * 前缀和简化求和运算
     * sums 的长度设为 n+1 的目的是为了方便计算 sumRange(i,j)，不需要对 i=0 的情况特殊处理
     */
    public class NumArray {
        private int[] preSum;

        public NumArray(int[] nums) {
            // preSum[i] 表示 0～(i - 1) 元素的和
            preSum = new int[nums.length + 1];
            for (int i = 1; i <= nums.length; i++) {
                preSum[i] = preSum[i - 1] + nums[i - 1];
            }
        }

        public int sumRange(int left, int right) {
            // preSum[i] - preSum[j] = (j, i]数组的和
            // (left, right + 1)
            // (i - 1, j)
            return preSum[right + 1] - preSum[left];
        }
    }

    /**
     * 304. 二维区域和检索 - 矩阵不可变
     * https://leetcode.cn/problems/range-sum-query-2d-immutable/description/
     * 二维数组形式前缀和
     */
    public class NumMatrix {

        private int[][] preSum;

        public NumMatrix(int[][] matrix) {
            // [0][0] ~ [i - 1][j - 1]的面积之和
            preSum = new int[matrix.length + 1][matrix[0].length + 1];
            for (int i = 1; i <= matrix.length; i++) {
                for (int j = 1; j <= matrix[0].length; j++) {
                    preSum[i][j] = preSum[i][j - 1] + preSum[i - 1][j] - preSum[i - 1][j - 1] + matrix[i - 1][j - 1];
                }
            }
        }

        public int sumRegion(int row1, int col1, int row2, int col2) {
            // 面积计算
            return preSum[row2 + 1][col2 + 1] - preSum[row2 + 1][col1] - preSum[row1][col2 + 1] + preSum[row1][col1];
        }
    }

    /**
     * 560. 和为K的子数组
     * https://leetcode-cn.com/problems/subarray-sum-equals-k/
     * 使用前缀和 PreSum[i] = 前i个数之和
     * PreSum[i] - PreSum[j] = (j, i]数组的和
     *
     * @param nums
     * @param k
     * @return
     */
    public int subArraySum(int[] nums, int k) {
        //提前计算前缀和
        int n = nums.length;
        int[] preSum = new int[n + 1];
        preSum[0] = 0;
        for (int i = 0; i < n; i++) {
            preSum[i + 1] = preSum[i] + nums[i];
        }
        int ans = 0;
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j < i; j++) {
                if (preSum[i] - preSum[j] == k) {
                    ans++;
                }
            }
        }
        return ans;
    }
}