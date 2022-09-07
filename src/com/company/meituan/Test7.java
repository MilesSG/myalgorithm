package com.company.meituan;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

/**
 * 小团的装饰物
 * https://leetcode.cn/leetbook/read/meituan/oxh202/
 */
public class Test7 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();

    }

    private static int getRes(List<List<Integer>> res) {
        return res.size();
    }

    List<List<Integer>> res = new LinkedList<>();
    LinkedList<Integer> path = new LinkedList<>();

    public List<List<Integer>> permute(int[] nums) {
        dfs(nums, 0);
        return res;
    }

    private void dfs(int[] nums, int start) {
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
}
