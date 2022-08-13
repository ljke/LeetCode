/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package tree;

import java.util.HashMap;
import java.util.Map;

/**
 * @author linjie
 * @version : BuildTree.java, v 0.1 2022年07月03日 4:29 下午 linjie Exp $
 */
public class BuildTree {
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
     *
     *
     * @param preorder
     * @param inorder
     * @return
     */
    Map<Integer, Integer> indexMap = new HashMap<>();

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        int len = preorder.length;
        for(int i = 0; i < len; i++) {
            indexMap.put(inorder[i], i);
        }
        return treeRecur(preorder, 0, len - 1, inorder, 0, len - 1);
    }

    public TreeNode treeRecur(int[] preorder, int p_left, int p_right, int[] inorder, int i_left, int i_right) {
        // leftSize = 0, 所以入参p_left = p_left + 1, p_right = p_left
        if (p_left > p_right) {
            return null;
        }
        // preorder的第一个元素就是根节点
        int parent = preorder[p_left];
        // 计算左子树长度
        int leftSize = indexMap.get(parent) - i_left;
        // 创建新节点
        TreeNode node = new TreeNode(parent);
        // 递归计算左右子树，更新边界值
        node.left = treeRecur(preorder, p_left + 1,  p_left + leftSize, inorder, i_left, i_left + leftSize - 1);
        node.right = treeRecur(preorder, p_left + leftSize + 1, p_right, inorder, i_left + leftSize + 1, i_right);
        return node;
    }

}