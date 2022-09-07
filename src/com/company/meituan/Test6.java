package com.company.meituan;

import java.util.HashSet;
import java.util.Scanner;

/**
 * 小团的神秘暗号
 * https://leetcode.cn/leetbook/read/meituan/ohbuu7/
 */
public class Test6 {
    public static void main(String[] args) {
//        String str = "MMATSATMMT";
//        System.out.println(getFirstIndexOfT(str));
//        System.out.println(getLastIndexOfM(str));
//        String str1 = getStr(str);
//        for (int i = 0; i < str1.length(); i++) {
//            System.out.println(str1.charAt(i));
//        }
        Scanner sc = new Scanner(System.in);
        int len = sc.nextInt();
        String str = sc.next();
        System.out.println(getStr(str));

    }

    private static String getStr(String str) {
        int left = getFirstIndexOfT(str);
        int right = getLastIndexOfM(str);
        return str.substring(left + 1, right);
    }

    // 找到下标T首次出现的下标索引
    private static int getFirstIndexOfT(String str) {
        char[] chars = str.toCharArray();
        int left = 0;
        for(Character c : chars) {
            left++;
            if (c == 'T') {
                break;
            }
        }
        return left - 1;
    }

    // 找到下标M最后一次出现的下标索引
    private static int getLastIndexOfM(String str) {
        char[] chars = str.toCharArray();
        for (int i = chars.length - 1; i >= 0; i--) {
            if (chars[i] == 'M') {
                return i;
            }
        }
        return -1;
    }
}
