/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2021 All Rights Reserved.
 */
package string;

/**
 * 67. 二进制求和
 * 遍历模拟
 *
 * @author linjie
 * @version : AddBinary.java, v 0.1 2021年08月26日 9:20 上午 linjie Exp $
 */
public class AddBinary {
    public String addBinary(String a, String b) {
        StringBuffer sb = new StringBuffer();

        int n = Math.max(a.length(), b.length()), carry = 0;
        for (int i = 0; i < n; i++) {
            carry += i < a.length() ? (a.charAt(a.length() - 1 - i)) - '0' : 0;
            carry += i < b.length() ? (b.charAt(b.length() - 1 - i)) - '0' : 0;
            sb.append((char)(carry % 2 + '0'));
            carry /= 2;
        }
        if (carry > 0) {
            sb.append('1');
        }
        sb.reverse();
        return sb.toString();
    }
}