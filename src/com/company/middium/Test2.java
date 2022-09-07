package com.company.middium;

import java.util.LinkedList;
import java.util.List;

/**
 * 179. 最大数
 */
public class Test2 {
    public static void main(String[] args) {
        int[] nums = {1, 2, 3};
        System.out.println(largestNumber(nums));
    }

    public static String largestNumber(int[] nums) {
        List<List<Integer>> lists = permute(nums);
        String[] strs = new String[lists.size()];
        int[] finNums = new int[strs.length];
        int finRes = 0;
        for (int i = 0; i < finNums.length; i++) {
            finRes = Math.max(finRes, parse(lists.get(i)));
        }

        return String.valueOf(finRes);
    }

    static List<List<Integer>> res = new LinkedList<>();
    static LinkedList<Integer> path = new LinkedList<>();

    public static List<List<Integer>> permute(int[] nums) {
        dfs(nums, 0);
        return res;
    }

    private static void dfs(int[] nums, int start) {
        if (path.size() == nums.length) {
            res.add(new LinkedList<>(path));
        }
        for (int i = 0; i < nums.length; i++) {
            // 已经用过的就不能再用了
            if (path.contains(nums[i])) {
                continue;
            }
            path.add(nums[i]);
            dfs(nums, i + 1);
            path.removeLast();
        }
    }

    // 将[1, 2, 3]解析出来 123数字
    private static int parse(List<Integer> lists) {
        StringBuilder sb = new StringBuilder();
        int left = 0;
        while (left < lists.size()) {
            sb.append(lists.get(left));
            left++;
        }
        return Integer.parseInt(sb.toString());
    }
}
