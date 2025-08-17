package heap;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * @author : ljke
 * @date : Created in 17:08 2025/8/17
 */
public class HeapSolution {
    /**
     * 215. 数组中的第K个最大元素
     * https://leetcode.cn/problems/kth-largest-element-in-an-array/
     *
     * 找第K大数的另类解法，使用大小为K的小顶堆，堆顶元素即为第K大数
     *
     * @param nums
     * @param k
     * @return
     */
    public static int findKthLargest(int[] nums, int k) {
        Queue<Integer> q = new PriorityQueue<>();
        for (int x : nums) {
            if (q.size() < k) {
                q.offer(x);
            } else {
                int top = q.peek();
                if (x > top) {
                    q.poll();
                    q.offer(x);
                }
            }
        }
        return q.peek();
    }

    /**
     * 347. 前 K 个高频元素
     * https://leetcode.cn/problems/top-k-frequent-elements/description/
     * 同上，堆排序
     *
     * @param nums
     * @param k
     * @return
     */
    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int n : nums) {
            map.put(n, map.getOrDefault(n, 0) + 1);
        }
        PriorityQueue<int[]> heap = new PriorityQueue<>((a, b) -> {
            return a[1] - b[1];
        });

        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            Integer num = entry.getKey();
            Integer count = entry.getValue();
            if (heap.size() < k) {
                heap.offer(new int[]{num, count});
            } else {
                if (count > heap.peek()[1]) {
                    heap.poll();
                    heap.offer(new int[]{num, count});
                }
            }
        }

        int[] res = new int[k];
        for (int i = 0; i < k; i++) {
            res[i] = heap.poll()[0];
        }
        return res;
    }
}
