package com.company.easy;

/**
 * title: 168. Excel表列名称
 *
 * @author: Miles
 * @date: 2022/9/7
 */
public class Test2 {
    public static void main(String[] args) {

    }

    public String convertToTitle(int columnNumber) {
        StringBuilder sb = new StringBuilder();
        while (columnNumber > 0) {
            columnNumber--;
            sb.append((char) (columnNumber % 26 + 'A'));
            columnNumber /= 26;
        }
        sb.reverse();
        return sb.toString();
    }
}
