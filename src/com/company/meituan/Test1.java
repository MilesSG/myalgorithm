package com.company.meituan;

import java.util.Scanner;

/**
 * 小美的用户名
 * https://leetcode.cn/leetbook/read/meituan/ohsjgd/
 */
public class Test1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int size = sc.nextInt();
        String[] names = new String[size];
        for (int i = 0; i < size; i++){
            names[i] = sc.next();
        }

        // 逐个判断
        for (String name : names){
            if (correct(name)){
                System.out.println("Accept");
            }else{
                System.out.println("Wrong");
            }
        }
    }

    private static boolean correct(String str) {
        char[] chars = str.toCharArray();;
        if (Character.toUpperCase(chars[0]) < 'A') {
            return false;
        }
        if (Character.toUpperCase(chars[0]) > 'Z') {
            return false;
        }
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] < '0') {
                return false;
            }
            if (Character.toUpperCase(chars[0]) < 'A' && Character.toUpperCase(chars[0]) > '9') {
                return false;
            }
            if (Character.toUpperCase(chars[0]) > 'Z') {
                return false;
            }
        }
        int numOfChar = 0;
        int numOfShuzi = 0;
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] > '0' && chars[i] < '9') {
                numOfShuzi++;
            }
            if (Character.toUpperCase(chars[0]) > 'A' && Character.toUpperCase(chars[0]) < 'Z' ) {
                numOfChar++;
            }
            if (numOfShuzi < 1 || numOfChar < 1) {
                return false;
            }
        }
        return true;
    }
}
