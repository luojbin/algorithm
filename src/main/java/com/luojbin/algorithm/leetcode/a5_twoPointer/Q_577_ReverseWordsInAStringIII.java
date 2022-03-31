package com.luojbin.algorithm.leetcode.a5_twoPointer;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class Q_577_ReverseWordsInAStringIII {
    @Test
    public void test() {
        // 构造参数与结果数组, 第一个参数是执行结果, 后面的是具体参数
        String[] params = new String[]{
                "Let's take LeetCode contest",
                "God Ding",
                "Ha1ah",
        };
        // 循环测试
        for (String p : params) {
            System.out.println("当前参数:" + p);
            // 调用待测方法

            System.out.println(reverseWords(p));
        }
    }

    public String reverseWords(String s) {
        char[] cs = s.toCharArray();
        int n = cs.length;
        int left = 0, right = 1;
        while (right < n) {
            if (cs[right] == ' ') {
                reverseString(cs, left, right-1);
                left = right + 1;
            }
            right++;
        }
        reverseString(cs, left, right-1);
        return String.valueOf(cs);
    }

    public void reverseString(char[] s, int i, int j) {
        char t;
        while (i < j) {
            t = s[j];
            s[j]= s[i];
            s[i] = t;
            i++;
            j--;
        }
    }
}
