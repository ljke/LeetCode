package tree;

import java.util.ArrayList;
import java.util.List;

/**
 * @author linjie
 * @date 2020/07/21
 */
public class BinarySearchTree {
    /**
     * Definition for a binary tree node.
     */
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    /**
     * 98. 验证二叉搜索树
     * https://leetcode.cn/problems/validate-binary-search-tree/
     *
     * @param root
     * @return
     */
    public boolean isValidBST(TreeNode root) {
        return recur(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    public boolean recur(TreeNode node, long low, long high) {
        if (node == null) {
            return true;
        }
        if (node.val <= low || node.val >= high) {
            return false;
        }
        // 递归处理左右子树
        return recur(node.left, low, node.val) && recur(node.right, node.val, high);
    }

    /**
     * 96. 不同的二叉搜索树
     * G(n):长度为n的序列能构成的数量
     * F(i,n):以i为根，序列长度为n的数量
     * G(n) = ∑F(i, n)
     * G(0) = 1, G(1) = 1
     * F(i,n) = G(i−1) * G(n−i)
     * G(n) = ∑(G(i−1) * G(n−i))
     * @param n
     * @return
     */
    public int numOfDifferentBST(int n) {
        int[] dp = new int[n+1];
        dp[0] = 1;
        dp[1] = 1;

        for (int i = 2; i <= n; i++) {
            for (int j = 1; j <= i; j++) {
                dp[i] += dp[j - 1] * dp[i - j];
            }
        }
        return dp[n];
    }

    /**
     * 95. 不同的二叉搜索树 II
     * https://leetcode.cn/problems/unique-binary-search-trees-ii/submissions/
     *
     * @param n
     * @return
     */
    public List<TreeNode> generateTrees(int n) {
        if (n == 0) {
            return new ArrayList<>();
        }
        return recur(1, n);
    }

    public List<TreeNode> recur(int start, int end) {
        List<TreeNode> allTree = new ArrayList<>();
        // 循环退出条件
        if (start > end) {
            allTree.add(null);
            return allTree;
        }
        // 遍历每个节点作为父节点
        for (int i = start; i <= end; i++) {
            // 递归求左右子树
            List<TreeNode> leftTree = recur(start, i - 1);
            List<TreeNode> rightTree = recur(i + 1, end);
            for (TreeNode left : leftTree) {
                for (TreeNode right : rightTree) {
                    TreeNode node = new TreeNode();
                    node.left = left;
                    node.right = right;
                    node.val = i;
                    allTree.add(node);
                }
            }
        }
        return allTree;
    }
}
