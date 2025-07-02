package search;

import java.util.ArrayList;
import java.util.List;

/**
 * 深度优先搜索相关
 */
public class DFS {

    /**
     * 386. 字典序排数
     * https://leetcode.cn/problems/lexicographical-numbers/description/
     *
     * 使用递归-深度优先搜索
     *
     * @param n
     * @return
     */
    public List<Integer> lexicalOrder(int n) {
        List<Integer> res = new ArrayList<>();
        for (int i = 1; i <= 9; i++) {
            dfs(i, n, res);
        }
        return res;
    }

    public void dfs(int cur, int n, List<Integer> res) {
        if (cur > n) {
            return;
        }
        res.add(cur);
        for (int i = 0; i <= 9; i++) {
            // 递归实现加位，注意不能修改cur值
            dfs(cur * 10 + i, n, res);
        }
    }

}
