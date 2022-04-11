package com.luojbin.algorithm.structures.tree.node;

import lombok.Data;

import java.util.ArrayDeque;
import java.util.Deque;

@Data
public class TreeNode {
    Object element;
    public TreeNode firstChild;
    public TreeNode nextSibling;

    public TreeNode(Object data) {
        this.element = data;
    }

    public  void levelOrderPrint() {
        // 需要两个队列, 一个是当前要打印的层级, 另一个是缓存下一层的节点
        Deque<TreeNode> currentLevel = new ArrayDeque<>();
        Deque<TreeNode> subLevel = new ArrayDeque<>();

        currentLevel.offer(this);
        levelOrderPrint(currentLevel, subLevel);
    }

    private static  void levelOrderPrint(Deque<TreeNode> currentLevel, Deque<TreeNode> subLevel) {
        TreeNode subNode;
        while (currentLevel.size() > 0) {
            while (currentLevel.peek() != null) {
                TreeNode currentNode = currentLevel.poll();
                if (currentNode.firstChild != null) {
                    subNode = currentNode.firstChild;
                    subLevel.offer(subNode);
                    while (subNode.nextSibling != null) {
                        subNode = subNode.nextSibling;
                        subLevel.offer(subNode);
                    }
                }
                System.out.println(currentNode);
            }
            currentLevel = subLevel;
            subLevel = new ArrayDeque<>();
        }
    }

    @Override
    public String toString() {
        return String.valueOf(element);
    }


}
