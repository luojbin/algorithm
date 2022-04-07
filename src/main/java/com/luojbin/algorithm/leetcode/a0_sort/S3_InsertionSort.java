package com.luojbin.algorithm.leetcode.a0_sort;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class S3_InsertionSort {

    @Test
    public void testInsertionSort() {
        int[] array = {1, 3, 52, -3, 5, 64, 31, 6, 54, 27, 6, 3, 236, 77, 67, 33, 7};
        insertionSort(array);
        System.out.println(Arrays.toString(array));
    }
    public void insertionSort(int[] array) {
        int n = array.length;
        // i = 有序序列的长度
        for (int i = 1; i < n; i++) {
            // 记录当前要操作的数
            int j = i;
            int tmp = array[j];
            // 当 tmp 比前一元素小时, 将该元素往后移动一格(相当于与tmp交换位置)
            while(j > 0 && tmp < array[j - 1]){
                array[j] = array[j - 1];
                // 相当于tmp的位置前移
                j--;
            }
            // 若当前元素不是最小元素, 交换
            if (j != i) {
                array[j] = tmp;
            }
        }
    }
}
