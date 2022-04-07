package com.luojbin.algorithm.leetcode.a0_sort;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class S1_SelectionSort {

    @Test
    public void testSelectionSort() {
        int[] array = {1, 3, 52, -3, 5, 64, 31, 6, 54, 27, 6, 3, 236, 77, 67, 33, 7};
        selectionSort(array);
        System.out.println(Arrays.toString(array));
    }

    public void selectionSort(int[] array) {
        int n = array.length;
        for (int i = 0; i < n-1; i++) {
            int minIdx = i;
            for (int j = i + 1; j < n; j++) {
                if (array[j] < array[minIdx]) {
                    // 若新元素更小, 记录最小元素下标
                    minIdx = j;
                }
            }
            // 若当前元素不是最小元素, 交换
            if (minIdx != i) {
                int t = array[minIdx];
                array[minIdx] = array[i];
                array[i] = t;
            }
        }
    }
}
