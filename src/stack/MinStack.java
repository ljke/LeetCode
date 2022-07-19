/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package stack;

import java.util.Stack;

/**
 * 155. 最小栈
 * https://leetcode.cn/problems/min-stack/
 *
 * @author linjie
 * @version : MinStack.java, v 0.1 2022年07月14日 11:49 下午 linjie Exp $
 */
public class MinStack {

    class MinStack1 {
        //维护一个最小值栈，表示当前栈中的最小值
        Stack<Integer> stack;
        Stack<Integer> minStack;

        public MinStack1() {
            stack = new Stack<>();
            minStack = new Stack<>();
            // 保存一个哨兵，处理边界情况
            minStack.push(Integer.MAX_VALUE);
        }

        public void push(int val) {
            stack.push(val);
            minStack.push(Math.min(val, minStack.peek()));
        }

        public void pop() {
            stack.pop();
            minStack.pop();
        }

        public int top() {
            return stack.peek();
        }

        public int getMin() {
            return minStack.peek();
        }
    }

    class MinStack2 {
        // stack维护差值，不需要2个栈
        // 有加减运算，大数相加可能会溢出
        Stack<Long> stack;
        // 当前的最小值
        long minValue;

        public MinStack2() {
            stack = new Stack<>();
        }

        public void push(int val) {
            if (stack.isEmpty()) {
                stack.push(0L);
                minValue = val;
            } else {
                long diff = val - minValue;
                stack.push(diff);
                // 更新最小值
                if (diff < 0) {
                    minValue = val;
                }
            }
        }

        public void pop() {
            if (!stack.isEmpty()) {
                long diff = stack.pop();
                // 更新最小值
                if (diff < 0) {
                    minValue = minValue - diff;
                }
            }
        }

        public int top() {
            if (stack.isEmpty()) {
                return -1;
            } else {
                long top = stack.peek();
                if (top < 0) {
                    // 小于0是minValue才是实际值，stack里保存的是差值
                    return (int)minValue;
                } else {
                    return (int)(minValue + top);
                }
            }
        }

        public int getMin() {
            if (stack.isEmpty()) {
                return -1;
            } else {
                return (int)minValue;
            }
        }
    }


}