package com.company.middium;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Test3 {
    public static void main(String[] args) {
        List<Integer> lists = new LinkedList<>();
        lists.add(1);
        lists.add(2);
        lists.add(3);
        System.out.println(parse(lists));

    }

    // 将[1, 2, 3]解析出来 123数字
    private static int parse(List<Integer> lists) {
        StringBuilder sb = new StringBuilder();
        int left = 0;
        while (left < lists.size()) {
            sb.append(lists.get(left));
            left++;
        }
        return Integer.parseInt(sb.toString());
    }
}
