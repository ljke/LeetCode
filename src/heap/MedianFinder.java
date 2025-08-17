package heap;

import java.util.PriorityQueue;

/**
 * 295. 数据流的中位数
 * https://leetcode.cn/problems/find-median-from-data-stream/description/
 *
 * @author : ljke
 * @date : Created in 17:07 2025/8/17
 */
public class MedianFinder {
    // 小于等于中位数 大顶堆
    PriorityQueue<Integer> minQueue;

    // 大于中位数 小顶堆
    PriorityQueue<Integer> maxQueue;

    public MedianFinder() {
        // 偶数:minQueue.size == maxQueue.size
        // 奇数:minQueue.size == maxQueue.size + 1
        minQueue = new PriorityQueue<>((a, b) -> (b - a));
        maxQueue = new PriorityQueue<>((a, b) -> (a - b));
    }

    public void addNum(int num) {
        if (minQueue.isEmpty() || num <= minQueue.peek()) {
            minQueue.offer(num);
            if (minQueue.size() > maxQueue.size() + 1) {
                // 调整位置
                maxQueue.offer(minQueue.poll());
            }
        } else {
            maxQueue.offer(num);
            if (maxQueue.size() > minQueue.size()) {
                // 调整位置
                minQueue.offer(maxQueue.poll());
            }
        }
    }

    public double findMedian() {
        if (minQueue.size() > maxQueue.size()) {
            return minQueue.peek();
        } else {
            return (minQueue.peek() + maxQueue.peek()) / 2.0;
        }
    }
}
