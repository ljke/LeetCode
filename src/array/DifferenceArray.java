package array;

import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;

/**
 * 差分数组相关
 */
public class DifferenceArray {


    /**
     * 3355. 零数组变换 I
     * https://leetcode.cn/problems/zero-array-transformation-i/description/
     *
     * @param nums
     * @param queries
     * @return
     */
    public boolean isZeroArray(int[] nums, int[][] queries) {
        // 构建差分数组
        int[] diff = new int[nums.length + 1];
        for (int[] q : queries) {
            int i = q[0];
            int j = q[1];
            diff[i] += 1;
            diff[j + 1] -= 1;
        }
        // 还原差分数组
        int[] operateCount = new int[nums.length];
        operateCount[0] = diff[0];
        for (int i = 1; i < nums.length; i++) {
            operateCount[i] = operateCount[i - 1] + diff[i];
        }
        // 结果判断
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > operateCount[i]) {
                return false;
            }
        }
        return true;
    }

    /**
     * 3356. 零数组变换 II
     * https://leetcode.cn/problems/zero-array-transformation-ii/description/
     * 差分数组 + 二分查找（由于往后的情况一定满足）
     *
     * @param nums
     * @param queries
     * @return
     */
    public int minZeroArray(int[] nums, int[][] queries) {
        int left = 0, right = queries.length;

        if (!check(nums, queries, right)) {
            return -1;
        }

        while (left < right) {
            int mid = left + ((right - left) >> 1);
            if (check(nums, queries, mid)) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    public boolean check(int[] nums, int[][] queries, int index) {
        int[] diff = new int[nums.length + 1];
        for (int k = 0; k < index; k++) {
            int[] q = queries[k];
            int begin = q[0];
            int end = q[1];
            int value = q[2];
            diff[begin] += value;
            diff[end + 1] -= value;
        }
        int[] operateCount = new int[nums.length];
        operateCount[0] = diff[0];
        for (int k = 1; k < nums.length; k++) {
            operateCount[k] = operateCount[k - 1] + diff[k];
        }
        for (int k = 0; k < nums.length; k++) {
            if (nums[k] > operateCount[k]) {
                return false;
            }
        }
        return true;
    }

    /**
     * 3362. 零数组变换 III
     * https://leetcode.cn/problems/zero-array-transformation-iii/description/
     * 贪心 + 优先级队列
     *
     * @param nums
     * @param queries
     * @return
     */
    public int maxRemoval(int[] nums, int[][] queries) {
        // queries按照左端点升序
        Arrays.sort(queries, (a, b) -> a[0] - b[0]);
        // 优先级队列保存queries右端点降序
        PriorityQueue<Integer> heap = new PriorityQueue<>(Collections.reverseOrder());

        // 当前操作数
        int operation = 0;
        // 差分数组用于排除已选取的小于右端点的queries
        int[] deltaArray = new int[nums.length + 1];
        for(int i = 0, j = 0; i < nums.length; i++) {
            // 使用差分数组更新当前操作数
            operation = operation + deltaArray[i];
            // 取出所有左端点包含i的queries, 加入优先级队列
            while (j < queries.length && queries[j][0] == i) {
                heap.offer(queries[j][1]);
                j++;
            }
            // 从优先级队列取出queries
            while(operation < nums[i] && !heap.isEmpty() && heap.peek() >= i) {
                // 使用queries更新当前操作数
                operation++;
                // 更新差分数组
                deltaArray[heap.poll() + 1] -= 1;
            }
            // 无法满足
            if (operation < nums[i]) {
                return -1;
            }
        }
        // 剩余的queries就是多余的
        return heap.size();
    }
    

}
