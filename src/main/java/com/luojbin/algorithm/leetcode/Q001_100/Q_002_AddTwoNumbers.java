package com.luojbin.algorithm.leetcode.Q001_100;


import com.luojbin.algorithm.leetcode.basic.ListNode;

public class Q_002_AddTwoNumbers {

    public static void main(String[] args) {
        ListNode<Integer> l1 = getListNode(123456);
        ListNode<Integer> l2 = getListNode(456);
        ListNode<Integer> result = addTwoNumbers(l1, l2);

        ListNode.print(result);
    }

    public static ListNode<Integer> addTwoNumbers(ListNode<Integer> l1, ListNode<Integer> l2) {
        int n1 = l1.val + l2.val;
        int temp = 0;
        if (n1 >= 10 ) {
            n1 = n1 - 10;
            temp = 1;
        }
        ListNode<Integer> node = new ListNode<>(n1);
        ListNode<Integer> begin = node;
        while (l1.next != null || l2.next != null || temp != 0) {
            int a = l1.next != null ? l1.next.val : 0;
            int b = l2.next != null ? l2.next.val : 0;
            int n = a + b + temp;
            temp = 0;
            if (n >= 10) {
                n = n - 10;
                temp = 1;
            }
            ListNode<Integer> nextNode = new ListNode<>(n);
            node.next = nextNode;
            node = nextNode;
            if (l1.next != null) {
                l1 =  l1.next;
            }
            if (l2.next != null) {
                l2 =  l2.next;
            }
        }
        return begin;
    }

    public static ListNode<Integer> getListNode(int input) {
        ListNode<Integer> node = new ListNode<>();
        ListNode<Integer> begin = node;
        // 按位分割一个整数
        while (input != 0) {
            node.val = input % 10;
            input = input / 10;
            if (input != 0) {
                node.next = new ListNode<>();
                node = node.next;
            }
        }
        return begin;
    }
}

