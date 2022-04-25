package com.luojbin.algorithm.structures.array;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class Q88_MergeSortedArray {
    @Test
    public void test() {
        // 构造参数与结果数组, 第一个参数是执行结果, 后面的是具体参数
        Object[][] params = new Object[][]{
                {new int[]{1,2,3,0,0,0}, 3, new int[]{2,5,6}, 3},
                {new int[]{1}, 1, new int[0], 0},
                {new int[1], 0, new int[]{1}, 1}
        };
        // 循环测试
        for (Object[] p : params) {
            System.out.println("当前参数:" + Arrays.toString(p));
            // 调用待测方法
            int[] num1 = (int[])p[0];
            int m = (int)p[1];
            int[] num2 = (int[])p[2];
            int n = (int)p[3];
            merge(num1, m, num2, n);
            System.out.println(Arrays.toString(num1));
        }
    }

    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int[] result = new int[m + n];
        int i = 0, j = 0, k = 0;
        if (m == 0) {
            System.arraycopy(nums2, 0, nums1, 0, n);
        } else if (n == 0) {
            return;
        }
        // 有值部分比较排序
        while (i < m && j < n) {
            int x = nums1[i];
            int y = nums2[j];
            if (x <= y) {
                result[k++] = x;
                i++;

            } else {
                result[k++] = y;
                j++;
            }
        }
        // 剩余部分直接添加
        for (;i < m;) {
            result[k++] = nums1[i++];
        }
        for (;j < n;) {
            result[k++] = nums2[j++];
        }
        // 将result复制到nums1
        System.arraycopy(result, 0, nums1, 0, m + n);
    }
}
