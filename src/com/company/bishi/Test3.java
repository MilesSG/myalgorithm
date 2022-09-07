package com.company.bishi;

/**
 * 20220905中兴第二题
 */

import java.util.Arrays;
import java.util.Scanner;

public class Test3 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
//        int m = sc.nextInt();
//        int n = sc.nextInt();
//        int[] nums1 = new int[m];
//        int[] nums2 = new int[n];
//        for (int i = 0; i < m; i++) {
//            nums1[i] = sc.nextInt();
//        }
//        for (int i = 0; i < n; i++) {
//            nums2[i] = sc.nextInt();
//        }
        String str1 = sc.nextLine();
        String[] str1s = str1.split(" ");
        int[] nums1 = new int[str1s.length];
        for (int i = 0; i < nums1.length; i++) {
            nums1[i] = Integer.parseInt(str1s[i]);
        }
        String str2 = sc.nextLine();
        String[] str2s = str2.split(" ");
        int[] nums2 = new int[str2s.length];
        for (int i = 0; i < nums2.length; i++) {
            nums2[i] = Integer.parseInt(str2s[i]);
        }

        System.out.println(getMid(nums1, nums2));
    }

    private static double getMid(int[] nums1, int[] nums2) {
        int[] res = merge(nums1, nums2);
        if (res.length / 2 == 1) {
            return res[(res.length - 1) / 2];
        } else {
            int len = res.length;
            return (res[len / 2 - 1] + res[len / 2]) / 2.0;
        }
    }

    private static int[] merge(int[] nums1, int[] nums2) {
        int[] res = new int[nums1.length + nums2.length];
        for (int i = 0; i < nums1.length; i++) {
            res[i] = nums1[i];
        }
        for (int i = 0; i < nums2.length; i++) {
            res[i + nums1.length] = nums2[i];
        }
        return res;
    }
}
