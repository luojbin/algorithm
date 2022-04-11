package com.luojbin.algorithm.structures.tree.node;

import lombok.Data;

@Data
public class BinaryTreeNode {

    private Object element;
    private BinaryTreeNode left;
    private BinaryTreeNode right;

    public BinaryTreeNode(Object element) {
        this.element = element;
    }

    public BinaryTreeNode(Object element, BinaryTreeNode left, BinaryTreeNode right) {
        this.element = element;
        this.left = left;
        this.right = right;
    }
}
