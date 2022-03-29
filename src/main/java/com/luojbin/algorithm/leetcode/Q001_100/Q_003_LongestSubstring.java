package com.luojbin.algorithm.leetcode.Q001_100;

import org.junit.jupiter.api.Test;

import java.util.*;

public class Q_003_LongestSubstring {
    public static void main(String[] args) {
        String s = "xyzabcdaefghijk";
        int result = lengthOfLongestSubstring(s);
        System.out.println(result);
    }

    public static int lengthOfLongestSubstring(String s) {
        int maxcount = 0;
        List<Character> tempList = new ArrayList<>();
        Map<Character, Integer> countMap = new HashMap<>();

        for (int i = 0; i < s.length() ; i++) {
            char c = s.charAt(i);
            if (countMap.get(c) == null) {
                countMap.put(c, i);
                tempList.add(c);
            } else {
                // 令 i = 重复元素的下标, 继续向后遍历
                if (maxcount < tempList.size()) {
                    maxcount = tempList.size();
                }
                i = countMap.get(c);
                tempList.clear();
                countMap.clear();
            }
        }
        if (maxcount < tempList.size()) {
            maxcount = tempList.size();
        }
        return maxcount;
    }

    @Test
    public void testSlideWindow() {
        // 原始字符串
        String s = "hijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789hijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789hijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789hijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789hijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789hijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        System.out.println(getLengthBySlideWindow(s));
    }

    private int getLengthBySlideWindow(String s) {
        if (s.length() == 0) {
            return 0;
        }
        // ascii码, 256个可能字符
        int[] flags= new int[256];
        Arrays.fill(flags, -1);
        // 转换成数组
        char[] chars = s.toCharArray();
        // 滑动窗口的两个指针
        int start = 0, end=0;
        // 总长度
        int length = s.length();
        // 目标结果, 子串长度, 至少是1;
        int result = 1;
        while(end < length) {
            int letter = chars[end];
            int beforeIdx = flags[letter];
            if (beforeIdx != -1 && beforeIdx >= start) {
                // 重复字符, 且在有效子串内
                // 将 start 移动到重复字符的后一位
                start = beforeIdx + 1;
            }
            // 更新当前字符的坐标
            flags[letter] = end;
            int currentLength = end - start + 1;
            if (currentLength > result) {
                result = currentLength;
            }
            end++;
        }
        return result;
    }
}
