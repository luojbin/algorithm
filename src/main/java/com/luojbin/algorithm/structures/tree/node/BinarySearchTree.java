package com.luojbin.algorithm.structures.tree.node;

import lombok.Data;
import org.junit.jupiter.api.Test;

/**
 * 二叉查找树, 基于二叉树基本节点
 * 基本节点维护节点及左右子树的关系, 本类提供二叉查找树的特殊功能方法
 */
public class BinarySearchTree<K extends Comparable<K>> {

    private BinaryTreeNode<K> root;

    public BinarySearchTree(BinaryTreeNode<K> root) {
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
    public boolean contains(K x) {
        if (x == null) {
            return false;
        }
        return contains(x, root);
    }
    private boolean contains(K x, BinaryTreeNode<K> node) {
        // 如果当前节点为空
        if (node == null) {
            return false;
        }
        // 由于二叉查找树是有序的, 可通过与当前节点比较大小, 判断需要在哪侧子树继续查找
        int compareResult = x.compareTo(node.getElement());
        if (compareResult == 0) {
            // 与当前节点相同, 返回true;
            return true;
        } else if (compareResult > 0) {
            // x 比当前节点大, 在右子树继续寻找
            return contains(x,node.getRight());
        } else {
            // x 比当前节点小, 在左子树继续寻找
            return contains(x, node.getLeft());
        }
    }
    //endregion

    //region 查找最值
    /**
     * 获取最大值的驱动方法
     */
    public K findMax() {
        return findMax(root);
    }
    /**
     * 以递归的方式获取最大值
     */
    private K findMax(BinaryTreeNode<K> node) {
        if (node.getRight() == null) {
            return node.getElement();
        }
        return findMax(node.getRight());
    }

    /**
     * 获取最小值的驱动方法
     */
    public K findMin() {
        return findMin(root);
    }
    /**
     * 以循环的形式获取最小值
     */
    private K findMin(BinaryTreeNode<K> root) {
        BinaryTreeNode<K> current = root;
        while (current.getLeft() != null) {
            current = current.getLeft();
        }
        return current.getElement();
    }
    //endregion

    //region 插入元素

    /**
     * 插入操作的驱动方法
     */
    public void insert(K data) {
        root = insert(data, root);
    }
    /**
     * 具体的插入逻辑
     * 插入元素即新建节点后, 需要将该节点添加到树中, 因此需要返回值
     */
    private BinaryTreeNode<K> insert(K data, BinaryTreeNode<K> tree) {
        // 如果当前的树为空, 新建一个节点作为树
        if (tree == null) {
            return new BinaryTreeNode<>(data, null, null);
        }
        // 当前节点非空, 比较大小
        int compareResult = data.compareTo(tree.getElement());
        if (compareResult > 0) {
            // 新元素比当前节点大, 应添加到右子树
            tree.setRight(insert(data, tree.getRight()));
        } else if (compareResult < 0) {
            // 新元素比当前节点小, 添加到左子树
            tree.setLeft(insert(data, tree.getLeft()));
        } else {
            // 重复元素, 不做操作
        }
        return tree;
    }

    /**
     * 插入操作的驱动方法
     */
    public void insert2(K data) {
        if (root == null) {
            root = new BinaryTreeNode<>(data, null, null);
        } else {
            insert2(data, root);
        }
    }
    private void insert2(K data, BinaryTreeNode<K> tree) {
        // 当前节点非空, 比较大小
        int compareResult = data.compareTo(tree.getElement());
        if (compareResult > 0) {
            // 新元素比当前节点大, 应添加到右子树
            BinaryTreeNode<K> right = tree.getRight();
            if (right == null) {
                tree.setRight(new BinaryTreeNode<>(data, null, null));
            } else {
                insert2(data, right);
            }
        } else if (compareResult < 0) {
            // 新元素比当前节点大, 应添加到右子树
            BinaryTreeNode<K> left = tree.getRight();
            if (left == null) {
                tree.setLeft(new BinaryTreeNode<>(data, null, null));
            } else {
                insert2(data, left);
            }
        } else {
            // 重复元素, 不做操作
        }
    }
    //endregion

    public void remove(K x) {
        root = remove(x, root);
    }

    private BinaryTreeNode<K> remove(K x, BinaryTreeNode<K> t) {
        if (t == null) {
            return t;
        }
        int compareResult = x.compareTo(t.getElement());
        if (compareResult > 0) {
            // 要删除的节点在右子树
            t.setRight(remove(x, t.getRight()));
        } else if (compareResult < 0) {
            // 要删除的节点在左子树
            t.setLeft(remove(x, t.getLeft()));
        } else if (t.getLeft() != null && t.getRight() != null){
            // 要删除的是当前节点, 且当前节点有左右两个子树
            // 取右子树中最小的值作为当前节点的新值, 覆盖原来的值, 则要删除的数据已经被删除
            t.setElement(findMin(t.getRight()));
            // 在右子树中删掉原来的最小值
            t.setRight(remove(t.getElement(), t.getRight()));
        } else {
            // 要删除的是当前节点, 且当前节点无子树/有一棵子树
            if (t.getLeft() != null) {
                // 当前节点有左子树, 将左子树上提一级即可
                t = t.getLeft();
            } else {
                // 当前节点无左子树, 将右子树上提一级即可(右子树可能也是null)
                t = t.getRight();
            }
        }
        return t;
    }
}
