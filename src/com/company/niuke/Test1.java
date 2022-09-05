package com.company.niuke;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.TreeMap;

/**
 * BM53 缺失的第一个正整数
 * https://www.nowcoder.com/practice/50ec6a5b0e4e45348544348278cdcee5?tpId=295&tqId=2188893&ru=/exam/oj&qru=/ta/format-top101/question-ranking&sourceUrl=%2Fexam%2Foj
 */
public class Test1 {
    public static void main(String[] args) {
        int[] nums = new int[]{1, 1, 2, 2, 4, 4, 3, 3};
        System.out.println(minNumberDisappeared(nums));
    }

    public static int minNumberDisappeared(int[] nums) {
        // write code here
        TreeMap<Integer, Integer> map = new TreeMap<>();
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], 1);
        }
        int tmp = 1;
        while (map.containsKey(tmp)) {
            tmp++;
        }
        return tmp;
    }
}
