package com.luojbin.algorithm.leetcode.a0_sort;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class S1_BubbleSort {

    @Test
    public void testBubbleSort() {
        int[] array = {1,3,52,-3,5,64,31,6,54,27,6,3,236,77,67,33,7};
        bubbleSort(array);
        System.out.println(Arrays.toString(array));
    }

    public void bubbleSort(int[] array) {
        int n = array.length;
        for (int i = 0; i< n; i++) {
            for(int j = i + 1; j < n; j++) {
                if (array[j] < array[i]) {
                    int t = array[j];
                    array[j] = array[i];
                    array[i] = t;
                }
            }
        }
    }
}
