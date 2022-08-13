/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package tree;

import java.util.*;

/**
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

    /**
     * 112. 路径总和
     * https://leetcode.cn/problems/path-sum/
     * 递归方法
     * 深度优先搜索
     *
     * @param root
     * @param targetSum
     * @return
     */
    public boolean hasPathSum1(TreeNode root, int targetSum) {
        if (root == null) {
            return false;
        }
        if (root.left == null && root.right == null) {
            return root.val == targetSum;
        }
        return hasPathSum1(root.left, targetSum - root.val) || hasPathSum1(root.right, targetSum - root.val);
    }

    /**
     * 使用队列
     * 广度优先搜索
     *
     * @param root
     * @param targetSum
     * @return
     */
    public boolean hasPathSum2(TreeNode root, int targetSum) {
        if (root == null) {
            return false;
        }
        // 分别保存当前节点和当前路径和
        Queue<TreeNode> queue = new LinkedList<>();
        Queue<Integer> sum = new LinkedList<>();
        queue.offer(root);
        sum.offer(root.val);
        while (!queue.isEmpty()) {
            TreeNode now = queue.poll();
            Integer i = sum.poll();
            // 到达叶子节点进行判断
            if (now.left == null && now.right == null) {
                if (i == targetSum) {
                    return true;
                }
                continue;
            }
            if (now.left != null) {
                queue.offer(now.left);
                sum.offer(i + now.left.val);
            }
            if (now.right != null) {
                queue.offer(now.right);
                sum.offer(i + now.right.val);
            }
        }
        return false;
    }

    /**
     * 113. 路径总和 II
     * https://leetcode.cn/problems/path-sum-ii/
     * 深度优先算法
     *
     * @param root
     * @param targetSum
     * @return
     */
    public List<List<Integer>> pathSum1(TreeNode root, int targetSum) {
        List<List<Integer>> ret = new LinkedList<>();
        List<Integer> path = new LinkedList<>();
        dfs(root, targetSum, ret, path);
        return ret;
    }

    public void dfs(TreeNode node, int targetSum, List<List<Integer>> ret, List<Integer> path) {
        if (node == null) {
            return;
        }
        // 退出条件
        path.add(node.val);
        targetSum -= node.val;
        if (node.left == null && node.right == null && targetSum == 0) {
            ret.add(new LinkedList<>(path));
        }
        dfs(node.left, targetSum, ret, path);
        dfs(node.right, targetSum, ret, path);
        // 回溯
        path.remove(path.size() - 1);
    }

    // 保存节点的前驱
    Map<TreeNode, TreeNode> map = new HashMap<>();

    /**
     * 广度优先算法
     *
     * @param root
     * @param targetSum
     * @return
     */
    public List<List<Integer>> pathSum2(TreeNode root, int targetSum) {
        List<List<Integer>> ret = new LinkedList<>();
        if (root == null) {
            return ret;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        Queue<Integer> sum = new LinkedList<>();
        queue.offer(root);
        sum.offer(root.val);
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            Integer i = sum.poll();
            if (node.left == null && node.right == null) {
                if (i == targetSum) {
                    getPath(node, ret);
                }
            } else {
                if (node.left != null) {
                    map.put(node.left, node);
                    queue.offer(node.left);
                    sum.offer(i + node.left.val);
                }
                if (node.right != null) {
                    map.put(node.right, node);
                    queue.offer(node.right);
                    sum.offer(i + node.right.val);
                }
            }
        }
        return ret;
    }

    public void getPath(TreeNode node, List<List<Integer>> ret) {
        List<Integer> tmp = new ArrayList<>();
        while (node != null) {
            tmp.add(node.val);
            node = map.get(node);
        }
        Collections.reverse(tmp);
        ret.add(tmp);
    }

    public int maxV = Integer.MIN_VALUE;

    /**
     *  124. 二叉树中的最大路径和
     *  https://leetcode.cn/problems/binary-tree-maximum-path-sum/
     * @param root
     * @return
     */
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