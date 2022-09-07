package com.company.easy;

/**
 * title: 191. 位1的个数
 *
 * @author: Miles
 * @date: 2022/9/7
 */
public class Test3 {
    public int hammingWeight(int n) {
        int res = 0;
        while (n != 0) {
            n = n & (n - 1);
            res++;
        }
        return res;
    }
}
