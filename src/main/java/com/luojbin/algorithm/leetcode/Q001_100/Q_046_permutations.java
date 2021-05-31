package com.luojbin.algorithm.leetcode.Q001_100;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Q_046_permutations {

    public static void main(String[] args) {
        int[] nums = {1,2,3,4,5,6};
        List<List<Integer>> result = permute(nums);
        for(List<Integer> r : result) {
            System.out.println(r);
        }
    }

    public static List<List<Integer>> permute(int[] nums) {

        if (nums.length == 1) {
           return Collections.singletonList(Collections.singletonList(nums[0]));
        }

        if (nums.length == 2) {
            List<List<Integer>> result = new ArrayList<>();
            List<Integer> item1 = new ArrayList<>();
            item1.add(nums[0]);
            item1.add(nums[1]);
            List<Integer> item2 = new ArrayList<>();
            item2.add(nums[1]);
            item2.add(nums[0]);
            result.add(item1);
            result.add(item2);
            return result;
        }

        if (nums.length > 2) {
            List<List<Integer>> result = new ArrayList<>();
            for (int i = 0; i < nums.length ; i++) {
                // 当前元素
                int item = nums[i];
                // 剩余元素组成数组
                int[] subNums = new int[nums.length-1];
                if (i != 0) {
                    System.arraycopy(nums, 0, subNums, 0, i);
                }
                if (i != nums.length-1) {
                    System.arraycopy(nums, i+1, subNums, i, nums.length-i-1);
                }

                // 以当前元素为首个元素, 剩余元素的全排列放在后面, 组成新的全排列
                // 剩余元素的全排列
                List<List<Integer>> subResult = permute(subNums);
                for (List<Integer> sr : subResult) {
                    List<Integer> sumResult = new ArrayList<>();
                    sumResult.add(item);
                    sumResult.addAll(sr);
                    result.add(sumResult);
                }
            }
            return result;
        }

        return Collections.emptyList();
    }
}
