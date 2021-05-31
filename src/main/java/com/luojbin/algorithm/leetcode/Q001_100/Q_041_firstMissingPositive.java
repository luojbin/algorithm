package com.luojbin.algorithm.leetcode.Q001_100;

public class Q_041_firstMissingPositive {

    public static void main(String[] args) {
        int[] nums = {1,2,3,4,5,6,7,100,200,300};
        int result = firstMissingPositive(nums);
        System.out.println(result);
    }
    public static int firstMissingPositive(int[] nums) {
        boolean[] count = new boolean[300];
        for(int n : nums) {
            if (n > 0 && n <= 300) {
                count[n-1] = true;
            }
        }
        for (int i = 0; i< 300; i++) {
            if (!count[i]) {
                return i+1;
            }
        }
        return 301;
    }
}
