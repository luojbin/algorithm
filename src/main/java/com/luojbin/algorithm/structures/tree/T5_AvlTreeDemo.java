package com.luojbin.algorithm.structures.tree;

import com.luojbin.algorithm.structures.tree.node.AvlNode;
import com.luojbin.algorithm.structures.tree.node.AvlTree;
import org.junit.jupiter.api.Test;

public class T5_AvlTreeDemo {

    @Test
    public void testAvlTree() {
        int[] array = {
                3,2,1,
                4,5,6,7,
                16,15,14,13,12,11,10,
                8,9
        };
        AvlTree<Integer> tree = new AvlTree<>();
        for (int i : array) {
            tree.insert(i);
        }
        System.out.println();
    }

    private AvlNode<Integer> getDemoTreeRoot() {
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
        AvlNode<Integer> n7 = new AvlNode<>(7);

        AvlNode<Integer> n5 = new AvlNode<>(5);
        AvlNode<Integer> n10 = new AvlNode<>(10);
        n7.setLeft(n5);
        n7.setRight(n10);

        AvlNode<Integer> n3 = new AvlNode<>(3);
        AvlNode<Integer> n6 = new AvlNode<>(6);
        n5.setLeft(n3);
        n5.setRight(n6);
        AvlNode<Integer> n8 = new AvlNode<>(8);
        AvlNode<Integer> n12 = new AvlNode<>(12);
        n10.setLeft(n8);
        n10.setRight(n12);

        AvlNode<Integer> n2 = new AvlNode<>(2);
        AvlNode<Integer> n4 = new AvlNode<>(4);
        n3.setLeft(n2);
        n3.setRight(n4);
        AvlNode<Integer> n9 = new AvlNode<>(9);
        AvlNode<Integer> n11 = new AvlNode<>(11);
        n8.setRight(n9);
        n12.setLeft(n11);

        AvlNode<Integer> n1 = new AvlNode<>(1);
        n2.setLeft(n1);
        return n7;
    }
}
