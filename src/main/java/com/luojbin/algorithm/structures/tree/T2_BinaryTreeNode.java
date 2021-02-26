package com.luojbin.algorithm.structures.tree;

import lombok.Data;

@Data
public class T2_BinaryTreeNode<T> {

    private T element;
    private T2_BinaryTreeNode<T> left;
    private T2_BinaryTreeNode<T> right;

    public T2_BinaryTreeNode(T element) {
        this.element = element;
    }

    public T2_BinaryTreeNode(T element, T2_BinaryTreeNode<T> left, T2_BinaryTreeNode<T> right) {
        this.element = element;
        this.left = left;
        this.right = right;
    }
}
