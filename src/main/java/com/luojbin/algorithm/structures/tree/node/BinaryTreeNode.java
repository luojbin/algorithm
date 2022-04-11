package com.luojbin.algorithm.structures.tree.node;

import lombok.Data;

/**
 * 二叉树的基本节点
 */
@Data
public class BinaryTreeNode<T> {

    private T element;
    private BinaryTreeNode<T> left;
    private BinaryTreeNode<T> right;

    public BinaryTreeNode(T element) {
        this.element = element;
    }

    public BinaryTreeNode(T element, BinaryTreeNode<T> left, BinaryTreeNode<T> right) {
        this.element = element;
        this.left = left;
        this.right = right;
    }
}
