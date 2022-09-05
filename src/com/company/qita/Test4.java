package com.company.qita;

import java.util.LinkedList;
import java.util.List;

/**
 * 409. 最长回文串
 * https://leetcode.cn/problems/longest-palindrome/
 */
public class Test4 {
    public static void main(String[] args) {
        Test4 demo = new Test4();
        System.out.println(demo.longestPalindrome("abc"));
    }

    public int longestPalindrome(String s) {
        dfs(s.toCharArray(), 0);
        String[] strs = new String[res.size()];
        for (int i = 0; i < strs.length; i++) {
            strs[i] = String.valueOf(res.get(i));
        }
        int num = 0;
        for (String str : strs) {
            if (isTrue(str)) {
                num++;
            }
        }
        return num;
    }

    // 判断是否是回文
    private boolean isTrue(String str) {
        int i = 0;
        int j = str.length() - 1;
        while (i < j) {
            if (str.charAt(i) != str.charAt(j)) {
                return false;
            }
            i++;
            j--;
        }
        return true;
    }

    // DFS 全排列出字符串s所有的组合
    List<List<Character>> res = new LinkedList<>();
    LinkedList<Character> path = new LinkedList<>();

    private void dfs(char[] chars, int start) {
        if (path.size() == chars.length) {
            res.add(new LinkedList<>(path));
        }
        for (int i = 0; i < chars.length; i++) {
            if (path.contains(chars[i])) {
                continue;
            }
            path.add(chars[i]);
            dfs(chars, i + 1);
            path.removeLast();
        }
    }
}
