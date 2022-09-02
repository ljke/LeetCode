/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package array;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 621. 任务调度器
 * https://leetcode.cn/problems/task-scheduler/
 *
 * @author linjie
 * @version : LeastInterval.java, v 0.1 2022年08月20日 5:01 下午 linjie Exp $
 */
public class LeastInterval {
    /**
     * 模拟
     *
     * 原理是每次取剩余次数最多的任务执行
     * 将每种任务的剩余执行次数尽可能平均，使得 CPU 处于待命状态的时间尽可能少
     *
     * @param tasks
     * @param n
     * @return
     */
    public int leastInterval1(char[] tasks, int n) {
        // 统计每个字符频次
        Map<Character, Integer> freq = new HashMap<>();
        for (char ch : tasks) {
            freq.put(ch, freq.getOrDefault(ch, 0) + 1);
        }
        // 每个字符的二元组(下一次可以执行时间, 当前剩余次数)
        // 和实际的字符其实没有关系
        List<Integer> nextTime = new ArrayList<>();
        List<Integer> rest = new ArrayList<>();
        for (Map.Entry<Character, Integer> entry : freq.entrySet()) {
            nextTime.add(1);
            rest.add(entry.getValue());
        }
        // 当前时间片开始执行，次数为任务数
        int time = 0;
        int m = freq.size();
        for (int j = 0; j < tasks.length; j++) {
            time++;
            // 跳过不必要的待命状态
            int minNextValid = Integer.MAX_VALUE;
            for (int i = 0; i < m; i++) {
                // 当前可以执行的任务
                if (rest.get(i) != 0) {
                    minNextValid = Math.min(minNextValid, nextTime.get(i));
                }
            }
            time = Math.max(time, minNextValid);
            // 查找剩余次数最多的字符
            int best = -1;
            for (int i = 0; i < m; i++) {
                // 当前可以执行的任务, nextTime.get(i)是有可能小于time的
                if (rest.get(i) != 0 && nextTime.get(i) <= time) {
                    if (best == -1 || rest.get(i) > rest.get(best)) {
                        best = i;
                    }
                }
            }
            // 更新状态
            nextTime.set(best, time + n + 1);
            rest.set(best, rest.get(best) - 1);
        }
        return time;
    }

    /**
     * 构造
     * 使用一个宽为 n+1 的矩阵可视化地展现执行时间点
     * 两种情况: 任务总数大于 or 小于 n + 1
     *
     *
     * @param tasks
     * @param n
     * @return
     */
    public int leastInterval2(char[] tasks, int n) {
        Map<Character, Integer> freq = new HashMap<>();
        // 统计最多执行次数
        int maxExec = 0;
        for (char c : tasks) {
            int exec = freq.getOrDefault(c, 0) + 1;
            freq.put(c, exec);
            maxExec = Math.max(maxExec, exec);
        }
        // 统计等于最大执行次数的字符
        int maxCount = 0;
        for (Integer i : freq.values()) {
            if (i == maxExec) {
                maxCount++;
            }
        }
        // 最大值的可能情况
        return Math.max(tasks.length, (n + 1) * (maxExec - 1) + maxCount);
    }
}