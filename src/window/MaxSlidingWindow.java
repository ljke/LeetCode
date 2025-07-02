package window;

import java.util.Comparator;
import java.util.Deque;
import java.util.LinkedList;
import java.util.PriorityQueue;

public class MaxSlidingWindow {

    /**
     * 239. 滑动窗口最大值
     * https://leetcode.cn/problems/sliding-window-maximum/description/
     *
     * @param nums
     * @param k
     * @return
     */
    public int[] maxSlidingWindow(int[] nums, int k) {
        // 优先级队列: 动态维护最大值
        PriorityQueue<int[]> heap = new PriorityQueue<>(new Comparator<int[]>() {
            public int compare(int[] p1, int[] p2) {
                if (p1[0] != p2[0]) {
                    return p2[0] - p1[0];
                } else {
                    return p2[1] - p1[1];
                }
            }
        });

        int len = nums.length;
        int[] ans = new int[len - k + 1];

        // 首次初始化
        for (int i = 0; i < k; i++) {
            heap.offer(new int[]{nums[i], i});
        }
        ans[0] = heap.peek()[0];

        // 添加元素，并移除不在范围内的最大值
        for (int i = k; i < len; i++) {
            heap.offer(new int[]{nums[i], i});
            while (heap.peek()[1] <= (i - k)) {
                heap.poll();
            }
            ans[i - k + 1] = heap.peek()[0];
        }

        return ans;
    }

    /**
     * 单调队列写法
     *
     * @param nums
     * @param k
     * @return
     */
    public int[] maxSlidingWindow2(int[] nums, int k) {
        Deque<int[]> deque = new LinkedList<>();

        // 初始化单调队列，队首到队尾单调递减
        for (int i = 0; i < k; i++) {
            // 从队尾最小值开始比较
            // 小于当前值的元素无法成为最大值，出队
            // 大于当前值的元素可能是最大值，也可能因为超出窗口不是最大值，保留
            while (!deque.isEmpty() && nums[i] >= deque.peekLast()[1]) {
                deque.pollLast();
            }
            deque.offerLast(new int[]{i, nums[i]});
        }

        int len = nums.length;
        int[] ans = new int[len - k + 1];
        ans[0] = deque.peekFirst()[1];

        for (int i = k; i < len; i++) {
            // 出队条件：小于当前值 || 超过窗口范围
            while (!deque.isEmpty() && nums[i] >= deque.peekLast()[1]) {
                deque.pollLast();
            }
            while (!deque.isEmpty() && deque.peekFirst()[0] <= (i - k)) {
                deque.pollFirst();
            }
            deque.offerLast(new int[]{i, nums[i]});
            ans[i - k + 1] = deque.peekFirst()[1];
        }

        return ans;
    }

}
