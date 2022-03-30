package com.luojbin.algorithm.leetcode.a3_recrusive;

import com.luojbin.algorithm.leetcode.basic.ListNode;
import org.junit.jupiter.api.Test;

import java.util.List;

public class Q_021_MergeTwoSortedLists {
    @Test
    public void test() {
        // 1,2,4
        ListNode<Integer> list1 = new ListNode<>(1, new ListNode<>(2, new ListNode<>(4, null)));
        // 1,3,4
        ListNode<Integer> list2 = new ListNode<>(1, new ListNode<>(3, new ListNode<>(4, null)));
        ListNode<Integer> mergeList = mergeTwoLists(list1, list2);

        ListNode<Integer> node = mergeList;
        while (node.next != null) {
            System.out.println(node.val);
            node = node.next;
        }
        System.out.println(node.val);
    }

    public ListNode<Integer> mergeTwoLists(ListNode<Integer> list1, ListNode<Integer> list2) {
        if (list1 == null){
            return list2;
        } else if (list2 == null) {
            return list1;
        } else if (list1.val < list2.val) {
            // 如果 list1 当前节点的值小, 排在前面, 则 list1 的下一个, 是 list1 剩余部分与 list2 合并的结果
            list1.next = mergeTwoLists(list1.next, list2);
            return list1;
        } else {
            // 如果 list2 当前节点的值小, 排在前面, 则 list2 的下一个, 是 list1 与 list2 剩余部分合并的结果
            list2.next = mergeTwoLists(list1, list2.next);
            return list2;
        }
    }
}
