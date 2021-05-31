package com.luojbin.algorithm.leetcode.Q001_100;

import java.util.*;

public class Q_049_groupAnagrams {

    public static void main(String[] args) {
        String[] strs = {"eat", "tea", "tan", "ate", "nat", "bat"};
        Collection<List<String>> result = groupAnagrams(strs);
        for (List<String> r : result) {
            System.out.println(r);
        }
    }

    public static Collection<List<String>> groupAnagrams(String[] strs) {

        Map<String, Integer> str2countMap = new HashMap<>();
        for (String s : strs) {
            int[] countArray = new int[26];
            for (int i = 0; i< s.length();i++) {
                int charIdx = s.charAt(i) - 'a';
                countArray[charIdx]++;
            }
            str2countMap.put(s, Arrays.hashCode(countArray));
        }
        Map<Integer, List<String>> result = new HashMap<>();
        for (String s : strs) {
            Integer charCount = str2countMap.get(s);
            List<String> list = result.get(charCount);
            if (list == null) {
                list = new ArrayList<>();
                list.add(s);
                result.put(charCount, list);
            } else {
                list.add(s);
            }
        }
        return new ArrayList<>(result.values());
    }
}
