package com.luojbin.algorithm.leetcode.a4_binarySearch;

import org.junit.jupiter.api.Test;

public class Q_278_FirstBadVersion {

    @Test
    public void test() {
        // 构造参数与结果数组, 第一个参数是执行结果, 后面的是具体参数
        int[][] params = new int[][]{
                {5, 4},
                {1,1},
                {10086,10010},
                {281351794, 1313268}
        };
        // 循环测试
        for (int[] p : params) {
            VersionCheck vc = new VersionCheck(p[1]);
            int result = vc.firstBadVersion(p[0]);
            System.out.printf("total %s, bad %s, result %s", p[0], p[1], result);
            System.out.println();
        }
    }


    public static class VersionCheck {
        private int bad;

        public VersionCheck(int bad) {
            this.bad = bad;
        }

        public boolean isBadVersion(int version) {
            return version >= this.bad;
        }

        public int firstBadVersion(int n) {
            int left = 1, right = n;
            while (left < right) {
                // 为了避免 left + right 造成溢出, 这里改成这种写法
                int mid = left + (right - left) / 2;
                if (isBadVersion(mid)) {
                    // 当前版本是bad, 第一个坏版本在左区间, right左移, 但要保留这个错误的位置, 可能就是要求的第一个坏版本
                    right = mid;
                } else {
                    // 当前版本是好的, 第一个坏版本在右区间, left右移, mid不可能是所求位置, 不用保留当前 mid 位置
                    left = mid + 1;
                }
            }
            // 此时 left = right
            return left;
        }
    }
}
