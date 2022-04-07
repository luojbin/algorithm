package com.luojbin.algorithm.leetcode.a0_sort;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class S2_BubbleSort {

    @Test
    public void testBubbleSort() {
        int[] array = {1, 3, 52, -3, 5, 64, 31, 6, 54, 27, 6, 3, 236, 77, 67, 33, 7};
        bubbleSort(array);
        System.out.println(Arrays.toString(array));
    }
    public void bubbleSort(int[] array) {
        int n = array.length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                // 遍历未排序的部分, 比较相邻两个元素的大小, 一轮遍历会将最大的元素移动到最后面
                if (array[j] > array[j + 1]) {
                    // 若当前元素大于前一元素, 交换
                    int t = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = t;
                }
            }
        }
    }
}
