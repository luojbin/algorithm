package com.luojbin.algorithm.structures.tree.node;

/**
 * AVL 树, 基于二叉树查找树
 * 本类提供 AVL 树的特殊功能方法
 */
public class AvlTreeNode<K extends Comparable<K>> extends BinarySearchTree<K>{

    public AvlTreeNode(BinaryTreeNode<K> root) {
        super(root);
    }



}

