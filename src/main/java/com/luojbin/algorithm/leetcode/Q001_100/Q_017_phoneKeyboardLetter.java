package com.luojbin.algorithm.leetcode.Q001_100;

import java.util.ArrayList;
import java.util.List;

public class Q_017_phoneKeyboardLetter {

    public static void main(String[] args) {
        String digit = "234";
        List<String> result = letterCombinations(digit);
        System.out.println(result);
    }

    public static List<String> letterCombinations(String digits) {
        char[][] letters = {
                {'a', 'b', 'c'},
                {'d', 'e', 'f'},
                {'g', 'h', 'i'},
                {'j', 'k', 'l'},
                {'m', 'n', 'o'},
                {'p', 'q', 'r', 's'},
                {'t', 'u', 'v'},
                {'w', 'x', 'y', 'z'}
        };


        List<char[]> chars = new ArrayList<>();
        List<char[]> tempChars = new ArrayList<>();
        List<char[]> newChars = new ArrayList<>();
        char[] digitArray = digits.toCharArray();
        int length = digitArray.length;
        for (int i = 0; i < length; i++) {
            newChars.clear();
            int idx = digitArray[i] - '0' - 2;
            char[] letter = letters[idx];
            for (int j = 0; j < letter.length; j++) {
                if (i == 0) {
                    // chars 为空, 添加第一个字符
                    char[] item = new char[length];
                    item[0] = letter[j];
                    newChars.add(item);
                } else {
                    // a b c
                    // 遍历已有的, 追加一个字符 d
                    tempChars.clear();
                    for (char[] item: chars ) {
                        char[] newItem = new char[length];
                        System.arraycopy(item, 0, newItem, 0, length);
                        newItem[i] = letter[j];
                        tempChars.add(newItem);
                    }
                    newChars.addAll(tempChars);
                }
            }
            chars.clear();
            chars.addAll(newChars);
        }
        List<String> result = new ArrayList<>();
        for (char[] cs : chars) {
            result.add(new String(cs));
        }
        return result;
    }
}
