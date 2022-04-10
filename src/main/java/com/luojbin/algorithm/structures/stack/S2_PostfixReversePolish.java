package com.luojbin.algorithm.structures.stack;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

/**
 * 栈的应用: 后缀表达式(逆波兰算法)
 */
public class S2_PostfixReversePolish {
    @Test
    public void test() {
        // 构造参数与结果数组, 第一个参数是执行结果, 后面的是具体参数
        String[][] params = new String[][]{
                {"1", "+", "2", "+", "3", "*", "4"},    // 1+2+3*4
                {"2", "*", "(", "3", "+", "4", ")"},    // 2*(3+4)
                {"2", "+", "3", "*", "4", "+", "5"}    // 2+3*4+5
        };
        // 循环测试
        for (String[] p : params) {
            System.out.println("当前参数:" + Arrays.toString(p));
            // 中缀转为后缀表达式
            String[] postfix = convertToPostfix(p);
            // 计算后缀表达式
            System.out.println("计算结果: " + calByReversePolish(postfix));
            System.out.println("---------------------------------------");
        }
    }

    /**
     * 逐个语素处理,
     * 如果是数值, 直接输出;
     * 如果是运算符, 跟栈顶元素比较优先级,
     *    如果当前运算符比栈顶符号高, 压栈
     *    如果当前运算符比栈顶符号低, 或平级, 弹出栈顶元素, 比较下一个栈顶
     * 如果是左括号, 优先级最高, 压栈
     * 如果是右括号, 弹栈, 直至弹出对应的左括号
     * @param input
     * @return
     */
    private String[] convertToPostfix(String[] input) {
        // 栈, 记录操作符
        Deque<String> stack = new LinkedList<>();
        // 结果数组, 记录后缀表达式的元素
        String[] result = new String[input.length];
        int j = 0;
        for (int i = 0; i < input.length; i++) {
            String s = input[i];
            if ("+".equals(s) || "-".equals(s)) {
                // 如果是 + - 号,
                while (true) {
                    String peek = stack.pop();
                    if (!"(".equals(peek)) {
                        result[j++] = peek;
                    } else {
                        break;
                    }
                }
            } else if ("*".equals(s) || "/".equals(s)) {
                // 如果是 * / 号
            } else if ("(".equals(s)) {
                // 压栈
                stack.push(s);
            } else if (")".equals(s)) {
                // 如果是右括号, 弹栈直到碰见左括号
                while (true) {
                    String peek = stack.pop();
                    if (!"(".equals(peek)) {
                        result[j++] = peek;
                    } else {
                        break;
                    }
                }
            } else {
                // 数字, 添加到结果数组中
                result[j++] = s;
            }
        }

        return result;
    }

    public int calByReversePolish(String[] postfix) {
        //
        return 0;
    }
}
