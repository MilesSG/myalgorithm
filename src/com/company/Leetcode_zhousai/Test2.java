package com.company.Leetcode_zhousai;

// 周赛： 2384. 最大回文数字
// https://leetcode.cn/problems/largest-palindromic-number/
class Text2 {
    public String largestPalindromic(String num) {
        // 定义暂存数组
        int[] dig = new int[10];
        StringBuilder sb = new StringBuilder();
        // 统计各个数字出现的次数
        for (int i = 0; i < num.length(); i++) {
            dig[num.charAt(i) - '0']++;
        }
        // 从9开始遍历各个数字
        for (int i = 9; i >= 0; i--) {
            // 如果是偶数个，依次一个i，0最后填入
            if ((dig[i] > 0 && dig[i] % 2 == 0 && i != 0) ||
                    (i == 0 && sb.length() != 0 && dig[i] > 0 && dig[i] % 2 == 0)) {
                while (dig[i] != 0) {
                    sb.append(i);
                    dig[i] -= 2;
                }
            }
            // 如果是奇数个，依次填入i，直到为1，0最后填入
            if ((dig[i] > 1 && dig[i] % 2 == 1 && i != 0) ||
                    (i == 0 && sb.length() != 0 && dig[i] > 1 && dig[i] % 2 == 1)) {
                while (dig[i] != 1) {
                    sb.append(i);
                    dig[i] -= 2;
                }
            }
        }
        // 反转字符串
        StringBuilder sReverse = new StringBuilder(sb).reverse();
        // 填入单个的数字
        for (int i = 9; i >= 0; i--) {
            if (dig[i] == 1) {
                sb.append(i);
                break;
            }
        }
        // 加上之前反转的字符串
        sb.append(sReverse);
        return sb.length() == 0 ? "0" : sb.toString();
    }
}