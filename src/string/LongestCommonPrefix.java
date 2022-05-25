/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2021 All Rights Reserved.
 */
package string;

/**
 * 14. 最长公共前缀
 *  (1). 横向扫描
 *  https://leetcode-cn.com/problems/longest-common-prefix/
 * @author linjie
 * @version : LongestCommonPrefix.java, v 0.1 2021年07月22日 1:07 上午 linjie Exp $
 */
public class LongestCommonPrefix {

    /**
     * 横向扫描方法
     *
     * @param strs
     * @return
     */
    public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }
        int n = strs.length;
        String commonPrefix = strs[0];
        for (int i = 1; i < n; i++) {
            int l = Math.min(commonPrefix.length(), strs[i].length());
            int j = 0;
            while(j < l) {
                if (commonPrefix.charAt(j) != strs[i].charAt(j)) {
                    break;
                } else {
                    j++;
                }
            }
            commonPrefix = commonPrefix.substring(0, j);
            if (commonPrefix.length() == 0) {
                break;
            }
        }
        return commonPrefix;
    }
}