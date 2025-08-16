package graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 拓扑排序相关
 *
 * @author : ljke
 * @date : Created in 15:01 2025/8/3
 */
public class TopologicalSort {

    List<List<Integer>> edges;
    int[] indeg;

    /**
     * 207. 课程表
     * https://leetcode.cn/problems/course-schedule/description/
     * DFS形式
     *
     * @param numCourses
     * @param prerequisites
     * @return
     */
    public boolean canFinish1(int numCourses, int[][] prerequisites) {
        edges = new ArrayList<>();
        for (int i = 0; i < numCourses; i++) {
            edges.add(new ArrayList<>());
        }
        indeg = new int[numCourses];
        for (int[] info : prerequisites) {
            // 记录有向边
            edges.get(info[1]).add(info[0]);
            // 记录入度
            indeg[info[0]]++;
        }

        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            if (indeg[i] == 0) {
                queue.offer(i);
            }
        }

        int visited = 0;
        while (!queue.isEmpty()) {
            visited++;
            int u = queue.poll();
            for (int v : edges.get(u)) {
                indeg[v]--;
                // 入度为0才允许进入队列
                if (indeg[v] == 0) {
                    queue.offer(v);
                }
            }
        }

        return visited == numCourses;
    }

    int[] visited; //0：未遍历 1：遍历中 2：遍历完成
    boolean valid = true;

    /**
     * BFS形式
     *
     * @param numCourses
     * @param prerequisites
     * @return
     */
    public boolean canFinish2(int numCourses, int[][] prerequisites) {
        edges = new ArrayList<>();
        for (int i = 0; i < numCourses; i++) {
            edges.add(new ArrayList<>());
        }
        for (int[] info : prerequisites) {
            // 记录有向边
            edges.get(info[1]).add(info[0]);
        }
        visited = new int[numCourses];
        for (int i = 0; i < numCourses && valid; i++) {
            if (visited[i] == 0) {
                dfs(i);
            }
        }
        return valid;
    }

    public void dfs(int u) {
        visited[u] = 1;
        for (Integer v : edges.get(u)) {
            if (visited[v] == 0) {
                dfs(v);
                // 判断递归后是否有无效状态，提前退出
                if (!valid) {
                    return;
                }
            } else if (visited[v] == 1) {
                // 递归过程中碰到1：遍历中状态，表示有环
                valid = false;
                return;
            }
        }
        // 表示所有边都已经遍历完成
        visited[u] = 2;
    }

}
