package com.luojbin.algorithm.leetcode.Q001_100;

public class Q_011_container {

    public static void main(String[] args) {
        int[] height = {1, 8, 6, 2, 5, 4, 8, 3, 7};
        int result = maxArea(height);
        System.out.println(result);
    }

    public static int maxArea(int[] height) {
        int tempArea = 0;
        // 长度
        int length = height.length;
        // 移动的指针
        int p1 = 0;
        int p2 = length - 1;
        while(p2>p1) {
            int h1 = height[p1];
            int h2 = height[p2];
            int h = Math.min(h1, h2);
            if (h * (p2 - p1) > tempArea) {
                tempArea = h * (p2 - p1);
            }
            // 向内移动指针
            if (h2 > h1) {
                p1++;
            } else {
                p2--;
            }
        }
        return tempArea;
    }
}
