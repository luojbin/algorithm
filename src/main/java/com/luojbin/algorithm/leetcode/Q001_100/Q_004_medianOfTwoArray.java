package com.luojbin.algorithm.leetcode.Q001_100;

public class Q_004_medianOfTwoArray {

    public static void main(String[] args) {
        int[] nums1 = {};
        int[] nums2 = {};
        double result = findMedianSortedArrays(nums1, nums2);
    }

    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {

        // 先求两数组的总数 n
        // 若 n 为奇数, 则中位数为 a[(n+1)/2]
        // 若 n 为偶数, 则中位数为 (a[n/2] + a[n/2+1])/2

        int n = nums1.length + nums2.length;
        if (n % 2 == 0) {
            // n 为偶数, 取(a[n/2] + a[n/2+1])/2


        } else {
            // n 为奇数, 取第 a[(n+1)/2]
            int i = 0 , j = 0;
            // while () {
            //
            // }

        }


        return 0;
    }
}
