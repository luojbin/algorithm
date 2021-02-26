package com.luojbin.algorithm.structures.tree;

import lombok.Data;

import java.util.ArrayDeque;
import java.util.Deque;

@Data
public class T1_TreeNode<T> {
    T element;
    T1_TreeNode<T> firstChild;
    T1_TreeNode<T> nextSibling;

    public T1_TreeNode(T data) {
        this.element = data;
    }

    public static <N> void levelOrderPrint(T1_TreeNode<N> tree) {
        // 需要两个队列, 一个是当前要打印的层级, 另一个是缓存下一层的节点
        Deque<T1_TreeNode<N>> currentLevel = new ArrayDeque<>();
        Deque<T1_TreeNode<N>> subLevel = new ArrayDeque<>();

        currentLevel.offer(tree);
        levelOrderPrint(currentLevel, subLevel);
    }

    private static <N> void levelOrderPrint(Deque<T1_TreeNode<N>> currentLevel, Deque<T1_TreeNode<N>> subLevel) {
        T1_TreeNode<N> subNode;
        while (currentLevel.size() > 0) {
            while (currentLevel.peek() != null) {
                T1_TreeNode<N> currentNode = currentLevel.poll();
                if (currentNode.firstChild != null) {
                    subNode = currentNode.firstChild;
                    subLevel.offer(subNode);
                    while (subNode.nextSibling != null) {
                        subNode = subNode.nextSibling;
                        subLevel.offer(subNode);
                    }
                }
                System.out.println(currentNode);
            }
            currentLevel = subLevel;
            subLevel = new ArrayDeque<>();
        }
    }

    @Override
    public String toString() {
        return String.valueOf(element);
    }

    public static void main(String[] args) {
        /*
                   1
                  /
                 2 -- 3 -- 4
                /         /
               5         6
              /         /
             7 -- 8    9
         */

        // 1
        T1_TreeNode<Integer> t1 = new T1_TreeNode<>(1);
        // 2
        T1_TreeNode<Integer> t2 = new T1_TreeNode<>(2);
        T1_TreeNode<Integer> t3 = new T1_TreeNode<>(3);
        T1_TreeNode<Integer> t4 = new T1_TreeNode<>(4);
        t1.firstChild = t2;
        t2.nextSibling = t3;
        t3.nextSibling = t4;

        // 3-1
        T1_TreeNode<Integer> t5 = new T1_TreeNode<>(5);
        t2.firstChild = t5;
        T1_TreeNode<Integer> t6 = new T1_TreeNode<>(6);
        t4.firstChild = t6;
        // 3-2
        T1_TreeNode<Integer> t7 = new T1_TreeNode<>(7);
        T1_TreeNode<Integer> t8 = new T1_TreeNode<>(8);
        t5.firstChild = t7;
        t7.nextSibling = t8;
        T1_TreeNode<Integer> t9 = new T1_TreeNode<>(9);
        t6.firstChild = t9;

        levelOrderPrint(t1);
    }
}
