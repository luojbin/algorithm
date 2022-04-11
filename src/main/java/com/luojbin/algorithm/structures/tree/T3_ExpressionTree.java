package com.luojbin.algorithm.structures.tree;

import com.luojbin.algorithm.structures.tree.node.BinaryTreeNode;
import org.junit.jupiter.api.Test;

import java.util.Deque;
import java.util.LinkedList;

public class T3_ExpressionTree {
    @Test
    public void test() {
        // 以逆波兰式记录输入内容
        String exp = "a b + c d e + * *";
        // 转换成数组方便处理
        String[] expArray = exp.split(" ");
        BinaryTreeNode<String> tree = buildBinaryTreeByExp(expArray);
        System.out.println(tree);
    }

    private BinaryTreeNode<String> buildBinaryTreeByExp(String[] expArray) {
        // 栈, 记录中间结果
        Deque<BinaryTreeNode<String>> stack = new LinkedList<>();
        for (String item : expArray) {
            if ("+".equals(item) || "-".equals(item) || "*".equals(item) || "/".equals(item)) {
                // 如果是运算符, 取出两个元素, 计算结果再重新压栈
                BinaryTreeNode<String> rightTree = stack.pop();
                BinaryTreeNode<String> leftTree = stack.pop();
                stack.push(new BinaryTreeNode<>(item, leftTree, rightTree));
            } else {
                // 如果是数字, 压栈
                stack.push(new BinaryTreeNode<>(item, null, null));
            }
        }
        return stack.pop();
    }
}
