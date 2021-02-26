package com.luojbin.algorithm.structures.tree;

public class T4_AvlTree<T extends Comparable<T>> {

    private T2_BinaryTreeNode<T> root;

    // 构造函数
    public T4_AvlTree(T2_BinaryTreeNode<T> root) {
        this.root = root;
    }

    //region 空树判断
    public void makeEmpty() {
        this.root = null;
    }
    public boolean isEmpty() {
        return this.root == null;
    }
    //endregion

    //region 查找元素
    public boolean contains(T x) {
        if (x == null) {
            return false;
        }
        return contains(x, root);
    }
    private boolean contains(T x, T2_BinaryTreeNode<T> node) {
        // 是否当前节点
        if (x.equals(node.getElement())) {
            return true;
        }
        boolean result = false;
        // 在左子树查找
        if (node.getLeft() != null) {
            result = contains(x, node.getLeft());
        }
        // 在右子树查找
        if (!result && node.getRight() != null) {
            result = contains(x, node.getRight());
        }
        return result;
    }
    //endregion

    public T findMax() {
        return findMax(root);
    }
    private T findMax(T2_BinaryTreeNode<T> node) {
        if (node.getRight() == null) {
            return node.getElement();
        }
        return findMax(node.getRight());
    }
    public T findMin() {
        T2_BinaryTreeNode<T> current = root;
        while (current.getLeft() != null) {
            current = current.getLeft();
        }
        return current.getElement();
    }


    public static void main(String[] args) {
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
        T2_BinaryTreeNode<Integer> n7 = new T2_BinaryTreeNode<>(7);


        T2_BinaryTreeNode<Integer> n5 = new T2_BinaryTreeNode<>(5);
        T2_BinaryTreeNode<Integer> n10 = new T2_BinaryTreeNode<>(10);
        n7.setLeft(n5);
        n7.setRight(n10);

        T2_BinaryTreeNode<Integer> n3 = new T2_BinaryTreeNode<>(3);
        T2_BinaryTreeNode<Integer> n6 = new T2_BinaryTreeNode<>(6);
        n5.setLeft(n3);
        n5.setRight(n6);
        T2_BinaryTreeNode<Integer> n8 = new T2_BinaryTreeNode<>(8);
        T2_BinaryTreeNode<Integer> n12 = new T2_BinaryTreeNode<>(12);
        n10.setLeft(n8);
        n10.setRight(n12);

        T2_BinaryTreeNode<Integer> n2 = new T2_BinaryTreeNode<>(2);
        T2_BinaryTreeNode<Integer> n4 = new T2_BinaryTreeNode<>(4);
        n3.setLeft(n2);
        n3.setRight(n4);
        T2_BinaryTreeNode<Integer> n9 = new T2_BinaryTreeNode<>(9);
        T2_BinaryTreeNode<Integer> n11 = new T2_BinaryTreeNode<>(11);
        n8.setRight(n9);
        n12.setLeft(n11);

        T2_BinaryTreeNode<Integer> n1 = new T2_BinaryTreeNode<>(1);
        n2.setLeft(n1);

        T4_AvlTree<Integer> tree = new T4_AvlTree<>(n7);
        System.out.println(tree.contains(6));
        System.out.println(tree.contains(2));
        System.out.println(tree.contains(13));

        System.out.println(tree.findMax());
        System.out.println(tree.findMin());
    }
}

