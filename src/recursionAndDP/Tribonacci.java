/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2021 All Rights Reserved.
 */
package recursionAndDP;

/**
 * 1137. 第 N 个泰波那契数
 * https://leetcode-cn.com/problems/n-th-tribonacci-number/
 * T0 = 0, T1 = 1, T2 = 1, 且在 n >= 0 的条件下 Tn+3 = Tn + Tn+1 + Tn+2
 * @author linjie
 * @version : Tribonacci.java, v 0.1 2021年08月08日 7:29 下午 linjie Exp $
 */
public class Tribonacci {
    /**
     * 迭代实现，滚动数组优化空间
     * 时间复杂度O(n), 空间复杂度O(1)
     */
    public int tribonacci1(int n) {
        if (n == 0) return 0;
        if (n == 1 || n == 2) return 1;
        int p = 0, q = 0, r = 1, s = 1;
        for (int i = 3; i <= n; i++) {
            p = q;
            q = r;
            r = s;
            s = p + q + r;
        }
        return s;
    }

    /**
     * 递归实现
     * 通过cache避免重复计算
     * 时间复杂度O(n), 空间复杂度O(n)
     */
    int[] cache = new int[40];
    public int tribonacci2(int n) {
        if (n == 0) return 0;
        if (n == 1 || n == 2) return 1;
        if (cache[n] != 0) return cache[n];
        cache[n] =  tribonacci2(n - 1) + tribonacci2(n - 2) + tribonacci2(n - 3);
        return cache[n];
    }

    /**
     * 通过矩阵快速幂实现
     * 矩阵快速幂参看 math_problem.SuperPow
     */
    public int tribonacci3(int n) {
        if (n == 0) return 0;
        if (n == 1 || n == 2) return 1;
        int[][] q = {{1, 1, 1}, {1, 0, 0}, {0, 1, 0}};
        int[][] res = pow(q, n);
        return res[0][2];
    }

    private int[][] pow(int[][] a, int b) {
        int[][] res = {{1, 0, 0}, {0, 1, 0}, {0, 0, 1}};
        while(b != 0) {
            if ((b & 1) == 1) {
                res = mul(res, a);
            }
            a = mul(a, a);
            b = b >> 1;
        }
        return res;
    }

    private int[][] mul(int[][] a, int[][] b) {
        int[][] c = new int[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                c[i][j] = a[i][0] * b[0][j] + a[i][1] * b[1][j] + a[i][2] * b[2][j];
            }
        }
        return c;
    }

}