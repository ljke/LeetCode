package array;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * 下一个排列系列题目
 * https://leetcode-cn.com/problems/next-permutation/
 */
public class NextPermutation {
    /**
     * 31. 下一个排列
     * https://leetcode-cn.com/problems/next-permutation/
     * @param nums
     */
    public static void nextPermutation(int[] nums) {
        int len = nums.length;
        if (len <= 1) {
            return;
        }
        //step 1: 从后往前，寻找第一个违反升序的元素partitionNumber
        int p = len - 2;
        while (p >= 0 && nums[p] >= nums[p + 1]) {
            p--;
        }
        if (p == -1) { //特殊情况，说明当前序列已经是最大序列，反转整个数组，变为最小序列
            reverse(nums, 0, len - 1);
            return;
        }
        //step 2：从后往前，寻找第一个大于partitionNumber的元素changeNumber
        int c = len - 1;
        while (c > 0 && nums[c] <= nums[p]) {
            c--;
        }
        //step 3：交换partitionNumber和changeNumber，此时后面的序列依然满足从前往后降序
        swap(nums, p, c);
        //step 4：反转后面的序列为从前往后升序，因为这样的排列是最小的
        reverse(nums, p + 1, len - 1);
    }

    /**
     * 交换数组中的两个数
     *
     * @param nums
     * @param i
     * @param j
     */
    public static void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

    /**
     * 反转数组元素，包含i和j
     *
     * @param nums
     * @param i
     * @param j
     */
    public static void reverse(int[] nums, int i, int j) {
        while (i < j) {
            swap(nums, i, j);
            i++;
            j--;
        }
    }

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
        // 从后往前遍历，记录后面比当前大的数，使用单调栈
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
