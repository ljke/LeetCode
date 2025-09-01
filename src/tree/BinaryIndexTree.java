package tree;

/**
 *
 * 线段树解法
 * 307. 区域和检索 - 数组可修改
 * https://leetcode.cn/problems/range-sum-query-mutable/description/
 *
 * @author : ljke
 * @date : Created in 23:10 2025/8/7
 */
public class BinaryIndexTree {
    int[] sums;

    int[] nums;

    public BinaryIndexTree(int[] nums) {
        this.sums = new int[nums.length + 1];
        this.nums = nums;
        for (int i = 0; i < nums.length; i++) {
            // 初始化累加数组
            insert(i, nums[i]);
        }
    }

    // 找到x的二进制数的最后一个1所表示的二进制
    // 表示当前sums[x]的管辖区域长度
    private int lowBit(int x) {
        return x & (-x);
    }

    private void insert(int index, int val) {
        int x = index + 1;
        while (x < sums.length) {
            sums[x] = sums[x] + val;
            // sums[x + lowBit(x)] 一定包含sums[x]
            x += lowBit(x);
        }
    }

    public void update(int index, int val) {
        int x = index + 1;
        while (x < sums.length) {
            sums[x] = sums[x] - nums[index] + val;
            x += lowBit(x);
        }
        nums[index] = val;
    }

    public int sumRange(int left, int right) {
        return query(right + 1) - query(left);
    }

    private int query(int x) {
        int s = 0;
        while (x != 0) {
            s += sums[x];
            // 跳转到下一个区域管辖范围
            x -= lowBit(x);
        }
        return s;
    }
}
