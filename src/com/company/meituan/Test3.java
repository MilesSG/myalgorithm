package com.company.meituan;

import java.util.ArrayList;
import java.util.List;

public class Test3 {
    static List<Character> list = new ArrayList<>();
    static int res = Integer.MAX_VALUE;
    static int start = 0;
    static int[] ints = new int[2];

    public static void main(String[] args) {
        char[] chars = new char[]{'c', 'a', 'c', 'b', 'c', 'c'};
//        char[] chars = new char[]{'a','d','c','d','d'};

        matchword("abc", chars);

    }

    public static int[] matchword(String targetWord, char[] inputArray) {
        char[] s = targetWord.toCharArray();
        int len = s.length;
        int index = 0;
        backTrackint(targetWord, inputArray, 0, 0);
        System.out.println(ints[0] + " " + ints[1]);
        return ints;


    }

    public static void backTrackint(String targetWord, char[] inputArray, int index, int targetIndex) {
        if (targetIndex == targetWord.length()) {
            if (index - start + 1 < res) {
                res = index - start + 1;
                start = start;
                ints = new int[]{start, index - 1};
            }
            return;
        }
        for (int i = index; i < inputArray.length; i++) {
            if (inputArray[i] == targetWord.charAt(targetIndex)) {
                if (targetIndex == 0) {
                    start = i;
                }
                targetIndex++;
                backTrackint(targetWord, inputArray, i + 1, targetIndex);
                targetIndex--;
            }
        }

    }
}