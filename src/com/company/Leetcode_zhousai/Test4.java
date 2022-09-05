package com.company.Leetcode_zhousai;

import java.util.HashMap;
import java.util.HashSet;

/**
 * 6167. 检查相同字母间的距离
 * https://leetcode.cn/contest/weekly-contest-309/problems/check-distances-between-same-letters/
 */
public class Test4 {
    public static void main(String[] args) {
        String s = "abaccb";
        int[] distance = new int[]{1, 3, 0, 5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        System.out.println(checkDistances(s, distance));
    }

    public static boolean checkDistances(String s, int[] distance) {
        HashMap<Character, Integer> map = new HashMap<>();
        char[] chars = s.toCharArray();
        HashSet<Character> set = new HashSet<>();
        for (Character c : chars) {
            set.add(c);
        }
        for (Character c : set) {
            map.put(c, getWidth(c, s));
        }

        // 遍历distance进行匹配
        int i = 0;
        for (Character c : set) {
            if (map.get(c) != distance[i]) {
                return false;
            }
            i++;
        }
        return true;
    }

    // 获取某个字符在一个字符串中第一次出现的位置
    private static int getFirst(char c, String str) {
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == c) {
                return i;
            }
        }
        return -1;
    }

    // 获取某个字符在一个字符串中第二次出现的位置
    private static int getLast(char c, String str) {
        for (int i = str.length() - 1; i >= 0; i--) {
            if (str.charAt(i) == c) {
                return i;
            }
        }
        return -1;
    }

    // 计算出第一次出现和第二次出现之间的距离(注意减一)
    private static int getWidth(char c, String str) {
        return getLast(c, str) - getFirst(c, str) - 1;
    }
}
