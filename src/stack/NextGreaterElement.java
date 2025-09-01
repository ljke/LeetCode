package stack;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * @author : ljke
 * @date : Created in 12:05 2025/8/26
 */
public class NextGreaterElement {
    /**
     * 496. 下一个更大元素 I
     * https://leetcode.cn/problems/next-greater-element-i/solution/
     *
     * @param nums1
     * @param nums2
     * @return
     */
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        Map<Integer, Integer> map = new HashMap<>();
        Stack<Integer> stack = new Stack<>();
        // 从后往前遍历，记录后面比当前大的数，使用单调栈 栈底 -> 栈顶 单调递减
        // 因为不需要下标，所以单调栈内保存数值
        for (int i = nums2.length - 1; i >= 0; i--) {
            int num = nums2[i];
            while (!stack.isEmpty() && num >= stack.peek()) {
                stack.pop();
            }
            map.put(num, stack.isEmpty() ? -1 : stack.peek());
            stack.push(num);
        }
        // 从map中直接得到结果
        int[] res = new int[nums1.length];
        for (int i = 0; i < nums1.length; i++) {
            res[i] = map.get(nums1[i]);
        }
        return res;
    }

    /**
     * 503. 下一个更大元素 II
     * https://leetcode.cn/problems/next-greater-element-ii/
     *
     * @param nums
     * @return
     */
    public int[] nextGreaterElements(int[] nums) {
        int n = nums.length;
        int[] res = new int[n];
        Arrays.fill(res, -1);
        // 单调递减栈，保存下标
        Stack<Integer> stack = new Stack<>();
        // 循环数组，最多只需要遍历2遍
        for (int i = 0; i < 2 * n; i++) {
            // 不满足递减的出栈，表示找到了下一个更大元素
            int num = nums[i % n];
            while (!stack.isEmpty() &&  num > nums[stack.peek()]) {
                int index = stack.pop();
                res[index] = num;
            }
            stack.push(i % n);
        }
        return res;
    }

}
