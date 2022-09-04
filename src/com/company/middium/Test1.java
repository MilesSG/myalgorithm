package com.company.middium;

import com.company.Leetcode_zhousai.Test3;

// 43. 字符串相乘 https://leetcode.cn/problems/multiply-strings/
public class Test1 {
    public static void main(String[] args) {
        String s = new Test1().multiply("123", "456");
        System.out.println(s);
    }

    public String multiply(String num1, String num2) {
        if (num1.equals("0")  || num2.equals("0")) {
            return "0";
        }
        int len1 = num1.length();
        int len2 = num2.length();

        int[] res = new int[len1 + len2];

        for (int i = len1 - 1; i >= 0; i--) {
            int x = num1.charAt(i) - '0';
            for (int j = len2 - 1; j >= 0; j--) {
                int y = num2.charAt(j) - '0';
                res[i + j + 1] += x * y;
            }
        }

        for (int i = len1 + len2 - 1; i > 0; i--) {
            res[i - 1] += res[i] / 10;
            res[i] %= 10;
        }

        StringBuilder sb = new StringBuilder();
        int index = (res[0] == 0) ? 1 : 0;
        while (index < len1 + len2) {
            sb.append(res[index]);
            index++;
        }
        return sb.toString();
    }
}
