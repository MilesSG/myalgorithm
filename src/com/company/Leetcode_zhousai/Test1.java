package com.company.Leetcode_zhousai;

// 0829周赛 https://leetcode.cn/contest/ubiquant2022/problems/xdxykd/
// 暴力超时 29/30
public class Test1 {
    public int numberOfPairs(int[] nums) {
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                int num1 = nums[i];
                int num2 = nums[j];
                int sum1 = num1 + reverse(num2);
                int sum2 = reverse(num1) + num2;
                if (sum1 == sum2) {
                    count++;
                }
            }
        }
        return count;
    }

    // 将12345 转成54321
    private int reverse(int num) {
        // int ---> String
        String str = String.valueOf(num);
        // String ---> StringBuilder
        StringBuilder sb = new StringBuilder(str);
        // 调用StringBuilder的reverse() API
        sb.reverse();
        // StringBuilder ---> String; String ---> int
        return Integer.parseInt(sb.toString());
    }
}
