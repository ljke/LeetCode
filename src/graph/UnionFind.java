package graph;

/**
 * 并查集相关
 *
 * @author : ljke
 * @date : Created in 11:12 2025/8/3
 */
public class UnionFind {

    class UnionFind1 {
        int count;
        int[] parent;

        public UnionFind1(char[][] grid) {
            count = 0;
            int m = grid.length;
            int n = grid[0].length;
            parent = new int[m * n];
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (grid[i][j] == '1') {
                        parent[i * n + j] = i * n + j;
                        count++;
                    }
                }
            }
        }

        public int find(int i) {
            if (parent[i] != i) {
                // 路径压缩
                parent[i] = find(parent[i]);
            }
            return parent[i];
        }

        public void union(int x, int y) {
            int rootx = find(x);
            int rooty = find(y);
            if (rootx != rooty) {
                parent[rootx] = rooty;
                count--;
            }
        }

        public int getCount() {
            return count;
        }
    }

    /**
     * 200. 岛屿数量
     * https://leetcode.cn/problems/number-of-islands/description/
     * 也可以用DFS或者BFS实现
     *
     * @param grid
     * @return
     */
    public int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }

        int m = grid.length;
        int n = grid[0].length;
        UnionFind1 uf = new UnionFind1(grid);
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == '1') {
                    //如果可以修改原数组，可以避免重复计算
                    //grid[i][j] = '0';
                    //和四周位置进行union
                    if (i >= 1 && grid[i - 1][j] == '1') {
                        uf.union(i * n + j, (i - 1) * n + j);
                    }
                    if (i < m - 1 && grid[i + 1][j] == '1') {
                        uf.union(i * n + j, (i + 1) * n + j);
                    }
                    if (j >= 1 && grid[i][j - 1] == '1') {
                        uf.union(i * n + j, i * n + j - 1);
                    }
                    if (j < n - 1 && grid[i][j + 1] == '1') {
                        uf.union(i * n + j, i * n + j + 1);
                    }
                }
            }
        }

        return uf.getCount();
    }

    class UnionFind2 {
        int[] parent;

        public UnionFind2() {
            parent = new int[26];
            for (int i = 0; i < 26; i++) {
                parent[i] = i;
            }
        }

        public void union(int a, int b) {
            int roota = find(a);
            int rootb = find(b);
            if (roota != rootb) {
                parent[roota] = rootb;
            }
        }

        public int find(int a) {
            if (parent[a] != a) {
                parent[a] = find(parent[a]);
            }
            return parent[a];
        }

        public boolean connected(int a, int b) {
            return find(a) == find(b);
        }
    }

    /**
     * 990. 等式方程的可满足性
     * https://leetcode.cn/problems/satisfiability-of-equality-equations/description/
     *
     * @param equations
     * @return
     */
    public boolean equationsPossible(String[] equations) {
        UnionFind2 uf = new UnionFind2();
        // 构建联通关系
        for (String str : equations) {
            if (str.charAt(1) == '=') {
                int a = str.charAt(0) - 'a';
                int b = str.charAt(3) - 'a';
                uf.union(a, b);
            }
        }
        // 判断非联通是否合法
        for (String str : equations) {
            if (str.charAt(1) == '!') {
                int a = str.charAt(0) - 'a';
                int b = str.charAt(3) - 'a';
                if (uf.connected(a, b)) {
                    return false;
                }
            }
        }
        return true;
    }
}
