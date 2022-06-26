/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package tree;

/**
 * 124. 二叉树中的最大路径和
 * https://leetcode.cn/problems/binary-tree-maximum-path-sum/
 *
 * @author linjie
 * @version : MaxPathSum.java, v 0.1 2022年06月26日 3:40 下午 linjie Exp $
 */
public class MaxPathSum {

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

    public int maxV = Integer.MIN_VALUE;

    public int maxPathSum(TreeNode root) {
        dfs(root);
        return maxV;
    }

    /**
     * 返回已当前节点为顶点的最大路径和
     */
    public int dfs(TreeNode root) {
        // 返回条件
        if (root == null) {
            return 0;
        }
        // 计算左右节点值，只有正数才可以算入
        int left = Math.max(dfs(root.left), 0);
        int right = Math.max(dfs(root.right), 0);
        // 更新最大值，有2种情况
        // 1. 经过当前节点的路径
        // 2. 以当前节点为顶点的路径
        // 这里简化为只考虑第1种
        maxV = Math.max(maxV, root.val + left + right);
        // 返回结果
        return root.val + Math.max(left, right);
    }
}