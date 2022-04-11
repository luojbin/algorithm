package com.luojbin.algorithm.structures.tree.node;

public class AvlTreeNode<T extends Comparable<T>> {

    private BinaryTreeNode<T> root;

    // 构造函数
    public AvlTreeNode(BinaryTreeNode<T> root) {
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
    private boolean contains(T x, BinaryTreeNode<T> node) {
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
    private T findMax(BinaryTreeNode<T> node) {
        if (node.getRight() == null) {
            return node.getElement();
        }
        return findMax(node.getRight());
    }
    public T findMin() {
        BinaryTreeNode<T> current = root;
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

        AvlTreeNode<Integer> tree = new AvlTreeNode<>(n7);
        System.out.println(tree.contains(6));
        System.out.println(tree.contains(2));
        System.out.println(tree.contains(13));

        System.out.println(tree.findMax());
        System.out.println(tree.findMin());
    }
}

