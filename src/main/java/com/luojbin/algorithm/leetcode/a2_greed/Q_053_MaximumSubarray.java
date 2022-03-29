package com.luojbin.algorithm.leetcode.a2_greed;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Q_053_MaximumSubarray {

    @Test
    public void testMaximumSubarray() {
        int[] nums = {-2,1,-3,4,-1,2,1,-5,4};
        assertEquals(6, maxSubArray(nums));
    }

    public int maxSubArray(int[] nums) {
        int length = nums.length;
        // 特殊情况优化
        if (length == 0) {
            return 0;
        } else if (length == 1) {
            return nums[0];
        }
        // 滑动窗口求最大值
        int end = 0, sum = 0;
        int max = Integer.MIN_VALUE;
        while (end < length) {
            // end 后移一位, sum加上最新的一个字符
            sum += nums[end];
            if (sum > max) {
                max = sum;
            }
            // 一旦 sum < 0, 清零从下一位开始重新求和
            if (sum < 0) {
                sum = 0;
            }
            end++;
        }
        return max;
    }
}
