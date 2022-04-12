package com.luojbin.algorithm.structures.tree;

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

        for (int j : array) {
            tree.remove(j);
        }
        System.out.println();
    }
}
