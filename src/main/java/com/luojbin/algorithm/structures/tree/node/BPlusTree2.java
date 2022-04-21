package com.luojbin.algorithm.structures.tree.node;

import java.util.LinkedList;
import java.util.List;

public class BPlusTree2 {

    /** 叶子节点的记录数 */
    final int L;
    final int halfL;
    /** 非叶子节点的分叉数 */
    final int M;
    final int halfM;
    /** 根节点, 可能是叶子节点, 也可能是非叶节点 */
    Node root;

    /** 构造函数, 需要指定 M 和 L, 且需要大于3, 否则退化成二叉树 */
    public BPlusTree2(int m, int l) {
        if (m < 3) {
            throw new RuntimeException("M 不能小于3");
        }
        if (l < 3) {
            throw new RuntimeException("L 不能小于3");
        }
        this.M = m;
        this.L = l;
        this.halfM = (this.M + 1) / 2;
        this.halfL = (this.L + 1) / 2;
    }

    //region 空树判断
    public void makeEmpty() {
        this.root = null;
    }

    public boolean isEmpty() {
        return this.root == null;
    }
    //endregion

    public boolean add(int x) {
        if (root == null) {
            root = new Node(M, L, true);
        } else {
            Node newNode = add(x, root);
            if (newNode != null) {
                // 如果返回了新节点,
                Node newRoot = new Node(M, L, false);

            }
        }

        return true;
    }

    /**
     * 将元素添加到目标子树, 若需要分裂, 则返回新节点, 否则返回空
     *
     * @param x
     * @param node
     * @return
     */
    private Node add(int x, Node node) {
        Node newNode = null;
        Node newSubNode = null;
        if (node.leafNode) {
            // 如果是叶子节点, 插入到当前节点
            if (node.values.size() >= L) {
                // TODO 已经满了, 需要分裂

            } else {
                // 未满, 直接插入
                node.values.add(x);
            }

        } else {
            // 如果是非叶子节点, 遍历比较关键字, 找到下一级节点再插入
            List<Integer> keys = node.keys;
            boolean found = false;
            int i = 0;
            for (; i < keys.size(); i++) {
                Integer key = keys.get(i);
                if (x < key) {
                    found = true;
                    newSubNode = add(x, node.children.get(i));
                    break;
                }
            }
            if (!found) {
                // 如果没有找到比x大的key, 将x添加到最后一片子节点
                newSubNode = add(x, node.children.get(i);
            }
            // 如果插入返回了新节点, 判断本节点需不需要分裂
            if (newSubNode != null) {
                if (node.children.size() == M) {
                    // 满了, 分裂本节点
                    newNode = new Node(M, L, false);
                    // 将原node的后半的key, 转移到新node中
                    for (int j = 0; j < halfM; j++) {
                        // 若 M 为偶数, 如 6, halfM = 7/2 = 3, 从3开始移
                        // 若 M 为奇数, 如 7, halfM = 8/2 = 4, 从4开始移
                        int nodeIdx = j + halfM;

                        // 将key 从原节点移到新节点
                        int key = node.keys.get(nodeIdx);
                        newNode.keys.add(key);
                        node.keys.remove(key);

                        // 将子节点从原节点移到新节点
                        Node child = node.children.get(nodeIdx);
                        newNode.children.add(child);
                        node.children.remove(child);
                    }

                } else {
                    // 未满, 本节点不需要分裂, 只需要添加新的关键字和新的子节点
                    int newKey = newSubNode.leafNode ? newSubNode.values.get(0) : newSubNode.keys.get(0);
                    node.keys.add(newKey);
                    node.children.add(newSubNode);
                }
            }
        }

        return newNode;
    }

    public boolean remove(int x) {
        // 删除后仍半满, 直接删除
        // 删除后不半满, 从相邻节点领养, 或者递归地合并相邻节点, 最终可能导致树的高度降低
        return false;
    }

    public static class Node {
        // 是否叶子节点
        final boolean leafNode;

        // 用作非叶子节点时的子节点数
        final int M;
        final int halfM;
        // 用作非叶子节点时, 记录下级节点的数组
        List<Node> children;
        // 用作非叶子节点时, 记录关键字的数组
        List<Integer> keys;


        // 用作叶子节点时的容量
        final int L;
        final int halfL;
        // 用作叶子节点时, 记录具体数据
        List<Integer> values;


        Node(int m, int l, boolean leafNode) {
            this.M = m;
            this.L = l;
            this.leafNode = leafNode;
            if (leafNode) {
                // 叶子节点, 创建具体数据的数组
                this.values = new LinkedList<>();
            } else {
                // 如果不是叶子节点, 创建关键字和子节点数组
                this.children = new LinkedList<>();
                this.keys = new LinkedList<>();
            }
        }
    }

}
