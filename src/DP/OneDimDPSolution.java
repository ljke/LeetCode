package DP;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 一维动态规划相关问题
 *
 * @author : ljke
 * @date : Created in 16:02 2025/8/24
 */
public class OneDimDPSolution {

    /**
     *
     * 198. 打家劫舍
     * https://leetcode.cn/problems/house-robber/
     * 考虑每个房子，有偷和不偷两个选择
     *
     * @param nums
     * @return
     */
    public int rob(int[] nums) {
        int length = nums.length;
        if (length == 0) {
            return 0;
        }
        if (length == 1) {
            return nums[0];
        }
        int[] dp = new int[length];
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);
        for (int i = 2; i < length; i++) {
            dp[i] = Math.max(dp[i - 1], nums[i] + dp[i - 2]);
        }
        return dp[length - 1];
    }

    /**
     * 279. 完全平方数
     * https://leetcode.cn/problems/perfect-squares/description/
     * 从较小数推导出
     *
     * @param n
     * @return
     */
    public int numSquares(int n) {
        int[] dp = new int[n + 1];
        // dp[0] = 0 不能遍历
        for (int i = 1; i <= n; i++) {
            int min = Integer.MAX_VALUE;
            for (int j = 1; (j * j) <= i; j++) {
                min = Math.min(min, dp[i - j * j] + 1);
            }
            dp[i] = min;
        }
        return dp[n];
    }

    /**
     * 322. 零钱兑换
     * https://leetcode.cn/problems/coin-change/description/
     * 从较小数推导出
     *
     * @param coins
     * @param amount
     * @return
     */
    public int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        for (int i = 1; i <= amount; i++) {
            // 涉及到+1，所以不能用Integer.MAX_VALUE
            int min = amount + 1;
            for (int j = 0; j < coins.length; j++) {
                int c = coins[j];
                if (i >= c) {
                    min = Math.min(min, dp[i - coins[j]] + 1);
                }
            }
            dp[i] = min;
        }
        // 只能在外部判断 不能直接把dp值赋值为-1，否则会参与推导
        return dp[amount] > amount ? -1 : dp[amount];
    }

    /**
     * 139. 单词拆分
     * https://leetcode.cn/problems/word-break/
     *
     * 动态规划思想，从前往后遍历
     * 将子字符串分为前后部分，前部分用dp，后部分用保存的set判断
     *
     * @param s
     * @param wordDict
     * @return
     */
    public boolean wordBreak(String s, List<String> wordDict) {
        Set<String> wordDictSet = new HashSet<>(wordDict);
        boolean[] dp = new boolean[s.length() + 1];
        dp[0] = true;
        for (int i = 1; i <= s.length(); i++) {
            for (int j = 0; j < i; j++) {
                if (dp[j] && wordDictSet.contains(s.substring(j, i))) {
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[s.length()];
    }

}
