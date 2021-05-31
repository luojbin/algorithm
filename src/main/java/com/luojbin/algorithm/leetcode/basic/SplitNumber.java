package com.luojbin.algorithm.leetcode.basic;

public class SplitNumber {

    public static void main(String[] args) {
        int input = 123456;

        // 按位分割一个整数
        while (input != 0) {
            System.out.println(input % 10);
            input = input / 10;
        }

    }
}
