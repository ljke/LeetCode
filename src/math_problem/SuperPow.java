/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2021 All Rights Reserved.
 */
package math_problem;

import java.util.Stack;

/**
 * 372. 超级次方
 * https://leetcode-cn.com/problems/super-pow/
 *
 * @author linjie
 * @version : SuperPow.java, v 0.1 2021年08月08日 8:15 下午 linjie Exp $
 */
public class SuperPow {
    /**
     * 计算矩阵快速幂，然后取模
     * 幂运算：快速幂通过将幂指数使用二进制来参与计算的方式，将幂运算复杂度降低到logN
     * 模运算：可证明，对乘法的结果求模，等价于先对每个因子都求模，然后对因子相乘的结果再求模（在每个乘法处）
     * 处理数组指数：使用stack从后取值，递归思想缩小问题规模
     *
     * @param a
     * @param b
     * @return
     */
    static int base = 1337;

    public static int superPow(int a, int[] b) {
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < b.length; i++) {
            stack.push(b[i]);
        }
        return reverseSuperPow(stack , a, b);
    }

    private static int reverseSuperPow(Stack<Integer> stack, int a, int[] b) {
        if (stack.isEmpty()) {
            return 1;
        }
        int cur = stack.pop();
        int part1 = pow(a, cur);
        int part2 = pow(reverseSuperPow(stack, a, b), 10);
        return part1 * part2 % base;
    }

    /**
     * 计算a^b，并对base取模，递归形式
     *
     * @param a
     * @param b
     * @return
     */
    private static int pow_recur(int a, int b) {
        if (b == 0) return 1;
        a = a % base;
        if ((b & 1) == 1) {
            return a * pow(a, b - 1) % base;
        } else {
            int sub = pow(a, b / 2);
            return sub * sub % base;
        }
    }

    /**
     * 计算a^b，并对base取模，迭代形式
     *
     * @param a
     * @param b
     * @return
     */
    private static int pow(int a, int b) {
        if (b == 0) return 1;
        a = a % base;
        int res = 1;
        while(b != 0) {
            if ((b & 1) == 1) {
                res = res * a % base;
            }
            a = a * a % base;
            b = b >> 1;
        }
        return res;
    }


    public static void main(String[] args) {
        int res = superPow(2, new int[]{3});
        System.out.println(res);
    }

}