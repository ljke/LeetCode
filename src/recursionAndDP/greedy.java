/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package recursionAndDP;

/**
 * 贪心算法相关
 *
 * @author linjie
 * @version : greedy.java, v 0.1 2022年05月28日 1:02 上午 linjie Exp $
 */
public class greedy {

    /**
     * 55.跳跃游戏
     * https://leetcode.cn/problems/jump-game/
     *
     * @param nums
     * @return
     */
    public boolean canJump(int[] nums) {
        int n = nums.length;
        // 记录最远能到达距离
        int rightMore = 0;
        for (int i = 0; i < n; i++) {
            if (i <= rightMore) {
                // 能到达更新最远距离
                rightMore = Math.max(rightMore, i + nums[i]);
                if (rightMore >= (n - 1)) {
                    return true;
                }
            }
        }
        return false;
    }
}