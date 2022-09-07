package com.company.easy;

/**
 * 125. 验证回文串
 */
public class Test1 {
    public static void main(String[] args) {
        String str = "0P";
        System.out.println(new Test1().isPalindrome(str));
    }
    public boolean isPalindrome(String s) {
        String str = merge(s);
        char[] chars = str.toCharArray();
        int left = 0;
        int right = chars.length - 1;
        while (left < right) {
            if (Character.toUpperCase(str.charAt(left)) != Character.toUpperCase(str.charAt(right))) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }

    // 移除掉空格、逗号这些字符 合成一整个String字符串
    private static String merge(String str) {
        StringBuilder sb = new StringBuilder();
        char[] chars = str.toCharArray();
        for (Character c : chars) {
            if ((Character.toUpperCase(c) >= 'A' && Character.toUpperCase(c) <= 'Z')
                    || (c >= '0' && c <= '9')) {
                sb.append(c);
            }
        }
        return sb.toString();
    }
}
