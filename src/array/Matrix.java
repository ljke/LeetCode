package array;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : ljke
 * @description : 矩阵操作相关
 * @date : Created in 16:26 2025/7/17
 */
public class Matrix {

    /**
     * 73. 矩阵置零
     * https://leetcode.cn/problems/set-matrix-zeroes/description/
     * 复用第1行/第1列记录当前列/行是否置0，只需要O(1)的额外空间
     *
     * @param matrix
     */
    public void setZeroes(int[][] matrix) {
        int rowSize = matrix.length;
        int colSize = matrix[0].length;
        // 由于第一行和第一列被修改，所以提前使用flag保存原本是否包含0
        boolean flagRow = false;
        boolean flagCol = false;
        for (int i = 0; i < rowSize; i++) {
            if (matrix[i][0] == 0) {
                flagCol = true;
                break;
            }
        }

        for (int i = 0; i < colSize; i++) {
            if (matrix[0][i] == 0) {
                flagRow = true;
                break;
            }
        }

        // 使用第1行/第1列记录当前列/行是否置0
        // Note：注意这里遍历要从第2行/第2列开始，因为第1行/第1列已经不是原先的数了
        for (int i = 1; i < rowSize; i++) {
            for (int j = 1; j < colSize; j++) {
                if (matrix[i][j] == 0) {
                    matrix[i][0] = 0;
                    matrix[0][j] = 0;
                }
            }
        }

        for (int i = 1; i < rowSize; i++) {
            for (int j = 1; j < colSize; j++) {
                if (matrix[i][0] == 0 || matrix[0][j] == 0) {
                    matrix[i][j] = 0;
                }
            }
        }

        // 使用flag更新第1行/第1列
        if (flagRow) {
            for (int i = 0; i < colSize; i++) {
                matrix[0][i] = 0;
            }
        }
        if (flagCol) {
            for (int i = 0; i < rowSize; i++) {
                matrix[i][0] = 0;
            }
        }
    }


    /**
     * 54. 螺旋矩阵
     * https://leetcode.cn/problems/spiral-matrix/description/
     * 按层遍历，遍历顺序：
     * [[1, 1, 1, 1, 1, 1, 1],
     *  [4, 5, 5, 5, 5, 5, 2],
     *  [4, 8, 9, 9, 9, 6, 2],
     *  [4, 8, 7, 7, 7, 6, 2],
     *  [4, 3, 3, 3, 3, 3, 2]]
     *
     *
     * @param matrix
     * @return
     */
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> result = new ArrayList<>();
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return result;
        }
        int rows = matrix.length;
        int cols = matrix[0].length;
        int left = 0, right = cols - 1, top = 0, bottom = rows - 1;

        while (left <= right && top <= bottom) {
            for (int j = left; j <= right; j++) {
                result.add(matrix[top][j]);
            }
            for (int i = top + 1; i <= bottom; i++) {
                result.add(matrix[i][right]);
            }
            // 此处处理最后一层只有单行/单列的情况
            if (left < right && top < bottom) {
                for (int j = right - 1; j > left; j--) {
                    result.add(matrix[bottom][j]);
                }
                for (int i = bottom; i > top; i--) {
                    result.add(matrix[i][left]);
                }
            }
            left++;
            right--;
            top++;
            bottom--;
        }
        return result;
    }

    /**
     * 48. 旋转图像
     * https://leetcode.cn/problems/rotate-image/description/
     * 顺时针旋转 90 度 = 水平轴翻转 + 主对角线翻转
     *
     * @param matrix
     */
    public void rotate(int[][] matrix) {
        int n = matrix.length;
        // 水平轴翻转
        for (int i = 0; i < n / 2; i++) {
            for (int j = 0; j < n; j++) {
                int tmp = matrix[i][j];
                matrix[i][j] = matrix[n - 1 - i][j];
                matrix[n - 1 - i][j] = tmp;
            }
        }
        // 主对角线翻转
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                int tmp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = tmp;
            }
        }
    }

}
