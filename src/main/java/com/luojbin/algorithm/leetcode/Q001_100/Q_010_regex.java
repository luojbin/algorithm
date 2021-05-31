package com.luojbin.algorithm.leetcode.Q001_100;

public class Q_010_regex {
    public static void main(String[] args) {
        String s = "apple";
        String reg = "ap.le";
        System.out.println(isMatch(s, reg));
    }
    public static boolean isMatch(String s, String p) {
        // 如果是 .*, 直接全匹配, 返回true
        if (".*".equals(p)) {
            return true;
        }
        // 如果不含.和*, 直接看两字符串是否相等
        if (!s.contains(".") && !s.contains("*")) {
            return s.equals(p);
        }

        char[] chars = s.toCharArray();
        // 如果只含*, 找到*的前一个字母, 确定要匹配的字符, 然后在s中找到对应的位置
        // for () {
        //
        // }

        return true;
    }
}
