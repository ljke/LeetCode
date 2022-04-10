package tree;

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
     * @param n
     * @return
     */
    public int printDifferentBST(int n) {
        return 0;
    }

}
