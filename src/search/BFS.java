package search;

import java.util.*;

/**
 * @author : ljke
 * @date : Created in 15:37 2025/7/25
 */
public class BFS {

    /**
     * 127. 单词接龙
     * https://leetcode.cn/problems/word-ladder/description/
     *
     * @param beginWord
     * @param endWord
     * @param wordList
     * @return
     */
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        Map<String, List<String>> roadMap = new HashMap<>();
        wordList.add(beginWord);
        // 构造图
        for (String word : wordList) {
            char[] charArray = word.toCharArray();
            for (int i = 0; i < word.length(); i++) {
                // 添加只改变一个字符的中间虚拟节点
                char c = charArray[i];
                charArray[i] = '*';
                String change = new String(charArray);
                roadMap.computeIfAbsent(word, k -> new ArrayList<>()).add(change);
                roadMap.computeIfAbsent(change, k -> new ArrayList<>()).add(word);
                charArray[i] = c;
            }
        }
        if (!roadMap.containsKey(endWord)) {
            return 0;
        }
        // bfs遍历 记录层级
        int step = 0;
        Queue<String> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();
        queue.offer(beginWord);
        visited.add(beginWord);
        while (!queue.isEmpty()) {
            // 此处必须提前保存成变量，因为size会变
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                String word = queue.poll();
                if (endWord.equals(word)) {
                    // 由于中间虚拟节点的存在，需要除以2，并且要加上一个起点
                    return step / 2 + 1;
                }
                if (roadMap.containsKey(word)) {
                    for (String newWord : roadMap.get(word)) {
                        if (visited.contains(newWord)) {
                            continue;
                        }
                        queue.offer(newWord);
                        visited.add(newWord);
                    }
                }
            }
            step++;
        }
        return 0;
    }

    int[] dr = new int[]{-1, 0, 1, 0};
    int[] dc = new int[]{0, -1, 0, 1};

    /**
     * 994. 腐烂的橘子
     * https://leetcode.cn/problems/rotting-oranges/description/
     *
     * @param grid
     * @return
     */
    public int orangesRotting(int[][] grid) {
        int r = grid.length, c = grid[0].length;
        Queue<Integer> queue = new LinkedList<>();
        int count = 0;
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (grid[i][j] == 2) {
                    // 多源BFS，所有初始腐烂橘子都作为起点
                    queue.add(i * c + j);
                } else if (grid[i][j] == 1) {
                    // 记录新鲜橘子的数量，用于结果检查
                    count++;
                }
            }
        }
        int time = 0;

        while (!queue.isEmpty() && count > 0) {
            int size = queue.size();
            for (int x = 0; x < size; x++) {
                int idx = queue.poll();
                for (int y = 0; y < 4; y++) {
                    // 向四个方向扩散
                    int i = idx / c + dr[y];
                    int j = idx % c + dc[y];
                    if (i >= 0 && i < r && j >= 0 && j < c && grid[i][j] == 1) {
                        // 所有腐烂橘子一定是已经遍历过的，所以无需额外访问判断
                        grid[i][j] = 2;
                        queue.add(i * c + j);
                        count--;
                    }
                }
            }
            time++;
        }

        if (count > 0) {
            return -1;
        } else {
            return time;
        }
    }

    /**
     * 752. 打开转盘锁
     * https://leetcode.cn/problems/open-the-lock/description/
     * 通过A*优化
     *
     * @param deadends
     * @param target
     * @return
     */
    public int openLock1(String[] deadends, String target) {
        if ("0000".equals(target)) {
            return 0;
        }
        Set<String> dead = new HashSet<>();
        for (String deadend : deadends) {
            dead.add(deadend);
        }
        if (dead.contains("0000")) {
            return -1;
        }

        // 优先级队列保证每次取到估计距离最小的节点
        PriorityQueue<Astar> pq = new PriorityQueue<>((a, b) -> a.f - b.f);
        Set<String> visited = new HashSet<>();
        pq.offer(new Astar("0000", target, 0));
        visited.add("0000");

        while (!pq.isEmpty()) {
            Astar node = pq.poll();
            for (String nextStatus : get(node.status)) {
                if (visited.contains(nextStatus) || dead.contains(nextStatus)) {
                    continue;
                }
                if (nextStatus.equals(target)) {
                    return node.g + 1;
                }
                pq.offer(new Astar(nextStatus, target, node.g + 1));
                visited.add(nextStatus);
            }
        }

        return -1;
    }

    // 获取所有相邻组合
    public List<String> get(String status) {
        List<String> ret = new ArrayList<>();
        char[] array = status.toCharArray();
        for (int i = 0; i < 4; i++) {
            char num = array[i];
            array[i] = (num == '0' ? '9' : (char)(num - 1));
            ret.add(new String(array));
            array[i] = (num == '9' ? '0' : (char)(num + 1));
            ret.add(new String(array));
            array[i] = num;
        }
        return ret;
    }

    class Astar {
        // 当前节点值
        String status;
        // 总距离
        int f;
        // 从起点到当前的距离
        int g;
        // 当前到终点的估计距离
        int h;

        public Astar(String status, String target, int g) {
            this.status = status;
            this.g = g;
            this.h = getH(status, target);
            this.f = this.g + this.h;
        }

        // 计算启发函数
        public int getH(String status, String target) {
            int h = 0;
            for(int i = 0; i < 4; i++) {
                int dist = Math.abs(status.charAt(i) - target.charAt(i));
                h += Math.min(dist, 10 - dist);
            }
            return h;
        }
    }

    /**
     * 双向BFS
     *
     * @param deadends
     * @param target
     * @return
     */
    public int openLock2(String[] deadends, String target) {
        if ("0000".equals(target)) {
            return 0;
        }
        Set<String> dead = new HashSet<>();
        for (String deadend : deadends) {
            dead.add(deadend);
        }
        if (dead.contains("0000")) {
            return -1;
        }

        // 使用集合替代队列，便于判断是否存在
        Set<String> q1 = new HashSet<>();
        Set<String> q2 = new HashSet<>();
        Set<String> visited = new HashSet<>();
        q1.add("0000");
        q2.add(target);
        visited.add("0000");
        int step = 0;

        while (!q1.isEmpty() && !q2.isEmpty()) {
            Set<String> tmp;
            if (q1.size() > q2.size()) {
                // 始终使用q1 优先扩展较小的集合
                tmp = q2;
                q2 = q1;
                q1 = tmp;
            }
            tmp = new HashSet<>();
            for (String status : q1) {
                if (dead.contains(status)) {
                    continue;
                }
                // 判断位置，避免visited影响（否则需要两个visited，因为相遇时必然访问了两次）
                if (q2.contains(status)) {
                    // 先入集合后置判断，所以不需要+1
                    return step;
                }
                visited.add(status);
                for (String nextStatus : get(status)) {
                    if (visited.contains(nextStatus)) {
                        continue;
                    }
                    tmp.add(nextStatus);
                }
            }
            step++;
            q1 = tmp;
        }

        return -1;
    }

}
