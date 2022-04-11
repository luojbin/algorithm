package com.luojbin.algorithm.structures.tree.node;

public class AvlTreeNode {

    private BinaryTreeNode root;

    // 构造函数
    public AvlTreeNode(BinaryTreeNode root) {
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
    public boolean contains(Object x) {
        if (x == null) {
            return false;
        }
        return contains(x, root);
    }
    private boolean contains(Object x, BinaryTreeNode node) {
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

    public Object findMax() {
        return findMax(root);
    }
    private Object findMax(BinaryTreeNode node) {
        if (node.getRight() == null) {
            return node.getElement();
        }
        return findMax(node.getRight());
    }
    public Object findMin() {
        BinaryTreeNode current = root;
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
        BinaryTreeNode n7 = new BinaryTreeNode(7);


        BinaryTreeNode n5 = new BinaryTreeNode(5);
        BinaryTreeNode n10 = new BinaryTreeNode(10);
        n7.setLeft(n5);
        n7.setRight(n10);

        BinaryTreeNode n3 = new BinaryTreeNode(3);
        BinaryTreeNode n6 = new BinaryTreeNode(6);
        n5.setLeft(n3);
        n5.setRight(n6);
        BinaryTreeNode n8 = new BinaryTreeNode(8);
        BinaryTreeNode n12 = new BinaryTreeNode(12);
        n10.setLeft(n8);
        n10.setRight(n12);

        BinaryTreeNode n2 = new BinaryTreeNode(2);
        BinaryTreeNode n4 = new BinaryTreeNode(4);
        n3.setLeft(n2);
        n3.setRight(n4);
        BinaryTreeNode n9 = new BinaryTreeNode(9);
        BinaryTreeNode n11 = new BinaryTreeNode(11);
        n8.setRight(n9);
        n12.setLeft(n11);

        BinaryTreeNode n1 = new BinaryTreeNode(1);
        n2.setLeft(n1);

        AvlTreeNode tree = new AvlTreeNode(n7);
        System.out.println(tree.contains(6));
        System.out.println(tree.contains(2));
        System.out.println(tree.contains(13));

        System.out.println(tree.findMax());
        System.out.println(tree.findMin());
    }
}

