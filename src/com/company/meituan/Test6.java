package com.company.meituan;

import java.util.HashSet;

/**
 * 小团的神秘暗号
 * https://leetcode.cn/leetbook/read/meituan/ohbuu7/
 */
public class Test6 {
    public static void main(String[] args) {

    }

    private static String getStr(String str) {
        char[] chars = str.toCharArray();
        int left = 0;
        for(Character c : chars) {
            left++;
            if (c == 'T') {
                break;
            }
        }

    }
}
