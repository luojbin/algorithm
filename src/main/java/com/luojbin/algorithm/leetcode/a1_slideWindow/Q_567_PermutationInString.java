package com.luojbin.algorithm.leetcode.a1_slideWindow;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class Q_567_PermutationInString {

    @Test
    public void test() {
        // 构造参数与结果数组, 第一个参数是执行结果, 后面的是具体参数
        String[][] params = new String[][]{
                {"ab", "eidbaooo"},
                {"ab", "eidboaoo"},
                {"hello", "ooolleoooleh"}
        };
        // 循环测试
        for (String[] p : params) {
            System.out.println(checkInclusion(p[0], p[1]));
            // 调用待测方法

        }
    }

    public boolean checkInclusion(String s1, String s2) {
        int width = s1.length();
        int n = s2.length();
        if (width > n) {
            return false;
        }

        char[] c1 = s1.toCharArray();
        char[] c2 = s2.toCharArray();
        // 定义两个数组, 记录各字符在 s1 或滑动窗口中字符的出现次数
        char[] cnt1 = new char[26];
        char[] cnt2 = new char[26];
        // 统计 s1 和起始窗口的情况
        for (int i = 0; i < width; i++) {
            cnt1[c1[i] - 'a']++;
            cnt2[c2[i] - 'a']++;
        }
        // 初始化窗口后, 先判断一次
        if (Arrays.equals(cnt1, cnt2)) {
            return true;
        }
        // 设置新窗口位置, 在初始窗口基础上右移一位
        int left = 1, right = left + width - 1;
        // 逐步移动窗口
        while (right < n) {
            // 统计新窗口中的内容
            // 新增右边界的计数, 减少左边界的计数
            cnt2[c2[right] - 'a']++;
            cnt2[c2[left-1] - 'a']--;
            // 判断是否与 s1 的一样
            if (Arrays.equals(cnt1, cnt2)) {
                return true;
            }
            // 继续移动窗口
            left++;
            right++;
        }
        return false;
    }
}
