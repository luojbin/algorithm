package com.luojbin.algorithm.structures.stack;

import org.junit.jupiter.api.Test;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 栈的应用: 成对符号的平衡
 */
public class S1_SymbolBalance {
    @Test
    public void test() {
        // 构造参数与结果数组, 第一个参数是执行结果, 后面的是具体参数
        String[] params = new String[]{
                "(adsa([))",
                "(adsa([])))",
                "(adsa([]))",
                "(adsa()[])[)"
        };
        // 循环测试
        for (String p : params) {
            System.out.print(p + "------");
            // 调用待测方法
            System.out.println(symbolBalance(p));

        }
    }

    public String symbolBalance(String input) {
        // 创建一个栈, 记录开放符号
        Deque<Character> stack = new LinkedList<Character>();
        char[] array = input.toCharArray();
        for (int i = 0; i < array.length; i++) {
            char c = array[i];
            if (c == '(' || c == '[' || c == '{') {
                // 开放符号, 压栈
                stack.push(c);
            } else if (c == ')' || c == ']' || c == '}') {
                // 闭合符号, 弹出前一个
                if (stack.isEmpty()) {
                    return i + "未找到前置符号";
                }
                char pre = stack.pop();
                // 检查是否成对, 抛出异常, 告知错误的符号
                if (c == ')') {
                    if (pre != '(') {return String.format("%d: %s 与 %s 不成对", i, c, pre);}
                } else if (c == ']') {
                    if (pre != '[') {return String.format("%d: %s 与 %s 不成对", i, c, pre);}
                } else {
                    if (pre != '{') {return String.format("%d: %s 与 %s 不成对", i, c, pre);}
                }
            }
        }
        // 检查栈是空的
        if (!stack.isEmpty()) {
            return "仍有未成对的符号";
        }
        return "所有括号都成对, ok";
    }
}
