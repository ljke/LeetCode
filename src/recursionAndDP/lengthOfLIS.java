package recursionAndDP;

/**
 * @author : ljke
 * @date : Created in 16:15 2025/7/24
 */
public class lengthOfLIS {
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
