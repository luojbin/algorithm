package com.luojbin.algorithm.leetcode.Q001_100;

import com.luojbin.algorithm.leetcode.basic.ListNode;

public class Q_019_removeNthNode {

    public static void main(String[] args) {
        ListNode<Integer> listNode = ListNode.buildNodeList(new Integer[]{1,2,3,4,5,6,7});
        ListNode<Integer> result = removeNthFromEnd(listNode, 7);
        ListNode.print(result);

    }

    public static ListNode<Integer> removeNthFromEnd(ListNode<Integer> head, int n) {

        // 先跳过n个元素, 给 toRemove, beforeRemove 赋值
        ListNode<Integer> endNode = head;
        for (int i = 0; i < n-1; i++) {
            endNode = endNode.next;
        }
        ListNode<Integer> toRemove = head;
        ListNode<Integer> beforeRemove = null;

        // 继续遍历, 指针移动到最后一个
        while (endNode.next != null) {
            endNode = endNode.next;
            beforeRemove = toRemove;
            toRemove = toRemove.next;
        }

        // 调整链表
        if (beforeRemove == null) {
            // toRemove 是链表首个元素
            head = head.next;
        } else {
            beforeRemove.next = toRemove.next;
        }
        return head;
    }

}
