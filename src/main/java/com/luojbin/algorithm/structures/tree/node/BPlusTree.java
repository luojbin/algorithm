package com.luojbin.algorithm.structures.tree.node;

import java.util.*;

public class BPlusTree<K extends Comparable<K>, E> {

    // 关键字上限
    private final int KEY_UPPER_BOUND;

    // 关键字下限, 比这个少就要借儿子
    private final int UNDERFLOW_BOUND;

    // 根节点, 可能是叶子节点, 也可能是索引节点
    private BPlusTreeNode root;

    /** 构造器, 指定分叉数 */
    public BPlusTree(int order) {
        // 关键字是分叉数-1
        this.KEY_UPPER_BOUND = order - 1;
        this.UNDERFLOW_BOUND = KEY_UPPER_BOUND / 2;
    }

    /** 构造器, 默认8关键字(9分叉) */
    public BPlusTree() {
        this.KEY_UPPER_BOUND = 8;
        this.UNDERFLOW_BOUND = KEY_UPPER_BOUND / 2;
    }

    /** 插入方法 */
    public void insert(K key, E value) {
        // 如果根不存在, 空树, 创建一个叶子节点
        if (root == null) {
            root = new BPlusTreeLeafNode(asList(key), asList(asSet(value)));
            return;
        }

        // 如果根存在, 插入到根所代表的树中
        BPlusTreeNode newChildNode = root.insert(key, value);
        // 若返回新节点, 则树需要加深一层
        if (newChildNode != null) {
            // 看新节点是什么类型, 获取首个元素作为新根的key
            K newRootKey = newChildNode.getClass().equals(BPlusTreeLeafNode.class)
                    // 如果是叶子节点, 获取首个key
                    ? newChildNode.keys.get(0)
                    // 如果是索引节点, 搜索首个key
                    : ((BPlusTreeNonLeafNode) newChildNode).findLeafKey(newChildNode);
            // 创建新根, 包含一个key, 两个子节点
            root = new BPlusTreeNonLeafNode(asList(newRootKey), asList(root, newChildNode));
        }

    }

    public List<E> query(K key) {
        if (root == null) {
            return Collections.emptyList();
        }
        return root.query(key);
    }

    public List<E> rangeQuery(K startInclude, K endExclude) {
        if (root == null) {
            return Collections.emptyList();
        }
        return root.rangeQuery(startInclude, endExclude);
    }

    public boolean update(K key, E oldValue, E newValue) {
        if (root == null) {
            return false;
        }

        return root.update(key, oldValue, newValue);
    }

    public boolean remove(K key, E value) {
        if (root == null) {
            return false;
        }

        RemoveResult removeResult = root.remove(key, value);
        if (!removeResult.isRemoved) {
            return false;
        }

        if (root.keys.isEmpty()) {
            this.handleRootUnderflow();
        }

        return true;
    }

    public boolean remove(K key) {
        if (root == null) {
            return false;
        }

        RemoveResult removeResult = root.remove(key);
        if (!removeResult.isRemoved) {
            return false;
        }

        if (root.keys.isEmpty()) {
            this.handleRootUnderflow();
        }

        return true;
    }

    private void handleRootUnderflow() {
        root = root.getClass().equals(BPlusTreeLeafNode.class) ? null : ((BPlusTreeNonLeafNode) root).children.get(0);
    }

    @SafeVarargs
    private final <T> List<T> asList(T... e) {
        List<T> res = new ArrayList<>();
        Collections.addAll(res, e);
        return res;
    }

    @SafeVarargs
    private final <T> Set<T> asSet(T... e) {
        Set<T> res = new HashSet<>();
        Collections.addAll(res, e);
        return res;
    }

    @Override
    public String toString() {
        return root.toString();
    }

    private abstract class BPlusTreeNode {
        // 关键字列表
        protected List<K> keys;

        // 是否已满(达到关键字上限)
        protected boolean isFull() {
            return keys.size() == KEY_UPPER_BOUND;
        }

        // 是否需要从邻居借数
        protected boolean isUnderflow() {
            return keys.size() < UNDERFLOW_BOUND;
        }

        // 中间索引
        protected int getMedianIndex() {
            return KEY_UPPER_BOUND / 2;
        }

        /**
         * 二分法, 查找key
         */
        protected int findKeyIndex(K key) {
            int l = 0;
            int r = keys.size() - 1;
            // 开始是keys的长度, 相当于最后一个子节点的下标
            int index = keys.size();
            // 如果左右端点不同, 表示没定位到最精确的位置, 继续二分
            while (l <= r) {
                // 当前区间的中点, 是左端点加上区间长度的一半
                int mid = l + ((r - l) >> 1);
                // 如果中点的key大于等于要找的key, 即表示答案在左半区间(含中点)
                if (keys.get(mid).compareTo(key) >= 0) {
                    // 调整查找区间, 将右端点改成中点左侧(不含中点)
                    r = mid - 1;
                    // 将 index 修改为中点位置, 即查找区间后一个位置
                    index = mid;
                } else {
                    l = mid + 1;
                }
            }
            return index;
        }

        /**
         * 范围查找, 含头不含尾
         */
        public abstract List<E> rangeQuery(K startInclude, K endExclude);

        /**
         * 等值查询
         */
        public abstract List<E> query(K key);

        public abstract BPlusTreeNode insert(K key, E value);

        public abstract boolean update(K key, E oldValue, E newValue);

        public abstract RemoveResult remove(K key);

        public abstract RemoveResult remove(K key, E value);

        public abstract void combine(BPlusTreeNode neighbor, K parentKey);

        public abstract void borrow(BPlusTreeNode neighbor, K parentKey, boolean isLeft);
    }

    /**
     * 索引节点
     */
    private class BPlusTreeNonLeafNode extends BPlusTreeNode {
        /**
         * 子节点列表, 子节点可能是叶子节点, 也可能是索引节点, 这里用抽象节点
         */
        public List<BPlusTreeNode> children;

        /**
         * 以给定的key 和子节点列表, 构造一个索引节点
         * 子节点数 = key数 + 1
         */
        public BPlusTreeNonLeafNode(List<K> entries, List<BPlusTreeNode> children) {
            this.keys = entries;
            this.children = children;
        }


        @Override
        public List<E> rangeQuery(K startInclude, K endExclude) {
            return children.get(findChildIndex(findKeyIndex(startInclude), startInclude)).rangeQuery(startInclude, endExclude);
        }

        @Override
        public List<E> query(K key) {
            // 1. findKeyIndex, 根据key, 找到对应的子节点下标
            // 2. findChildIndex, 根据key, 找到对应的子节点下标
            // 3. 在子节点上调query方法, 若是索引节点, 递归查找, 若是叶子节点就能找到具体结果
            return children.get(findChildIndex(findKeyIndex(key), key))
                    .query(key);
        }

        @Override
        public boolean update(K key, E oldValue, E newValue) {
            return children.get(findChildIndex(findKeyIndex(key), key)).update(key, oldValue, newValue);
        }

        @Override
        public BPlusTreeNode insert(K key, E value) {
            BPlusTreeNode newChildNode = children.get(findChildIndex(findKeyIndex(key), key)).insert(key, value);

            if (newChildNode != null) {
                K newKey = findLeafKey(newChildNode);
                int newKeyIndex = findKeyIndex(newKey);
                if (isFull()) {
                    BPlusTreeNonLeafNode newNonLeafNode = split();
                    int medianIndex = getMedianIndex();
                    if (newKeyIndex < medianIndex) {
                        keys.add(newKeyIndex, newKey);
                        children.add(newKeyIndex + 1, newChildNode);
                    } else {
                        int rightIndex = newNonLeafNode.findKeyIndex(newKey);
                        newNonLeafNode.keys.add(rightIndex, newKey);
                        newNonLeafNode.children.add(rightIndex, newChildNode);
                    }
                    newNonLeafNode.keys.remove(0);
                    return newNonLeafNode;
                }

                keys.add(newKeyIndex, newKey);
                children.add(newKeyIndex + 1, newChildNode);
            }

            return null;
        }

        @Override
        public RemoveResult remove(K key) {
            int keyIndex = findKeyIndex(key);
            int childIndex = findChildIndex(keyIndex, key);
            BPlusTreeNode childNode = children.get(childIndex);
            RemoveResult removeResult = childNode.remove(key);
            if (!removeResult.isRemoved) {
                return removeResult;
            }

            if (removeResult.isUnderflow) {
                this.handleUnderflow(childNode, childIndex, keyIndex);
            }

            return new RemoveResult(true, isUnderflow());
        }

        @Override
        public RemoveResult remove(K key, E value) {
            int keyIndex = findKeyIndex(key);
            int childIndex = findChildIndex(keyIndex, key);
            BPlusTreeNode childNode = children.get(childIndex);
            RemoveResult removeResult = childNode.remove(key, value);
            if (!removeResult.isRemoved) {
                return removeResult;
            }

            if (removeResult.isUnderflow) {
                this.handleUnderflow(childNode, childIndex, keyIndex);
            }

            return new RemoveResult(true, isUnderflow());
        }

        @Override
        public void combine(BPlusTreeNode neighbor, K parentKey) {
            BPlusTreeNonLeafNode nonLeafNode = (BPlusTreeNonLeafNode) neighbor;
            this.keys.add(parentKey);
            this.keys.addAll(nonLeafNode.keys);
            this.children.addAll(nonLeafNode.children);
        }

        @Override
        public void borrow(BPlusTreeNode neighbor, K parentKey, boolean isLeft) {
            BPlusTreeNonLeafNode nonLeafNode = (BPlusTreeNonLeafNode) neighbor;
            if (isLeft) {
                this.keys.add(0, parentKey);
                this.children.add(0, nonLeafNode.children.get(nonLeafNode.children.size() - 1));
                nonLeafNode.children.remove(nonLeafNode.children.size() - 1);
                nonLeafNode.keys.remove(nonLeafNode.keys.size() - 1);
            } else {
                this.keys.add(parentKey);
                this.children.add(nonLeafNode.children.get(0));
                nonLeafNode.keys.remove(0);
                nonLeafNode.children.remove(0);
            }
        }

        public K findLeafKey(BPlusTreeNode cur) {
            if (cur.getClass().equals(BPlusTreeLeafNode.class)) {
                return cur.keys.get(0);
            }
            return findLeafKey(((BPlusTreeNonLeafNode) cur).children.get(0));
        }

        private void handleUnderflow(BPlusTreeNode childNode, int childIndex, int keyIndex) {
            BPlusTreeNode neighbor;
            if (childIndex > 0 && (neighbor = this.children.get(childIndex - 1)).keys.size() > UNDERFLOW_BOUND) {

                childNode.borrow(neighbor, this.keys.get(reviseKeyIndex(keyIndex)), true);
                this.keys.set(reviseKeyIndex(keyIndex), childNode.getClass().equals(BPlusTreeNonLeafNode.class) ? findLeafKey(childNode) : childNode.keys.get(0));

            } else if (childIndex < this.children.size() - 1 && (neighbor = this.children.get(childIndex + 1)).keys.size() > UNDERFLOW_BOUND) {

                childNode.borrow(neighbor, this.keys.get(keyIndex), false);
                this.keys.set(keyIndex, childNode.getClass().equals(BPlusTreeNonLeafNode.class) ? findLeafKey(neighbor) : neighbor.keys.get(0));

            } else {

                if (childIndex > 0) {
                    // combine current child to left child
                    neighbor = this.children.get(childIndex - 1);
                    neighbor.combine(childNode, this.keys.get(reviseKeyIndex(keyIndex)));
                    this.children.remove(childIndex);

                } else {
                    // combine right child to current child
                    neighbor = this.children.get(childIndex + 1);
                    childNode.combine(neighbor, this.keys.get(keyIndex));
                    this.children.remove(childIndex + 1);
                }

                this.keys.remove(reviseKeyIndex(keyIndex));

            }
        }

        private int reviseKeyIndex(int keyIndex) {
            return Math.min(this.keys.size() - 1, keyIndex);
        }

        private int findChildIndex(int keyIndex, K key) {
            // 如果keyIndex 是key的长度, 即要找的child是最后一个child, 下标等于keyIndex
            // 如果keyIndex 不是key的长度, 且目标key比该位置的key小, 子节点下标等于keyIndex
            // 如果keyIndex 不是key的长度, 且目标key比该位置的key大, 子节点下标等于后一个子节点
            return (keyIndex == keys.size() || key.compareTo(keys.get(keyIndex)) < 0) ? keyIndex : keyIndex + 1;
        }

        /**
         * 分裂节点
         */
        private BPlusTreeNonLeafNode split() {
            int medianIndex = getMedianIndex();
            List<K> allEntries = keys;
            List<BPlusTreeNode> allChildren = children;

            // 一半原key和子节点保留在当前节点中
            this.keys = new ArrayList<>(allEntries.subList(0, medianIndex));
            this.children = new ArrayList<>(allChildren.subList(0, medianIndex + 1));

            // 另外一半原key和子节点放到新节点中
            List<K> rightEntries = new ArrayList<>(allEntries.subList(medianIndex, allEntries.size()));
            List<BPlusTreeNode> rightChildren = new ArrayList<>(allChildren.subList(medianIndex + 1, allChildren.size()));
            return new BPlusTreeNonLeafNode(rightEntries, rightChildren);
        }

        @Override
        public String toString() {
            StringBuilder res = new StringBuilder();
            Queue<BPlusTreeNode> queue = new LinkedList<>();
            queue.add(this);
            while (!queue.isEmpty()) {
                int size = queue.size();
                for (int i = 0; i < size; ++i) {
                    BPlusTreeNode cur = queue.poll();
                    assert cur != null;
                    res.append(cur.keys).append("  ");
                    if (cur.getClass().equals(BPlusTreeNonLeafNode.class)) {
                        queue.addAll(((BPlusTreeNonLeafNode) cur).children);
                    }
                }
                res.append('\n');
            }
            return res.toString();
        }
    }

    /**
     * 叶子节点
     */
    private class BPlusTreeLeafNode extends BPlusTreeNode {

        // 叶子节点的的数据列表
        public List<Set<E>> data;

        // 记录下一个叶子节点的指针, 范围查询时可以避免重复寻址
        public BPlusTreeLeafNode next;

        /**
         * 以给定的key 和子节点列表, 构造一个索引节点
         * 子节点数 = key数 + 1
         */
        public BPlusTreeLeafNode(List<K> entries, List<Set<E>> data) {
            this.keys = entries;
            this.data = data;
        }

        @Override
        public List<E> rangeQuery(K startInclude, K endExclude) {
            List<E> res = new ArrayList<>();
            int start = findKeyIndex(startInclude);
            int end = findKeyIndex(endExclude);
            for (int i = start; i < end; ++i) {
                res.addAll(data.get(i));
            }
            BPlusTreeLeafNode nextLeafNode = next;
            while (nextLeafNode != null) {
                for (int i = 0; i < nextLeafNode.keys.size(); ++i) {
                    if (nextLeafNode.keys.get(i).compareTo(endExclude) < 0) {
                        res.addAll(nextLeafNode.data.get(i));
                    } else {
                        return res;
                    }
                }
                nextLeafNode = nextLeafNode.next;
            }
            return res;
        }

        @Override
        public List<E> query(K key) {
            int keyIndex = getEqualKeyIndex(key);
            // 如果找到了key, 返回具体结果, 否则返回空集合
            return keyIndex == -1 ? Collections.emptyList() : new ArrayList<>(data.get(keyIndex));
        }

        @Override
        public boolean update(K key, E oldValue, E newValue) {
            int keyIndex = getEqualKeyIndex(key);
            if (keyIndex == -1 || !data.get(keyIndex).contains(oldValue)) {
                return false;
            }

            data.get(keyIndex).remove(oldValue);
            data.get(keyIndex).add(newValue);
            return true;
        }

        @Override
        public RemoveResult remove(K key) {
            int keyIndex = getEqualKeyIndex(key);
            if (keyIndex == -1) {
                return new RemoveResult(false, false);
            }

            this.keys.remove(keyIndex);
            this.data.remove(keyIndex);

            return new RemoveResult(true, isUnderflow());
        }

        @Override
        public RemoveResult remove(K key, E value) {
            int keyIndex = getEqualKeyIndex(key);
            if (keyIndex == -1 || !data.get(keyIndex).contains(value)) {
                return new RemoveResult(false, false);
            }

            data.get(keyIndex).remove(value);
            if (data.get(keyIndex).isEmpty()) {
                this.keys.remove(keyIndex);
                this.data.remove(keyIndex);
            }

            return new RemoveResult(true, isUnderflow());
        }

        @Override
        public void combine(BPlusTreeNode neighbor, K parentKey) {
            BPlusTreeLeafNode leafNode = (BPlusTreeLeafNode) neighbor;
            this.keys.addAll(leafNode.keys);
            this.data.addAll(leafNode.data);
            this.next = leafNode.next;
        }

        @Override
        public void borrow(BPlusTreeNode neighbor, K parentKey, boolean isLeft) {
            BPlusTreeLeafNode leafNode = (BPlusTreeLeafNode) neighbor;
            int borrowIndex;

            if (isLeft) {
                borrowIndex = leafNode.keys.size() - 1;
                this.keys.add(0, leafNode.keys.get(borrowIndex));
                this.data.add(0, leafNode.data.get(borrowIndex));
            } else {
                borrowIndex = 0;
                this.keys.add(leafNode.keys.get(borrowIndex));
                this.data.add(leafNode.data.get(borrowIndex));
            }

            leafNode.keys.remove(borrowIndex);
            leafNode.data.remove(borrowIndex);
        }

        @Override
        public BPlusTreeNode insert(K key, E value) {
            // key是否存在
            int equalKeyIndex = getEqualKeyIndex(key);
            // 若key已存在, 将value添加到对应的 set
            if (equalKeyIndex != -1) {
                data.get(equalKeyIndex).add(value);
                return null;
            }

            // 如果key不存在, 获取该key应插入的位置
            int index = findKeyIndex(key);

            if (isFull()) {
                // 如果当前叶子节点已经满了, 需要分裂出一个新节点
                BPlusTreeLeafNode newLeafNode = split();
                int medianIndex = getMedianIndex();
                if (index < medianIndex) {
                    // 如果待插入位置在左边, 插入到原叶子节点的指定位置
                    // add(idx, x), 可以指定插入位置, 会自动调整列表
                    keys.add(index, key);
                    data.add(index, asSet(value));
                } else {
                    // 如果待插入位置在右边, 插入到新节点
                    // 计算在新节点中的位置
                    int rightIndex = index - medianIndex;
                    newLeafNode.keys.add(rightIndex, key);
                    newLeafNode.data.add(rightIndex, asSet(value));
                }
                return newLeafNode;
            }

            // 如果叶子节点未满, 将数据添加到叶子中
            // 添加key
            keys.add(index, key);
            // 添加数据
            data.add(index, asSet(value));
            return null;
        }

        private BPlusTreeLeafNode split() {
            int medianIndex = getMedianIndex();
            List<K> allKeys = keys;
            List<Set<E>> allData = data;

            this.keys = new ArrayList<>(allKeys.subList(0, medianIndex));
            this.data = new ArrayList<>(allData.subList(0, medianIndex));

            List<K> rightEntries = new ArrayList<>(allKeys.subList(medianIndex, allKeys.size()));
            List<Set<E>> rightData = new ArrayList<>(allData.subList(medianIndex, allData.size()));
            BPlusTreeLeafNode newLeafNode = new BPlusTreeLeafNode(rightEntries, rightData);

            newLeafNode.next = this.next;
            this.next = newLeafNode;
            return newLeafNode;
        }

        /**
         * 叶子节点中, 用二分法查找key, 找到了就返回, 找不到就返回-1
         */
        private int getEqualKeyIndex(K key) {
            int l = 0;
            int r = keys.size() - 1;
            while (l <= r) {
                int mid = l + ((r - l) >> 1);
                int compare = keys.get(mid).compareTo(key);
                if (compare == 0) {
                    return mid;
                } else if (compare > 0) {
                    r = mid - 1;
                } else {
                    l = mid + 1;
                }
            }
            return -1;
        }

        @Override
        public String toString() {
            return keys.toString();
        }
    }

    private static class RemoveResult {

        public boolean isRemoved;

        public boolean isUnderflow;

        public RemoveResult(boolean isRemoved, boolean isUnderflow) {
            this.isRemoved = isRemoved;
            this.isUnderflow = isUnderflow;
        }
    }
}