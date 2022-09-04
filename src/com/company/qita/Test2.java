package com.company.qita;

/**
 * 369. 给单链表加一
 * https://leetcode.cn/problems/plus-one-linked-list/
 */
public class Test2 {

    public static class ListNode {
        long val;
        ListNode next;

        public ListNode() {
        }

        public ListNode(long val) {
            this.val = val;
        }

        public ListNode(long val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    public static void main(String[] args) {
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        node1.next = node2;
        node2.next = node3;
        System.out.println(plusOne(node1));
    }

    public static ListNode plusOne(ListNode head) {
        ListNode cur = head;
        String str = "";
        while (cur != null) {
            str += (cur.val + "");
            cur = cur.next;
        }
        int tmp = Integer.parseInt(str) + 1;

        char[] chars = new char[getLen(tmp)];
        String strr = tmp + "";
        for (int i = 0; i < chars.length; i++) {
            chars[i] = strr.charAt(i);
        }

//        `ListNode first = new ListNode(Integer.parseInt(chars[0] + ""));
//        first.next = new ListNode(Integer.parseInt(chars[1] + ""));
//        first.next.next = new ListNode(Integer.parseInt(chars[2] + ""));`

        ListNode dummy = new ListNode(-1);
        ListNode pre = dummy;
        int i = 0;
        while (i < chars.length) {
            ListNode linShi = new ListNode(Long.parseLong(chars[i] + ""));
            pre.next = linShi;
            pre = pre.next;
            i++;

        }

        return dummy.next;
    }

    // 用于获取一个整数的长度，比如整数124长度是3
    private static int getLen(int num) {
        String str = String.valueOf(num);
        return str.length();
    }
}






























