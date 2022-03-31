package com.luojbin.algorithm.leetcode.a5_twoPointer;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class Q_283_MoveZeroes {
    @Test
    public void test() {
        // 构造参数与结果数组, 第一个参数是执行结果, 后面的是具体参数
        int[][] params = new int[][]{
                {0,1,0,3,12},
                {0,1,0,0,0,3,12},
                {0},
                {2,1},
        };
        // 循环测试
        for (int[] p : params) {
            // 调用待测方法
            moveZeroes(p);
            System.out.println(Arrays.toString(p));
        }
    }

    public void moveZeroes(int[] nums) {
        int n = nums.length;
        if (n <= 1) {
            return ;
        }
        int left = 0 , right = 1;
        while (right < n) {
            if (nums[left] != 0) {
                left++;
            }
            if (nums[left] == 0 && nums[right] != 0) {
                nums[left] = nums[right];
                nums[right] = 0;
            }
            right++;
        }
    }
}
