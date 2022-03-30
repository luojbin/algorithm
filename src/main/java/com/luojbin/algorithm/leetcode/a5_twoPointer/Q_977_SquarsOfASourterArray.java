package com.luojbin.algorithm.leetcode.a5_twoPointer;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class Q_977_SquarsOfASourterArray {

    @Test
    public void test() {
        // 构造参数与结果数组, 第一个参数是执行结果, 后面的是具体参数
        int[][] params = new int[][]{
                {-4, -1, 0, 3, 10},
                {-7, -3, 2, 3, 11},
        };
        // 循环测试
        for (int[] p : params) {
            System.out.println("当前参数:" + Arrays.toString(p));
            System.out.println("结果:" + Arrays.toString(sortedSquares3(p)));
            System.out.println();
        }
    }

    /** 双指针算法 */
    public int[] sortedSquares3(int[] nums) {
        // 先找到最大的负数下标
        int negative = -1;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] < 0) {
                negative = i;
            } else {
                // 找到最小负数之后就可以打断循环了
                break;
            }
        }
        int n = nums.length;
        int left = negative, right = negative + 1;
        int leftSqu;
        int rightSqu;
        int[] result = new int[n];
        for (int i = 0; i < n; i++) {
            if (left >= 0 && right < n) {
                 leftSqu = nums[left] * nums[left];
                 rightSqu = nums[right] * nums[right];
                // 左右指针的数都有效时, 比较平方值大小, 取较小的那个
                if (leftSqu < rightSqu) {
                    result[i] = leftSqu;
                    left--;
                } else {
                    result[i] = rightSqu;
                    right++;
                }
            } else if (left < 0) {
                // 如果左指针已越界, 逐个添加右边的平方数
                result[i] = nums[right] * nums[right];
                right++;
            } else {
                // 如果右指针已越界, 逐个添加左边的平方数
                result[i] = nums[left] * nums[left];
                left--;
            }
        }
        return result;
    }

    /** 双指针算法 */
    public int[] sortedSquares(int[] nums) {
        int[] squares = new int[nums.length];
        // 先求平方, 同时找到平方值最小的下标
        int minSqur = Integer.MAX_VALUE;
        int minSqurIdx = -1;
        for (int i = 0; i < nums.length; i++) {
            squares[i] = nums[i] * nums[i];
            if (squares[i] < minSqur) {
                minSqur = squares[i];
                minSqurIdx = i;
            }
        }
        System.out.println("排序前:" + Arrays.toString(squares));

        int[] result = new int[nums.length];
        // 从最小平方的下标开始, 向两侧移动
        int left = minSqurIdx, right = minSqurIdx + 1;
        for (int j = 0; j < nums.length; j++) {
            if (left >= 0 && right < nums.length) {
                if (squares[left] < squares[right]) {
                    result[j] = squares[left--];
                } else {
                    result[j] = squares[right++];
                }
            } else if (left < 0) {
                result[j] = squares[right++];
            } else {
                result[j] = squares[left--];
            }
        }
        return result;
    }

    /** 无脑平方再排序 */
    public int[] sortedSquares2(int[] nums) {
        int[] result = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            result[i] = nums[i] * nums[i];
        }
        Arrays.sort(result);
        return result;
    }
}
