package com.luojbin.algorithm.leetcode.a5_twoPointer;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class Q_344_ReverseString {
    @Test
    public void test() {
        // 构造参数与结果数组, 第一个参数是执行结果, 后面的是具体参数
        String[] params = new String[]{
                "hello",
                "Ha12ah",
                "Ha1ah",
        };
        // 循环测试
        for (String p : params) {
            System.out.println("当前参数:" + p);
            // 调用待测方法
            char[] s = p.toCharArray();
            reverseString(s);
            System.out.println(Arrays.toString(s));
        }
    }

    public void reverseString(char[] s) {
        int n = s.length;
        char t;
        for (int i = 0, j= n-1; i < j; i++, j--) {
            t = s[j];
            s[j]= s[i];
            s[i] = t;
        }
    }
}
