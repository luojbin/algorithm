package com.luojbin.algorithm.leetcode.Q001_100;

public class Q_005_longestPalindromicSubstring {
    public static void main(String[] args) {
        String s = "abaacdcaa";
        String result = longestPalindrome(s);
        System.out.println(result);
    }

    public static String longestPalindrome(String s) {
        // 最终结果的回文串, 缓存
        int begin = 0;
        int end = 0;

        // 长度
        int length = s.length();
        char[] chars = s.toCharArray();
        // 移动的指针
        for (int p1 = 0; p1 < length ; p1++) {
            for (int p2 = p1; p2 < length; p2++) {
                // 检查 p1 p2 之间是否回文
                boolean flag = true;
                int subLength = p2 - p1 + 1;
                for (int i = 0; i < subLength; i++) {
                    if(chars[p1 + i] != chars[p2 - i] ){
                        flag = false;
                        break;
                    }
                }
                if (flag && (p2-p1) > (end-begin)) {
                    begin = p1;
                    end = p2;
                }
            }
        }
        return s.substring(begin, end + 1);
    }
}
