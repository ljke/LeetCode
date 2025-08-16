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
        // 不提前push!!! stack.push(node)
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
            // 对应两种情况：1. 没有右子树；2.上次遍历的节点就是右子树节点
            if (root.right == null || root.right == prev) {
                // 左右子树都入栈结束
                res.add(root.val);
                // 记录上次遍历的节点
                prev = root;
                // 跳过下次循环的左子树入栈
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
        // 不要直接改root
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
            // 条件已经限定了最近公共祖先的唯一性，不存在多次赋值的情况
            ans = node;
        }
        return lson || rson || node.val == p.val || node.val == q.val;
    }

    /**
     * 101. 对称二叉树
     * https://leetcode.cn/problems/symmetric-tree/
     * 递归做法
     * 关键在于把root抽象成2个节点
     *
     * @param root
     * @return
     */
    public boolean isSymmetric(TreeNode root) {
        return check(root, root);
    }

    public boolean check(TreeNode p, TreeNode q) {
        if (p == null && q == null) {
            return true;
        }
        if (p == null || q == null) {
            return false;
        }
        return p.val == q.val && check(p.left, q.right) && check(p.right, q.left);
    }

    /**
     * 迭代做法
     *
     * @param root
     * @return
     */
    public boolean isSymmetric2(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        queue.offer(root);
        TreeNode u, v;
        while (!queue.isEmpty()) {
            u = queue.poll();
            v = queue.poll();
            if (u == null && v == null) {
                continue;
            }
            if (u == null || v == null || (u.val != v.val)) {
                return false;
            }
            queue.offer(u.left);
            queue.offer(v.right);

            queue.offer(u.right);
            queue.offer(v.left);
        }
        return true;
    }

    /**
     * 104. 二叉树的最大深度
     * https://leetcode.cn/problems/maximum-depth-of-binary-tree/description/
     *
     * BFS 层序遍历
     *
     * @param root
     * @return
     */
    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int depth = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
            depth++;
        }
        return depth;
    }

    /**
     * 226. 翻转二叉树
     * https://leetcode.cn/problems/invert-binary-tree/description/
     *
     * @param root
     * @return
     */
    public TreeNode invertTree(TreeNode root) {
        if (root == null) {
            return null;
        }
        TreeNode left = invertTree(root.left);
        TreeNode right = invertTree(root.right);
        root.left = right;
        root.right = left;
        return root;
    }

    int res = 1;

    /**
     * 543. 二叉树的直径
     * https://leetcode.cn/problems/diameter-of-binary-tree/description/
     *
     * @param root
     * @return
     */
    public int diameterOfBinaryTree(TreeNode root) {
        // 遍历树的深度
        depth(root);
        // ans是节点数，-1是边数
        return res - 1;
    }

    public int depth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = depth(root.left);
        int right = depth(root.right);
        // 遍历过程中保存最大值
        res = Math.max(res, left + right + 1);
        return Math.max(left, right) + 1;
    }

    /**
     * 108. 将有序数组转换为二叉搜索树
     * https://leetcode.cn/problems/convert-sorted-array-to-binary-search-tree/description/
     * 类似于二分的转换
     *
     * @param nums
     * @return
     */
    public TreeNode sortedArrayToBST(int[] nums) {
        TreeNode node = recur(nums, 0, nums.length - 1);
        return node;
    }

    public TreeNode recur(int[] nums, int begin, int end) {
        if (begin == end) {
            return new TreeNode(nums[begin]);
        } else if (begin > end) {
            return null;
        }
        int mid = begin + (end - begin) / 2;
        // mid作为父节点，排除
        TreeNode left = recur(nums, begin, mid - 1);
        TreeNode right = recur(nums, mid + 1, end);
        return new TreeNode(nums[mid], left, right);
    }

    /**
     * 230. 二叉搜索树中第 K 小的元素
     * https://leetcode.cn/problems/kth-smallest-element-in-a-bst/description/
     * 中序遍历k次
     *
     * @param root
     * @param k
     * @return
     */
    public int kthSmallest(TreeNode root, int k) {
        Stack<TreeNode> stack = new Stack<>();
        while (root != null || !stack.isEmpty()) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            k--;
            if (k == 0) {
                break;
            }
            root = root.right;
        }
        return root.val;
    }


    /**
     * 199. 二叉树的右视图
     * https://leetcode.cn/problems/binary-tree-right-side-view/description/
     *
     * @param root
     * @return
     */
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        dfs(root, ans, 1);
        return ans;
    }

    public void dfs(TreeNode root, List<Integer> ans, int depth) {
        if (root == null) {
            return;
        }
        if (depth > ans.size()) {
            // 记录每一层首次遍历到的节点
            ans.add(root.val);
        }
        // 先遍历右子树
        dfs(root.right, ans, depth + 1);
        dfs(root.left, ans, depth + 1);
    }
}