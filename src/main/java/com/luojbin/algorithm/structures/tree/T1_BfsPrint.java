package com.luojbin.algorithm.structures.tree;

import com.luojbin.algorithm.structures.tree.node.TreeNode;
import org.junit.jupiter.api.Test;

public class T1_BfsPrint {
    @Test
    public void test() {
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
        TreeNode t1 = new TreeNode(1);
        // 2
        TreeNode t2 = new TreeNode(2);
        TreeNode t3 = new TreeNode(3);
        TreeNode t4 = new TreeNode(4);
        t1.firstChild = t2;
        t2.nextSibling = t3;
        t3.nextSibling = t4;

        // 3-1
        TreeNode t5 = new TreeNode(5);
        t2.firstChild = t5;
        TreeNode t6 = new TreeNode(6);
        t4.firstChild = t6;
        // 3-2
        TreeNode t7 = new TreeNode(7);
        TreeNode t8 = new TreeNode(8);
        t5.firstChild = t7;
        t7.nextSibling = t8;
        TreeNode t9 = new TreeNode(9);
        t6.firstChild = t9;

        t1.levelOrderPrint();
    }
}

