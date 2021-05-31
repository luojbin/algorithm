package com.luojbin.algorithm.leetcode.basic;

public class ListNode<T> {
    public T val;
    public ListNode<T> next;

    public ListNode() {
    }

    public ListNode(T val) {
        this.val = val;
    }

    public ListNode(T val, ListNode<T> next) {
        this.val = val;
        this.next = next;
    }

    public static <T> void print(ListNode<T> node) {
        if (node == null) {
            System.out.println("[]");
            return;
        }
        while  (node.next != null){
            System.out.print(node.val);
            System.out.print(",");
            if (node.next != null) {
                node = node.next;
            }
        }
        System.out.print(node.val);
    }

    public static <T> ListNode<T> buildNodeList(T[] nodes) {
        if (nodes == null || nodes.length == 0) {
            return null;
        }
        ListNode<T> node = new ListNode<>(nodes[0]);
        ListNode<T> begin = node;

        for (int i = 1; i < nodes.length; i++) {
            ListNode<T> nextNode = new ListNode<>(nodes[i]);
            node.next = nextNode;
            node = nextNode;
        }
        return begin;
    }
}
