package com.luojbin.algorithm.leetcode.a5_twoPointer;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class Q_189_RotateArray {

    @Test
    public void test() {
        // 构造参数与结果数组, 第一个参数是执行结果, 后面的是具体参数
        Object[][] params = new Object[][]{
                {3, new int[]{1,2,3,4,5,6,7}},
        };
        // 循环测试
        for (Object[] p : params) {
            // 调用待测方法
            int[] nums = (int[])p[1];
            rotate(nums, (int)p[0]);
            System.out.println(Arrays.toString(nums));

        }
    }

    public void rotate(int[] nums, int k) {
        // 计算有效位移数
        int n = nums.length;
        int validK = k % n;
        int right = n-1 , left = n-1-validK;
        // 临时数组
        int[] temp = new int[n];
        // 不越界的右移 这个i是计数的, 不是下标
        for (int i = 0; i < n ; i++ ) {
            temp[right] = nums[left];
            left--;
            right--;
            if (left < 0) {
                left += n;
            }
        }
        // 将移动后的数组复制到原始数组
        System.arraycopy(temp, 0, nums, 0, n);
    }
}
