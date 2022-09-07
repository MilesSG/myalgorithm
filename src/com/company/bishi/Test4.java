package com.company.bishi;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
/**
 * 20220905中兴第一题
 */
public class Test4 {
    static List<String> res = new ArrayList<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] led = new int[10];
        dfs(led, 0, n, "");
        System.out.println("[");
        for (int i = res.size() - 1; i >= 0; i--) {
            System.out.println(res.get(i));
            if (i != 0) {
                System.out.println(", ");
            }
        }
        System.out.println("]");
    }

    private static void dfs(int[] led, int index, int count, String time) {
        if (count <= 0) {
            if ("0.1".equals(time)) {
                System.out.println(123);
            }
            res.add(time);
            return;
        }
        if (index >= 10) {
            return;
        }
        for (int i = index; i < 10; i++) {
            led[i] = 1;
            if (change(Arrays.copyOfRange(led, 0, 4)) < 12
                    && change(Arrays.copyOfRange(led, 4, 10)) < 60) {
                String h = String.valueOf(change(Arrays.copyOfRange(led, 0, 4)));
                String m = String.valueOf(change(Arrays.copyOfRange(led, 4, 10)));
                if (m.length() == 1) {
                    m = "0" + m;
                }
                time = h + ":" + m;
                count--;
                dfs(led, i + 1, count, time);
                count++;
            }
            led[i] = 0;
        }
    }

    private static int change(int[] subArr) {
        return Integer.valueOf(help(subArr), 2);
    }

    private static String help(int[] subArr) {
        StringBuilder sb = new StringBuilder();
        for (int num : subArr) {
            sb.append(num);
        }
        return sb.toString();
    }
}
