/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2021 All Rights Reserved.
 */
package array;

/**
 * 66. 加一
 * 简单逻辑判断
 * https://leetcode-cn.com/problems/plus-one/
 *
 * @author linjie
 * @version : PlusOne.java, v 0.1 2021年07月28日 12:19 上午 linjie Exp $
 */
public class PlusOne {
    public int[] plusOne(int[] digits) {
        for (int i = (digits.length - 1); i >= 0; i--) {
            if (digits[i] == 9) {
                digits[i] = 0;
            } else {
                digits[i] = digits[i] + 1;
                return digits;
            }
        }
        int[] new_digits = new int[digits.length + 1];
        new_digits[0] = 1;
        return new_digits;
    }
}