/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package array;

import java.util.Arrays;

/**
 * 179. 最大数
 * https://leetcode.cn/problems/largest-number/
 *
 * @author linjie
 * @version : LargestNumber.java, v 0.1 2022年07月03日 10:51 上午 linjie Exp $
 */
public class LargestNumber {
    public String largestNumber(int[] nums) {
        Integer[] numsArr = new Integer[nums.length];
        for(int i = 0; i < nums.length; i++) {
            numsArr[i] = nums[i];
        }
        // 从大到小排序，两个数组合
        Arrays.sort(numsArr, (x, y) -> {
            long sx = 10, sy = 10;
            while(sx <= x) {
                sx = sx * 10;
            }
            while(sy <= y) {
                sy = sy * 10;
            }
            return (int)(sx * y + x - (sy * x + y));
        });
        if (numsArr[0] == 0) {
            return "0";
        }
        // 按顺序进行拼接
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < nums.length; i++) {
            sb.append(numsArr[i]);
        }
        return sb.toString();
    }
}