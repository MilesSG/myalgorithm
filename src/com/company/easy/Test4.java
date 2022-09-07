package com.company.easy;

/**
 * title: 367. 有效的完全平方数
 *
 * @author: Miles
 * @date: 2022/9/7
 */
public class Test4 {
    public static void main(String[] args) {
        System.out.println(new Test4().isPerfectSquare(100000));
    }

    public boolean isPerfectSquare(int num) {
        int tmp = (int) Math.sqrt((double)num);
        return tmp * tmp == num;
    }
}
