/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package array;

import java.util.ArrayList;
import java.util.List;

/**
 * 119. 杨辉三角 II
 * https://leetcode.cn/problems/pascals-triangle-ii/submissions/
 * 
 * @author linjie
 * @version : YanghuiTriangle.java, v 0.1 2022年06月23日 12:29 上午 linjie Exp $
 */
public class YanghuiTriangle {
    /**
     * 思想类似于动态规划，从前一个状态推导出下一个状态
     * 这里就是构造每一行杨辉三角数据
     * 
     * @param rowIndex 
     * @return
     */
    public List<Integer> getRow(int rowIndex) {
        List<List<Integer>> triangle = new ArrayList<>();
        for(int i = 0; i <= rowIndex; i++) {
            List<Integer> row = new ArrayList<>();
            for(int j = 0; j <= i; j++) {
                if (j == 0 || j == i) {
                    // 第一个和最后一个特殊处理
                    row.add(1);
                } else {
                    // 从上一行推导
                    int x = triangle.get(i - 1).get(j - 1) + triangle.get(i - 1).get(j);
                    row.add(x);
                }
            }
            triangle.add(row);
        }
        return triangle.get(rowIndex);
    }


    /**
     * 优化版本
     * 使用一维list保存
     * 
     * @param rowIndex 
     * @return
     */
    public List<Integer> getRow2(int rowIndex){
        List<Integer> triangle = new ArrayList<>();
        for (int i = 0; i <= rowIndex; i++) {
            // 每行增加一个元素
            triangle.add(1);
            // 不处理第一个和最后一个元素，并且从后往前处理
            for (int j = (i - 1); j > 0; j--) {
                int x = triangle.get(j - 1) + triangle.get(j);
                triangle.set(j, x);
            }
        }
        return triangle;
    }
}