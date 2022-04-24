package com.luojbin.algorithm.structures.array;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.TreeSet;

public class Q217_ContainsDuplicate {

    @Test
    public void test() {
        // 构造参数与结果数组, 第一个参数是执行结果, 后面的是具体参数
        int[][] params = new int[][]{
                {1,2,3,4,5,6},
                {1,2,3,4,5,1},
                {Integer.MAX_VALUE, Integer.MIN_VALUE, 1,2,3,4}
        };
        // 循环测试
        for (int[] p : params) {
            System.out.println("当前参数:" + Arrays.toString(p));
            // 调用待测方法
            System.out.println(containsDuplicateBySort(p));
        }
    }

    /**
     * 标准库 TreeSet 解法
     * 执行用时：27 ms, 在所有 Java 提交中击败了5.32% 的用户
     * 内存消耗：53.7 MB, 在所有 Java 提交中击败了33.78% 的用户
     * @param nums
     * @return
     */
    public boolean containsDuplicateByTreeSet(int[] nums) {
        TreeSet<Integer> tree = new TreeSet<>();
        for (int n : nums) {
            if (tree.contains(n)) {
                return true;
            }
            tree.add(n);
        }
        return false;
    }

    /**
     * 标准库 HashSet 解法
     * 执行用时：7 ms, 在所有 Java 提交中击败了72.17% 的用户
     * 内存消耗：53.4 MB, 在所有 Java 提交中击败了42.99% 的用户
     */
    public boolean containsDuplicateByHashSet(int[] nums) {
        HashSet<Integer> set = new HashSet<>();
        for (int n : nums) {
            if (set.contains(n)) {
                return true;
            }
            set.add(n);
        }
        return false;
    }

    /**
     * 排序后比较相邻元素
     * 执行用时：18 ms, 在所有 Java 提交中击败了38.70% 的用户
     * 内存消耗：55.2 MB, 在所有 Java 提交中击败了12.63%
     * @param nums
     * @return
     */
    public boolean containsDuplicateBySort(int[] nums) {
        Arrays.sort(nums);
        int current;
        int prev = nums[0];
        for (int i = 1; i < nums.length; i++) {
            current = nums[i];
            if (current == prev) {
                return true;
            } else {
                prev = current;
            }
        }
        return false;
    }
}
