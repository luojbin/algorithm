package com.luojbin.algorithm.structures.stack;

import org.junit.jupiter.api.Test;

import java.util.*;

/**
 * 栈的应用: 后缀表达式(逆波兰算法)
 */
public class S2_PostfixReversePolish {
    @Test
    public void test() {
        // 构造参数与结果数组, 第一个参数是执行结果, 后面的是具体参数
        String[] params = new String[]{
                "1 + 2 + 3 * 4",
                "2 * ( 3 + 4 )",
                "2 + 3 * 4 + 5",
                "1 + 2 - 3 + 4 - 5",
                "( 1 + 2 ) * ( ( 2 + 3 ) * ( 3 - 1 ) )",
        };
        // 循环测试
        for (String p : params) {
            System.out.println("原始表达式:" + p);
            // 中缀转为后缀表达式
            List<String> postfix = convertToPostfix(p.split(" "));
            System.out.println("后缀表达式:" + postfix);

            // 计算后缀表达式
            System.out.println("计算结果: " + calByReversePolish(postfix));
            System.out.println("---------------------------------------");
        }
    }

    /**
     * 逐个语素处理,
     * 如果是数值, 直接输出;
     * 如果是运算符, 跟栈顶元素比较优先级,
     * 如果当前运算符比栈顶符号高, 压栈
     * 如果当前运算符比栈顶符号低, 或平级, 弹出栈顶元素, 比较下一个栈顶
     * 如果是左括号, 优先级最高, 压栈
     * 如果是右括号, 弹栈, 直至弹出对应的左括号
     *
     * @param input
     * @return
     */
    private List<String> convertToPostfix(String[] input) {
        // 栈, 记录操作符
        Deque<String> stack = new LinkedList<>();
        // 结果数组, 记录后缀表达式的元素
        List<String> result = new ArrayList<>();
        int j = 0;
        for (int i = 0; i < input.length; i++) {
            String s = input[i];
            if (")".equals(s)) {
                // 如果是右括号, 弹栈直到碰见左括号
                while (true) {
                    String peek = stack.peek();
                    if ("(".equals(peek)) {
                        stack.pop();
                        break;
                    } else {
                        peek = stack.pop();
                        result.add(peek);
                    }
                }
            } else if ("(".equals(s) || "*".equals(s) || "/".equals(s) || "+".equals(s) || "-".equals(s)) {
                // 如果是运算符, 看看栈顶有没有
                String peek = stack.peek();
                if (peek == null || getSymbolScore(s) - getSymbolScore(peek) > 0) {
                    // 如果栈顶为空, 压栈
                    // 如果当前符号的优先级更高, 压栈
                    stack.push(s);
                } else {
                    // 如果当前符号的优先级不高于栈顶元素, 一直弹栈
                    while (true) {
                        peek = stack.peek();
                        if (peek != null && !"(".equals(peek) && getSymbolScore(s) - getSymbolScore(peek) <= 0) {
                            peek = stack.pop();
                            result.add(peek);
                        } else {
                            break;
                        }
                    }
                    // 然后将当前符号压栈
                    stack.push(s);
                }
            } else {
                // 数字, 添加到结果数组中
                result.add(s);
            }
        }
        while (true) {
            String peek = stack.peek();
            if (peek == null) {
                break;
            } else {
                peek = stack.pop();
                result.add(peek);
            }
        }
        return result;
    }

    private int getSymbolScore(String symbol) {
        switch (symbol) {
            case "(":
                return 999;
            case "*":
            case "/":
                return 10;
            case "+":
            case "-":
                return 1;
        }
        return 0;
    }


    public int calByReversePolish(List<String> postfix) {
        // 栈, 记录中间结果
        Deque<Integer> stack = new LinkedList<>();
        for (String item : postfix) {
            if ("+".equals(item) || "-".equals(item) || "*".equals(item) || "/".equals(item)) {
                // 如果是运算符, 取出两个元素, 计算结果再重新压栈
                stack.push(cal(stack.pop(), stack.pop(), item));
            } else {
                // 如果是数字, 压栈
                stack.push(Integer.valueOf(item));
            }
        }
        return stack.pop();
    }

    private Integer cal(Integer a, Integer b, String item) {
        switch (item) {
            case "+":
                return b + a;
            case "-":
                return b - a;
            case "*":
                return b * a;
            case "/":
                return b / a;
            default:
                throw new RuntimeException("暂不支持的运算符");
        }
    }
}
