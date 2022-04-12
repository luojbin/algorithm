package com.luojbin.algorithm.structures.tree.node;

/**
 * AVL 树, 是特殊的二叉查找树, 需要满足平衡条件
 */
public class AvlTree<K extends Comparable<K>>{

    /**
     * 左右子树允许的高度差
     */
    private static final int IMBALANCE = 1;
    private AvlNode<K> root;

    public AvlTree() {
    }
    public AvlTree(AvlNode<K> root) {
        this.root = root;
    }

    // region 二叉查找树的基本方法
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
    private boolean contains(K x, AvlNode<K> node) {
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
    private K findMax(AvlNode<K> node) {
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
    private K findMin(AvlNode<K> root) {
        AvlNode<K> current = root;
        while (current.getLeft() != null) {
            current = current.getLeft();
        }
        return current.getElement();
    }
    //endregion
    // endregion

    private int getHeight(AvlNode<K> node){
        return node == null ? -1 : node.getHeight();
    }

    //region AVL插入元素
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
    private AvlNode<K> insert(K data, AvlNode<K> tree) {
        // 如果当前的树为空, 新建一个节点作为树
        if (tree == null) {
            return new AvlNode<>(data, null, null);
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
        // dev_note 插入节点后重新平衡, 由于在递归中处理平衡, 会从最底层开始检验平衡
        return balance(tree);
    }

    //region 平衡与旋转
    /**
     * 对子树进行平衡操作, 返回平衡后的新子树
     * @param tree 要平衡的子树
     * @return 平衡后的子树
     */
    private AvlNode<K> balance(AvlNode<K> tree){
        // 具体的平衡操作
        if (tree == null) {
            // 空树不用平衡, 直接返回
            return tree;
        }
        if (getHeight(tree.getLeft()) - getHeight(tree.getRight()) > IMBALANCE) {
            // 左子树比右子树高, 新节点添加到左子树了
            if (getHeight(tree.getLeft().getLeft()) >= getHeight(tree.getLeft().getRight())) {
                // 添加到左子树的左子树, 外侧, 单旋转
                tree = rotateWithLeftChild(tree);
            } else {
                // 添加到左子树的右子树, 内测, 双旋转
                tree = doubleRotateWithLeftChild(tree);
            }
        } else if (getHeight(tree.getRight()) - getHeight(tree.getLeft()) > IMBALANCE) {
            // 右子树比左子树高, 新节点添加到右子树了
            if (getHeight(tree.getRight().getRight()) >= getHeight(tree.getRight().getLeft())) {
                // 添加到右子树的右子树, 外侧, 单旋转
                tree = rotateWithRightChild(tree);
            } else {
                // 添加到右子树的左子树, 内测, 双旋转
                tree = doubleRotateWithRightChild(tree);
            }
        } else {
            // 左右子树高度差在允许范围内, 不用平衡
        }
        tree.setHeight(Math.max(getHeight(tree.getLeft()), getHeight(tree.getRight())) + 1);
        return tree;
    }

    /**
     * 左子树单旋转, 左子树根节点作为新的子树根
     *           k2             k1
     *          /  \           /  \
     *        k1    Z  --->   X   k2
     *      /   \                /  \
     *     X     Y              Y    Z
     *
     * @param k2 要旋转的子树
     */
    private AvlNode<K> rotateWithLeftChild(AvlNode<K> k2) {
        // 左子树根节点k2作为新的子树根节点
        AvlNode<K> k1 = k2.getLeft();
        // 将k2的右子树Y, 挂到k1的左侧
        k2.setLeft(k1.getRight());
        // 将k1 挂到k2的右侧
        k1.setRight(k2);
        // 更新高度, 各自子树高度+1
        k2.setHeight(Math.max(getHeight(k2.getLeft()), getHeight(k2.getRight())) + 1);
        k1.setHeight(Math.max(getHeight(k1.getLeft()), getHeight(k1.getRight())) + 1);
        // 返回新的子树
        return k1;
    }
    /**
     * 右子树单旋转
     * 右子树单旋转, 左子树根节点作为新的子树根
     *        k1                 k2
     *       /  \               /  \
     *      X   k2     --->   k1    Z
     *         /  \          /  \
     *        Y    Z        X    Y
     *
     * @param k1 要旋转的子树
     */
    private AvlNode<K> rotateWithRightChild(AvlNode<K> k1) {
        // 左子树根节点k2作为新的子树根节点
        AvlNode<K> k2 = k1.getRight();
        // 将k2的右子树Y, 挂到k1的右侧
        k1.setRight(k2.getLeft());
        // 将k1 挂到k2的右侧
        k2.setLeft(k1);
        // 更新高度, 各自子树高度+1
        k2.setHeight(Math.max(getHeight(k2.getLeft()), getHeight(k2.getRight())) + 1);
        k1.setHeight(Math.max(getHeight(k1.getLeft()), getHeight(k1.getRight())) + 1);
        // 返回新的子树
        return k2;
    }
    /**
     * 左子树双旋转: 先左子树右侧单旋转, 再本节点左侧单旋转
     *           k3                         k3                        k2
     *          /  \    k1-RIGHT           /  \      k3-LEFT        /   \
     *        k1    D  ----------->       k2   D  ----------->    k1    k3
     *      /   \                       /   \                    / \    / \
     *     A     k2                    k1    C                  A   B  C  D
     *          /  \                  /  \
     *         B    C                A    B
     * @param k3 要旋转的子树
     */
    private AvlNode<K> doubleRotateWithLeftChild(AvlNode<K> k3) {
        k3.setLeft(rotateWithRightChild(k3.getLeft()));
        return rotateWithLeftChild(k3);
    }
    /**
     * 右子树双旋转: 先左子树右侧单旋转, 再本节点左侧单旋转
     *            k1                     k1                          k2
     *           /  \     k3-LEFT       /  \       k1-RIGHT        /   \
     *          A    K3  ----------->  A    K2   ----------->    k1    k3
     *              /  \                   /  \                 / \    / \
     *            k2    D                 B    K3              A   B  C  D
     *           /  \                         /  \
     *          B    C                       C    D
     * @param k1 要旋转的子树
     */
    private AvlNode<K> doubleRotateWithRightChild(AvlNode<K> k1) {
        k1.setRight(rotateWithLeftChild(k1.getRight()));
        return rotateWithRightChild(k1);
    }
    //endregion
    //endregion

    //region AVL删除元素
    public void remove(K x) {
        root = remove(x, root);
    }

    private AvlNode<K> remove(K x, AvlNode<K> t) {
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
        // 移除元素后, 也要重新平衡
        return balance(t);
    }
    // endregion

}
