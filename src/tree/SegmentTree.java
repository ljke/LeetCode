package tree;

/**
 * 线段树解法
 * 307. 区域和检索 - 数组可修改
 * https://leetcode.cn/problems/range-sum-query-mutable/description/
 *
 * @author : ljke
 * @date : Created in 15:26 2025/8/5
 */
public class SegmentTree {

    private int[] segmentTree;

    private int n;

    public SegmentTree(int[] nums) {
        n = nums.length;
        segmentTree = new int[4 * n];
        build(nums, 0, 0, n - 1);
    }

    public void update(int index, int val) {
        update(index, val, 0, 0, n - 1);
    }

    public int sumRange(int left, int right) {
        return sumRange(left, right, 0, 0, n - 1);
    }

    // node表示当前节点下标，l..r表示当前节点下标对应的数据范围，必须对应上
    private void build(int[] nums, int node, int l, int r) {
        if (l == r) {
            segmentTree[node] = nums[l];
            return;
        }
        int m = l + (r - l) / 2;
        build(nums, node * 2 + 1, l, m);
        build(nums, node * 2 + 2, m + 1, r);
        // 递归构造树，父节点等于两个子节点相加
        segmentTree[node] = segmentTree[node * 2 + 1] + segmentTree[node * 2 + 2];
    }

    private void update(int index, int val, int node, int l, int r) {
        if (l == r) {
            segmentTree[node] = val;
            return;
        }
        int m = l + (r - l) / 2;
        if (index <= m) {
            update(index, val, node * 2 + 1, l, m);
        } else {
            update(index, val, node * 2 + 2, m + 1, r);
        }
        // pushUp
        segmentTree[node] = segmentTree[node * 2 + 1] + segmentTree[node * 2 + 2];
    }

    private int sumRange(int left, int right, int node, int l, int r) {
        if (left == l && right == r) {
            return segmentTree[node];
        }
        int m = l + (r - l) / 2;
        // 取对应部分总和，作为退出条件，left, right需要跟着变化
        if (right <= m) {
            return sumRange(left, right, node * 2 + 1, l, m);
        } else if (left > m) {
            return sumRange(left, right, node * 2 + 2, m + 1, r);
        } else {
            return sumRange(left, m, node * 2 + 1, l , m) + sumRange(m + 1, right, node * 2 + 2, m + 1, r);
        }
    }

}
