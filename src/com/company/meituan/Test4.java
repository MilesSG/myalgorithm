package com.company.meituan;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;

// 淘汰分数 https://www.nowcoder.com/test/question/9c4a4e879b4f49939dfaebea8948f976?pid=28665343&tid=60476219
public class Test4 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int totalNum = sc.nextInt();
        int jinJi = sc.nextInt();
        int taoTai = sc.nextInt();
        int[] nums = new int[totalNum];
        for (int i = 0; i < totalNum; i++) {
            nums[i] = sc.nextInt();
        }
        getNum(nums, jinJi, taoTai);
    }

    // 晋级和淘汰的人数均在[jinJi, taoTai]之间
    private static int getNum(int[] nums, int jinJi, int taoTai) {
        Arrays.sort(nums);
        HashSet<Integer> set = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            set.add(nums[i]);
        }
        // 存放不重复的分数
        Object[] tmp1 = set.toArray();
        int[] tmp = new int[tmp1.length];
        for (int i = 0; i < tmp.length; i++) {
            tmp[i] = (int) tmp1[i];
        }
        int m = 0;
        // 分别用leftCount和rightCount表示m左右的分数的数量,即淘汰的人数和晋级的人数
        int leftCount = 0;
        int rightCount = 0;
        for (int i = 0; i < nums.length; i++) {
            int cur = nums[i];
            while (cur > nums[0]) {
                leftCount++;
            }
            while (cur < nums[nums.length - 1]) {
                rightCount++;
            }
            if (leftCount >= jinJi && leftCount <= taoTai
                    && rightCount >= jinJi && rightCount <= taoTai) {
                return m;
            }
        }
        return -1;
    }
}
