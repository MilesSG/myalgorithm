package com.company.qita;

/**
 * 796. 旋转字符串
 * https://leetcode.cn/problems/rotate-string/
 */
public class Test3 {
    public static void main(String[] args) {
        String str = "abcde";
    }

    public boolean rotateString(String s, String goal) {
        String tmp = s + goal;
        return tmp.contains(s);

    }
}
