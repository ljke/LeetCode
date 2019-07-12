package array;

import java.util.ArrayList;
import java.util.List;

/**
 * 1030. 距离顺序排列矩阵单元格
 * https://leetcode-cn.com/problems/matrix-cells-in-distance-order/
 */
public class AllCellsDistOrder {
    public static int[][] allCellsDistOrder(int R, int C, int r0, int c0) {
        //初始化所有cell
        List<int[]> arr = new ArrayList<>(R * C);
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                arr.add(new int[]{i, j});
            }
        }
        //自定义comparator，比较曼哈顿距离
        //将所有cell按照距离进行排序
        arr.sort((o1, o2) -> {
            int dist1 = Math.abs(o1[0] - r0) + Math.abs(o1[1] - c0);
            int dist2 = Math.abs(o2[0] - r0) + Math.abs(o2[1] - c0);
            return dist1 - dist2;
        });
        int[][] result = new int[R * C][2];
        return arr.toArray(result);
    }
}
