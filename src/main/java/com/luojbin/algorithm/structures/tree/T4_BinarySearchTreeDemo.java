package com.luojbin.algorithm.structures.tree;

import com.luojbin.algorithm.structures.tree.node.BinarySearchTree;
import com.luojbin.algorithm.structures.tree.node.BinaryTreeNode;
import org.junit.jupiter.api.Test;

public class T4_BinarySearchTreeDemo {

    @Test
    public void testBinarySearchTree() {
        BinaryTreeNode<Integer> demoTreeRoot = getDemoTreeRoot();
        BinarySearchTree<Integer> demoTree = new BinarySearchTree<>(demoTreeRoot);

        // 测试 contains
        System.out.println(demoTree.contains(6));
        System.out.println(demoTree.contains(2));
        System.out.println(demoTree.contains(13));

        // 测试找最值
        System.out.println(demoTree.findMax());
        System.out.println(demoTree.findMin());

        // 测试插入
        demoTree.insert(13);
        System.out.println("插入成功: " + demoTree.contains(13));
        // 测试插入
        demoTree.insert2(15);
        System.out.println("插入成功: " + demoTree.contains(15));
        demoTree.insert2(14);
        System.out.println("插入成功: " + demoTree.contains(14));

        // 空树插入
        BinarySearchTree<Integer> emptyTree = new BinarySearchTree<>(null);
        emptyTree.insert2(1);
        System.out.println("空树插入成功: " + emptyTree.contains(1));
        // 空树插入
        BinarySearchTree<Integer> emptyTree2 = new BinarySearchTree<>(null);
        emptyTree2.insert(18);
        System.out.println("空树插入成功: " + emptyTree2.contains(18));

        // 测试删除
        System.out.println("测试删除");
        // 1: 无子树的节点
        demoTree = new BinarySearchTree<>(getDemoTreeRoot());
        demoTree.remove(1);
        System.out.println(demoTree.contains(1));
        // 2: 只有左子树的节点
        demoTree = new BinarySearchTree<>(getDemoTreeRoot());
        demoTree.remove(2);
        System.out.println(demoTree.contains(2));
        // 3: 只有右子树的节点
        demoTree = new BinarySearchTree<>(getDemoTreeRoot());
        demoTree.remove(8);
        System.out.println(demoTree.contains(8));
        // 4: 有左右子树的节点
        demoTree = new BinarySearchTree<>(getDemoTreeRoot());
        demoTree.remove(10);
        System.out.println(demoTree.contains(10));
    }

    private BinaryTreeNode<Integer> getDemoTreeRoot() {
                /*
                       7
                    /    \
                  5       10
                 / \     /    \
                3   6   8      12
               / \       \     /
               2  4       9   11
              /
             1
         */
        BinaryTreeNode<Integer> n7 = new BinaryTreeNode<>(7);

        BinaryTreeNode<Integer> n5 = new BinaryTreeNode<>(5);
        BinaryTreeNode<Integer> n10 = new BinaryTreeNode<>(10);
        n7.setLeft(n5);
        n7.setRight(n10);

        BinaryTreeNode<Integer> n3 = new BinaryTreeNode<>(3);
        BinaryTreeNode<Integer> n6 = new BinaryTreeNode<>(6);
        n5.setLeft(n3);
        n5.setRight(n6);
        BinaryTreeNode<Integer> n8 = new BinaryTreeNode<>(8);
        BinaryTreeNode<Integer> n12 = new BinaryTreeNode<>(12);
        n10.setLeft(n8);
        n10.setRight(n12);

        BinaryTreeNode<Integer> n2 = new BinaryTreeNode<>(2);
        BinaryTreeNode<Integer> n4 = new BinaryTreeNode<>(4);
        n3.setLeft(n2);
        n3.setRight(n4);
        BinaryTreeNode<Integer> n9 = new BinaryTreeNode<>(9);
        BinaryTreeNode<Integer> n11 = new BinaryTreeNode<>(11);
        n8.setRight(n9);
        n12.setLeft(n11);

        BinaryTreeNode<Integer> n1 = new BinaryTreeNode<>(1);
        n2.setLeft(n1);
        return n7;
    }
}
