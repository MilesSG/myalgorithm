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
        System.out.println(getFirst('a', s));
        System.out.println(getLast('a', s));
        System.out.println(getWidth('a', s));
    }

    public boolean checkDistances(String s, int[] distance) {
        HashMap<Character, Integer> map = new HashMap<>();
        char[] chars = s.toCharArray();
        HashSet<Character> set = new HashSet<>();
        for (Character c : chars) {
            set.add(c);
        }
        for (Character c : set) {
            map.put(c, getWidth(c, s));
        }

        HashMap<Integer, Character> tmpMap = new HashMap<>();
        tmpMap.put(0, 'a');
        tmpMap.put(1, 'b');
        tmpMap.put(2, 'c');
        tmpMap.put(3, 'd');
        tmpMap.put(4, 'e');
        tmpMap.put(5, 'f');
        tmpMap.put(6, 'g');
        tmpMap.put(7, 'h');
        tmpMap.put(8, 'i');
        tmpMap.put(9, 'j');
        tmpMap.put(10, 'k');
        tmpMap.put(11, 'l');
        tmpMap.put(12, 'm');
        tmpMap.put(13, 'n');
        tmpMap.put(14, 'o');
        tmpMap.put(15, 'p');
        tmpMap.put(16, 'q');
        tmpMap.put(17, 'r');
        tmpMap.put(18, 's');
        tmpMap.put(19, 't');
        tmpMap.put(20, 'u');
        tmpMap.put(21, 'v');
        tmpMap.put(22, 'w');
        tmpMap.put(23, 'x');
        tmpMap.put(24, 'y');
        tmpMap.put(25, 'z');
        // 遍历distance进行匹配
        for (int i = 0; i < set.size(); i++) {
            if (distance[i] != tmpMap.get(i)) {
                return false;
            }
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
