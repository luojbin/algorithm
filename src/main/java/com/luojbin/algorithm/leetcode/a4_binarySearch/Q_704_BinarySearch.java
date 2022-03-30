package com.luojbin.algorithm.leetcode.a4_binarySearch;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class Q_704_BinarySearch {

    @Test
    public void test() {
        int target = 2;
        int[] nums = {-1,0,3,5,9,12};
        System.out.println(search(nums, target));
    }

    public int search(int[] nums, int target) {
        int left = 0, right = nums.length -1;
        while (left <= right) {
            int mid = (right + left) / 2;
            int num = nums[mid];
            if (num == target) {
                return mid;
            } else if (num > target) {
                // num 大于目标值, target 在左区间
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return -1;
    }
}
