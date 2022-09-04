package com.company.Leetcode_zhousai;

import java.util.HashMap;
import java.util.LinkedList;

public class Test3 {
    public static void main(String[] args) {
        HashMap<Integer, Integer> map = new HashMap<>();
        LinkedList<Integer> list = new LinkedList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(4);
        list.add(5);
        for (Integer num : list) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        System.out.println(map);
    }
}
