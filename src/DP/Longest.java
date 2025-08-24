package DP;

/**
 * @author : ljke
 * @date : Created in 16:59 2025/8/24
 */
public class Longest {

    /**
     * 5. 最长回文子串
     * https://leetcode.cn/problems/longest-palindromic-substring/
     * 动态规划解法
     *
     * @param s
     * @return
     */
    public String longestPalindrome(String s) {
        int len = s.length();
        if (len < 2) {
            return s;
        }

        // 保存结果
        int begin = 0;
        int maxLen = 1;
        // 表示[i...j]是否回文串
        boolean[][] dp = new boolean[len][len];
        // 单字符情况
        for(int i = 0; i < len; i++) {
            dp[i][i] = true;
        }
        char[] arr = s.toCharArray();
        // 遍历字符串长度
        for(int L = 2; L <= len; L++) {
            // 遍历字符串开头i
            for(int i = 0; i <= len - L; i++) {
                // 字符串结尾j
                int j = i + L - 1;
                // 比较是否回文串
                if (arr[i] != arr[j]) {
                    dp[i][j] = false;
                } else {
                    if (L <= 3) {
                        // 3个字符内必可以
                        dp[i][j] = true;
                    } else {
                        // 取决于子串
                        dp[i][j] = dp[i + 1][j - 1];
                    }
                }
                // 保存最大值
                if (dp[i][j] && L > maxLen) {
                    begin = i;
                    maxLen = L;
                }
            }
        }
        return s.substring(begin, begin + maxLen);
    }

    /**
     * 1143. 最长公共子序列
     * https://leetcode.cn/problems/longest-common-subsequence/
     *
     * @param text1
     * @param text2
     * @return
     */
    public int longestCommonSubsequence(String text1, String text2) {
        int m = text1.length(), n = text2.length();
        // 哨兵优化减少判断，第0行/列相当于和空字符串相比，所以等于0
        int[][] dp = new int[m + 1][n + 1];
        for (int i = 1; i <= m; i++) {
            char c1 = text1.charAt(i - 1);
            for (int j = 1; j <= n; j++) {
                char c2 = text2.charAt(j - 1);
                if (c1 == c2) {
                    // 相等直接+1
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    // 不相等根据子问题推导
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[m][n];
    }

    /**
     * 300. 最长递增子序列
     * https://leetcode.cn/problems/longest-increasing-subsequence/
     * 动态规划，时间复杂度O(n^2)
     *
     * @param nums
     * @return
     */
    public int lengthOfLIS1(int[] nums) {
        // dp表示以i结尾的子序列的最大长度
        int[] dp = new int[nums.length];
        dp[0] = 1;
        int maxV = 1;
        for(int i = 1; i < nums.length; i++) {
            // 注意初始值
            int x = 0;
            // 与前面的dp比较取最大值，条件是必须大于对应位置的值
            for(int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    x = Math.max(x, dp[j]);
                }
            }
            dp[i] = x + 1;
            // 同时计算最优解
            maxV = Math.max(dp[i], maxV);
        }
        return maxV;
    }

    /**
     * 贪心+二分查找
     * 贪心体现在每次都保存最小的末尾取值
     * 时间复杂度O(nlogn)
     *
     * @param nums
     * @return
     */
    public int lengthOfLIS2(int[] nums) {
        int len = 1;
        int n = nums.length;
        if (n == 0) {
            return 0;
        }
        // d表示长度为i的递增序列的末尾最小取值
        int[] d = new int[n + 1];
        d[len] = nums[0];
        // 依次遍历每一个数
        for (int i = 1; i < n; i++) {
            if (nums[i] > d[len]) {
                // 大于情况作为序列元素添加
                len++;
                d[len] = nums[i];
            }
            // 可证明d是单调递增的，所以可以用二分查找
            // 查找最后一个小于nums[i]的元素位置
            int l = 1, r = len, pos = 0;
            while (l <= r) {
                int mid = l + (r - l) / 2;
                if (d[mid] < nums[i]) {
                    if (mid == len || d[mid + 1] >= nums[i]) {
                        pos = mid;
                        break;
                    } else {
                        l = mid + 1;
                    }
                } else {
                    r = mid - 1;
                }
            }
            d[pos + 1] = nums[i];
        }
        return len;
    }



}
