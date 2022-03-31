package com.luojbin.algorithm.leetcode.a5_twoPointer;

import com.luojbin.algorithm.leetcode.basic.ListNode;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class Q_876_MiddleOfTheLinkedList {

    @Test
    public void test() {
        // 构造参数与结果数组, 第一个参数是执行结果, 后面的是具体参数
        ListNode[] params = new ListNode[]{
                new ListNode(1, new ListNode(2, new ListNode<>(3, new ListNode<>(4, new ListNode<>(5))))),
                new ListNode(1, new ListNode(2, new ListNode<>(3, new ListNode<>(4, new ListNode<>(5, new ListNode<>(6))))))
        };
        // 循环测试
        for (ListNode p : params) {
            System.out.println("结果:" + middleNode2(p).val );
        }
    }

    /** 双指针 : 计数+结果 */
    public ListNode middleNode(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode middle = head;
        ListNode last = head;
        int count = 0;
        while(last.next != null) {
            if (count % 2 == 0) {
                middle = middle.next;

            }
            last = last.next;
            count++;
        }
        return middle;
    }
    /** 快慢指针: 快-计数, 慢-中点 */
    public ListNode middleNode2(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode middle = head;
        ListNode last = head;
        while(last != null && last.next != null) {
            middle = middle.next;
            last = last.next.next;
        }
        return middle;
    }
}
