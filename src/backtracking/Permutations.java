/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package backtracking;

import java.util.*;

/**
 * 46. 全排列
 * https://leetcode.cn/problems/permutations/
 *
 * @author linjie
 * @version : Permutations.java, v 0.1 2022年10月06日 9:32 下午 linjie Exp $
 */
public class Permutations {
    /**
     * 回溯(dfs)解法
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> permute(int[] nums) {
        int len = nums.length;
        // 使用一个动态数组保存所有可能的全排列
        List<List<Integer>> res = new ArrayList<>();
        if (len == 0) {
            return res;
        }

        boolean[] used = new boolean[len];
        Deque<Integer> path = new ArrayDeque<>(len);

        dfs(nums, len, 0, path, used, res);
        return res;
    }

    private void dfs(int[] nums, int len, int depth,
                     Deque<Integer> path, boolean[] used,
                     List<List<Integer>> res) {
        if (depth == len) {
            res.add(new ArrayList<>(path));
            return;
        }

        for (int i = 0; i < len; i++) {
            if (!used[i]) {
                path.addLast(nums[i]);
                used[i] = true;

                dfs(nums, len, depth + 1, path, used, res);

                used[i] = false;
                path.removeLast();
            }
        }
    }

    /**
     * 考虑不使用used数组，通过将数组分割成使用和未使用两部分实现
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> permuteOpti(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> output = new ArrayList<>();
        for(int n : nums) {
            output.add(n);
        }
        backtrace(output, output.size(), 0, res);
        return res;
    }

    public void backtrace(List<Integer> output, int n, int first, List<List<Integer>> res) {
        if (first == n) {
            res.add(new ArrayList<>(output));
            return;
        }
        // i必须从first开始，不能从first+1开始，否则进不了循环
        for (int i = first; i < n; i++) {
            Collections.swap(output, first, i);
            backtrace(output, n, first + 1, res);
            Collections.swap(output, first, i);
        }
    }

}