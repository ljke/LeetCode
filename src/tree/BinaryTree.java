/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2021 All Rights Reserved.
 */
package tree;

import java.util.*;

/**
 * @author linjie
 * @version : BinaryTree.java, v 0.1 2021年07月30日 12:23 上午 linjie Exp $
 */
public class BinaryTree {
    /**
     * Definition for a binary tree node.
     */
    class TreeNode {
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
     * 先序遍历迭代形式
     *
     * @param root
     * @return
     */
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) {
            return res;
        }

        Stack<TreeNode> stack = new Stack<>();
        TreeNode node = root;
        // 对应下面的两种处理，一种是遍历到最左节点，另一种是从栈中取一个数
        while (node != null || !stack.isEmpty()) {
            while (node != null) {
                res.add(node.val);
                stack.push(node);
                node = node.left;
            }
            node = stack.pop();
            node = node.right;
        }
        return res;
    }


    /**
     * 中序遍历递归形式
     *
     * @param root
     * @return
     */
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        inorder(root, res);
        return res;
    }

    public void inorder(TreeNode node, List<Integer> res) {
        if (node == null) {
            return;
        }
        inorder(node.left, res);
        res.add(node.val);
        inorder(node.right, res);
    }

    /**
     * 中序遍历迭代形式
     *
     * @param root
     * @return
     */
    public List<Integer> inorderTraversal1(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) {
            return res;
        }

        Stack<TreeNode> stack = new Stack<>();
        TreeNode node = root;
        while (!stack.isEmpty() || node != null) {
            while (node != null) {
                stack.push(node);
                node = node.left;
            }
            node = stack.pop();
            res.add(node.val);
            node = node.right;
        }
        return res;
    }


    /**
     * 中序遍历
     * Morris遍历
     *
     * @param root
     * @return
     */
    public List<Integer> inorderTraversal2(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) {
            return res;
        }

        TreeNode cur = root;
        TreeNode mostRight = null;
        while (cur != null) {
            mostRight = cur.left;
            if (mostRight != null) {
                // 有左子树，进行处理
                while (mostRight.right != null && mostRight.right != cur) {
                    mostRight = mostRight.right;
                }
                if (mostRight.right == null) {
                    // 对应第一种退出条件mostRight.right != null
                    // 把当前节点挂到左子树下面
                    mostRight.right = cur;
                    cur = cur.left;
                    continue;
                } else {
                    // 对应第二种退出条件mostRight.right != cur
                    // 还原
                    mostRight.right = null;
                }
            }
            // 往后处理
            res.add(cur.val);
            cur = cur.right;
        }
        return res;
    }


    /**
     * 后序遍历迭代形式
     * 标准做法
     *
     * @param root
     * @return
     */
    public List<Integer> postorderTraversal1(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }
        Stack<TreeNode> stack = new Stack<>();
        List<Integer> res = new ArrayList<>();
        // 记录上一个处理节点，用于区分两次压栈
        TreeNode prev = null;
        while (!stack.isEmpty() || root != null) {
            while (root != null) {
                // 左子树入栈
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            if (root.right == null || root.right == prev) {
                // 出栈结束
                res.add(root.val);
                prev = root;
                root = null;
            } else {
                // 右子树入栈
                stack.push(root);
                root = root.right;
            }
        }
        return res;
    }

    /**
     * 后序遍历迭代形式
     * 自己YY做法
     *
     * @param root
     * @return
     */
    public List<Integer> postorderTraversal2(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }
        Stack<TreeNode> stack = new Stack<>();
        List<Integer> res = new ArrayList<>();
        stack.push(root);
        while(!stack.isEmpty()) {
            TreeNode node = stack.pop();
            res.add(node.val);
            if (node.left != null) {
                stack.push(node.left);
            }
            if (node.right != null) {
                stack.push(node.right);
            }
        }
        Collections.reverse(res);
        return res;
    }

    /**
     * 层次遍历，广度优先搜索
     *
     * @param root
     * @return
     */
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while(!queue.isEmpty()) {
            List<Integer> level = new ArrayList<>();
            // 记录当前层节点数量
            int currentSize = queue.size();
            for (int i = 0; i < currentSize; i++) {
                TreeNode node = queue.poll();
                level.add(node.val);
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
            res.add(level);
        }
        return res;
    }


    /**
     * 二叉树展开为链表
     * https://leetcode.cn/problems/flatten-binary-tree-to-linked-list/submissions/
     * 非原地做法，先展开后修改指向
     *
     * @param root
     */
    public void flatten1(TreeNode root) {
        if (root == null) {
            return;
        }
        // 先进行先序遍历
        List<TreeNode> list = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode node = root;
        while (!stack.isEmpty() || node != null) {
            while (node != null) {
                list.add(node);
                stack.push(node);
                node = node.left;
            }
            node = stack.pop();
            node = node.right;
        }
        // 再修改指向
        int size = list.size();
        for (int i = 1; i < size; i++) {
            TreeNode prev = list.get(i - 1);
            TreeNode cur = list.get(i);
            prev.left = null;
            prev.right= cur;
        }
    }

    /**
     * 原地做法，修改指针指向
     *
     * @param root
     */
    public void flatten2(TreeNode root) {
        TreeNode cur = root;
        while (cur != null) {
            // 调整左子树
            if (cur.left != null) {
                TreeNode mostRight = cur.left;
                // 记录下一个位置
                TreeNode next = cur.left;
                while(mostRight.right != null) {
                    mostRight = mostRight.right;
                }
                // 右子树挂到最右节点
                mostRight.right = cur.right;
                // 左子树转到右子树
                cur.left = null;
                cur.right = next;
            }
            cur = cur.right;
        }
    }

    private TreeNode ans = null;

    /**
     * 236. 二叉树的最近公共祖先
     * https://leetcode.cn/problems/lowest-common-ancestor-of-a-binary-tree/
     *
     * @param root
     * @param p
     * @param q
     * @return
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        dfs(root, p, q);
        return ans;
    }

    public boolean dfs (TreeNode node, TreeNode p, TreeNode q) {
        if (node == null) {
            return false;
        }
        boolean lson = dfs(node.left, p, q);
        boolean rson = dfs(node.right, p, q);
        // 判断条件
        if ((lson && rson) || ((node.val == p.val || node.val == q.val) && (lson || rson))) {
            ans = node;
        }
        return lson || rson || node.val == p.val || node.val == q.val;
    }
}