package com.company.qita;

/**
 * 929. 独特的电子邮件地址
 * https://leetcode.cn/problems/unique-email-addresses/
 */
public class Test1 {
    public static void main(String[] args) {
        String change = change("test.email+alex");
        String change1 = change("test.e.mail+bob.cathy");
        System.out.println(change("testemail+david"));
        System.out.println(change);
        System.out.println(change1);
    }

    private static String change(String str) {
        StringBuilder sb = new StringBuilder();
        char[] chars = str.toCharArray();
        for (Character c : chars) {
            if (c == '.') {
                continue;
            } else if (c == '+') {
                break;
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }
}
