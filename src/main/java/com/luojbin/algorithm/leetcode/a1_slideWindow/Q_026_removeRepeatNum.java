package com.luojbin.algorithm.leetcode.a1_slideWindow;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class Q_026_removeRepeatNum {

    @Test
    public void test() {
        int[] nums = {1,2,3,3,5,5,6,7,8,8,9,9};
        System.out.println(removeRepeat(nums));
        System.out.println(Arrays.toString(nums));
    }

    public int removeRepeat(int[] nums) {
        int length = nums.length;
        if (length == 0 || length == 1) {
            return length;
        }
        int k = 1;
        int t;
        for (int i = 1; i < length; i++) {
            if (nums[i] > nums[k-1]) {
                t = nums[i];
                nums[i] = nums[k];
                nums[k] = t;
                k++;
            }
        }
        return k;
    }
}
