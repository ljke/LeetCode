package stack;

import java.util.Stack;

/**
 * 84. 柱状图中最大的矩形
 * https://leetcode.cn/problems/largest-rectangle-in-histogram/
 *
 * @author : ljke
 * @date : Created in 17:40 2025/8/16
 */
public class LargestRectangleArea {

    public int largestRectangleArea(int[] heights) {
        int n = heights.length;
        //当前位置左侧最近的小于其高度柱子下标
        int[] left = new int[n];
        //当前位置右侧最近的小于其高度柱子下标
        int[] right = new int[n];

        //从左往右遍历
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && heights[stack.peek()] >= heights[i]) {
                stack.pop();
            }
            left[i] = stack.isEmpty() ? -1 : stack.peek();
            stack.push(i);
        }
        //从右往左遍历
        stack = new Stack<>();
        for (int i = n - 1; i >= 0; i--) {
            while (!stack.isEmpty() && heights[stack.peek()] >= heights[i]) {
                stack.pop();
            }
            right[i] = stack.isEmpty() ? n : stack.peek();
            stack.push(i);
        }
        int ans = 0;
        for (int i = 0; i < n; i++) {
            // 需要排除left[i]和right[i]这两个位置，所以是-1
            int cur = heights[i] * (right[i] - left[i] - 1);
            ans = Math.max(ans, cur);
        }
        return ans;
    }

}
