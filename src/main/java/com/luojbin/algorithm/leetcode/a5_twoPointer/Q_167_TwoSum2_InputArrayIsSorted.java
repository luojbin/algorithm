package com.luojbin.algorithm.leetcode.a5_twoPointer;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class Q_167_TwoSum2_InputArrayIsSorted {
    @Test
    public void test() {
        // 构造参数与结果数组, 第一个参数是执行结果, 后面的是具体参数
        Object[][] params = new Object[][]{
                {9, new int[]{2,7,11,15}},
                {6, new int[]{2,3,4}},
                {-1, new int[]{-1,0}},
                {100, new int[]{5,25,75}},
        };
        // 循环测试
        for (Object[] p : params) {
            System.out.println("当前参数:" + Arrays.toString(p));
            // 调用待测方法
            int[] result = twoSum1((int[])p[1], (int)p[0]);
            System.out.println(Arrays.toString(result));
        }
    }

    /**
     * 正确但超时
     * @param numbers
     * @param target
     * @return
     */
    public int[] twoSum(int[] numbers, int target) {
        int[] result = new int[2];
        int n = numbers.length;
        boolean flag = false;
        for(int i = 0; i<n ;i++) {
            for (int j = i+1; j < n; j++) {
                if (numbers[i] + numbers[j] == target) {
                    result[0] = i+1;
                    result[1] = j+1;
                    flag = true;
                    break;
                }
            }
            if (flag) {
                break;
            }
        }
        return result;
    }
    public int[] twoSum1(int[] numbers, int target) {
        int n = numbers.length;
        int i = 0, j= n-1;
        while (i < j) {
            int sum = numbers[i] + numbers[j];
            if (sum == target) {
                return new int[]{i+1, j+1};
            } else if (sum > target) {
                // i已取得最小值, 其他i也会大于target, 说明j过大, 当前j无效, 缩减j
                j--;
            } else {
                // sum < target, j 已取得最大值, 其他的j也会小于target, 说明i 太小了, 当前i无效, 扩大i
                i++;
            }
        }
        return new int[2];
    }
}
