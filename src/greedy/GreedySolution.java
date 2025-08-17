/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package greedy;

import java.util.ArrayList;
import java.util.List;

/**
 * 贪心算法相关
 *
 * @author linjie
 * @version : GreedySolution.java, v 0.1 2022年05月28日 1:02 上午 linjie Exp $
 */
public class GreedySolution {

    /**
     * 121. 买卖股票的最佳时机
     * https://leetcode.cn/problems/best-time-to-buy-and-sell-stock/description/
     *
     * @param prices
     * @return
     */
    public int maxProfit(int[] prices) {
        int maxProfit = 0;
        int minValue = Integer.MAX_VALUE;
        for (int i = 0; i < prices.length; i++) {
            // 维护目前最小值
            minValue = Math.min(prices[i], minValue);
            // 当前卖出 - 历史最小值
            maxProfit = Math.max(prices[i] - minValue, maxProfit);
        }
        return maxProfit;
    }

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

    /**
     * 45. 跳跃游戏 II
     * https://leetcode.cn/problems/jump-game-ii/description/
     *
     * @param nums
     * @return
     */
    public int jump(int[] nums) {
        int n = nums.length;
        //上次能跳到的最远位置
        int end = 0;
        //目前能跳到的最远位置
        int maxPos = 0;
        int step = 0;
        // 避免最后一次跳跃边界是最后一个位置导致多加一次跳跃，所以不遍历最后一个位置
        for (int i = 0; i < n - 1; i++) {
            //更新目前能调到的最远距离
            maxPos = Math.max(maxPos, i + nums[i]);
            //到达上次能跳到的最远位置时更新成目前能跳到的最远位置
            if (i == end) {
                end = maxPos;
                step++;
            }
        }
        return step;
    }

    /**
     * 763. 划分字母区间
     * https://leetcode.cn/problems/partition-labels/description/
     *
     * @param s
     * @return
     */
    public List<Integer> partitionLabels(String s) {
        int[] last = new int[26];
        // 记录每个字母最后一次出现的位置
        for (int i = 0; i < s.length(); i++) {
            last[s.charAt(i) - 'a'] = i;
        }
        //上次的最后位置
        int start = 0;
        //本次的最后位置
        int end = 0;
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < s.length(); i++) {
            end = Math.max(end, last[s.charAt(i) - 'a']);
            if (i == end) {
                res.add(end - start + 1);
                // 更新最后位置
                start = end + 1;
            }
        }
        return res;
    }

}