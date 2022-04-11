package com.luojbin.algorithm.structures.tree.node;

import lombok.Data;

/**
 * AVL树节点
 */
@Data
public class AvlNode<T extends Comparable<T>>{

    /**
     * avl树节点, 比普通的二叉树节点多一个高度信息(该节点到叶子节点的距离)
     */
    private int height;
    private T element;
    private AvlNode<T> left;
    private AvlNode<T> right;

    public AvlNode(T element) {
        this(element, null, null);
    }

    public AvlNode(T element, AvlNode<T> left, AvlNode<T> right) {
        this.element = element;
        this.left = left;
        this.right = right;
        this.height = 0;
    }
}

