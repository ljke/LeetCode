package backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : ljke
 * @date : Created in 22:32 2025/7/22
 */
public class CombinationSum {

    /**
     * 39. 组合总和
     * https://leetcode.cn/problems/combination-sum/description/
     *
     * @param candidates
     * @param target
     * @return
     */
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> output = new ArrayList<>();
        backtrace(target, candidates, 0, output, res);
        return res;
    }

    public void backtrace(int target, int[] candidates, int idx, List<Integer> output, List<List<Integer>> res) {
        if (idx == candidates.length) {
            return;
        }
        if (target == 0) {
            res.add(new ArrayList<>(output));
            return;
        }
        // 不包含当前数字
        backtrace(target, candidates, idx + 1, output, res);
        // 包含当前数字
        int current = candidates[idx];
        if (target >= current) {
            output.add(current);
            // 由于数字可重复，所以idx不自增，继续考虑当前数字
            backtrace(target - current, candidates, idx, output, res);
            output.remove(output.size() - 1);
        }
    }

}
