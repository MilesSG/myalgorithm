package com.company.niuke;

import java.util.HashSet;

/**
 * BM90 最小覆盖子串
 */
public class Test3 {
    public static void main(String[] args) {

    }

    public String minWindow (String S, String T) {
        // write code here
        HashSet<Character> set = new HashSet<>();
        for (int i = 0; i < T.length(); i++) {
            set.add(T.charAt(i));
        }

    }
}
