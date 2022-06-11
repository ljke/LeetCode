/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package search;

/**
 * @author linjie
 * @version : MatrixSearch.java, v 0.1 2022年06月04日 11:32 上午 linjie Exp $
 */
public class MatrixSearch {

    /**
     * 搜索二维矩阵
     * 每行中的整数从左到右按升序排列。
     * 每行的第一个整数大于前一行的最后一个整数。
     * https://leetcode.cn/problems/search-a-2d-matrix/
     *
     * @param matrix
     * @param target
     * @return
     */
    public boolean searchMatrixI(int[][] matrix, int target) {
        // 使用一次二分查找，将二维转换为一维
        int m = matrix.length, n = matrix[0].length;
        int low = 0, high = m * n - 1;
        while (low <= high) {
            int mid = (high - low) / 2 + low;
            int x = mid / n;
            int y = mid % n;
            if (matrix[x][y] == target) {
                return true;
            } else if (matrix[x][y] > target) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return false;
    }


    /**
     * 搜索二维矩阵 II
     * 每行的元素从左到右升序排列。
     * 每列的元素从上到下升序排列。
     * https://leetcode.cn/problems/search-a-2d-matrix-ii/
     *
     * @param matrix
     * @param target
     * @return
     */
    public boolean searchMatrixII(int[][] matrix, int target) {
        int m = matrix.length, n = matrix[0].length;
        // 从右上角开始遍历
        int i = 0, j = (n - 1);
        while ((i <= (m - 1)) && (j >= 0)) {
            if (matrix[i][j] == target) {
                return true;
            } else if (matrix[i][j] > target) {
                // 当前列全大于，排除
                j--;
            } else if (matrix[i][j] < target) {
                // 当前行全小于，排除
                i++;
            }
        }
        return false;
    }
}