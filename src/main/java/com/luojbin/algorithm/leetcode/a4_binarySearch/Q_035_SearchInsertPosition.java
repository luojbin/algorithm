package com.luojbin.algorithm.leetcode.a4_binarySearch;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class Q_035_SearchInsertPosition {

    @Test
    public void test() {
        // 构造参数与结果数组, 第一个参数是执行结果, 后面的是具体参数
        Object[][] params = new Object[][]{
                {5, new int[]{1,3,5,6}},
                {0, new int[]{1,3,5,6}},
                {2, new int[]{1,3,5,6}},
                {7, new int[]{1,3,5,6}}
        };
        // 循环测试
        for (Object[] p : params) {
            System.out.println(search((int[])p[1], (int)p[0]));
        }
    }

    public int search(int[] nums, int target) {
        // 可能要插入到数组最前面或最后面, 最前面是0 , 最后面是nums.length
        int left = 0, right = nums.length;
        while (left < right) {
            int mid = left + (right - left) / 2;
            int num = nums[mid];
            if (num == target) {
                return mid;
            } else if (num > target) {
                // num 大于目标值, target 可能在左区间, 也可能要插入到 mid 当前位置, 因此要保留 mid 为右端点
                right = mid;
            } else {
                // num 小于目标值, target 只能在右区间, 也不可能是当前mid的位置, 直接跳到下一个位置
                left = mid + 1;
            }
        }
        // 此时 left = right, 是该插入的位置
        return right;
    }
}
