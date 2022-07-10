/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package array;

import java.util.*;

/**
 * 字母异位词相关问题
 *
 * @author linjie
 * @version : Anagram.java, v 0.1 2022年07月03日 11:08 上午 linjie Exp $
 */
public class Anagram {
    /**
     * 242. 有效的字母异位词
     * https://leetcode.cn/problems/valid-anagram/
     *
     * @param s
     * @param t
     * @return
     */
    public boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        // 使用一个散列表来存储字母数量
        char[] sArr = s.toCharArray();
        char[] tArr = t.toCharArray();
        int[] charArr = new int[26];
        for(int i = 0; i < sArr.length; i++) {
            charArr[sArr[i] - 'a']++;
        }
        for(int i = 0; i < tArr.length; i++) {
            charArr[tArr[i] - 'a']--;
            if (charArr[tArr[i] - 'a'] < 0) {
                return false;
            }
        }
        return true;
    }


    /**
     * 49. 字母异位词分组
     * https://leetcode.cn/problems/group-anagrams/
     *
     * @param strs
     * @return
     */
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();
        for (int i = 0; i < strs.length; i++) {
            char[] c = strs[i].toCharArray();
            //排序后的String作为key
            Arrays.sort(c);
            String key = new String(c);
            List<String> value = map.getOrDefault(key, new ArrayList<>());
            value.add(strs[i]);
            map.put(key, value);
        }
        return new ArrayList<>(map.values());
    }
}