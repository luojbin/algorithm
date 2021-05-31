package com.luojbin.algorithm.leetcode.Q001_100;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
}
