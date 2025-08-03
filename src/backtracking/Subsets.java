package backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : ljke
 * @date : Created in 22:06 2025/7/22
 */
public class Subsets {

    /**
     * 78. 子集
     * https://leetcode.cn/problems/subsets/description/
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        // 回溯遍历所有情况
        backtrace(0, nums, new ArrayList<>(), res);
        return res;
    }

    public void backtrace(int i, int[] nums, List<Integer> output, List<List<Integer>> res) {
        if (i == nums.length) {
            // 结束条件是所有元素都选取结束
            res.add(new ArrayList<>(output));
            return;
        }
        // 包含当前元素
        output.add(nums[i]);
        backtrace(i + 1, nums, output, res);
        // 不包含当前元素
        output.remove(output.size() - 1);
        backtrace(i + 1, nums, output, res);
    }

}
