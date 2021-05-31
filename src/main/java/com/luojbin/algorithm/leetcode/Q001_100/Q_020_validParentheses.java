package com.luojbin.algorithm.leetcode.Q001_100;

import java.util.ArrayDeque;
import java.util.Deque;

public class Q_020_validParentheses {

    public static void main(String[] args) {
        String s = "";
        boolean result = isValid(s);
        System.out.println(result);
    }

    public static boolean isValid(String s) {
        Deque<Character> stack = new ArrayDeque<>();
        char[] array = s.toCharArray();
        for (char c : array) {
            if (c == '(' || c == '{' || c == '[' ) {
                stack.push(c);
            } else {
                if (stack.isEmpty()) {
                    return false;
                }
                char peek = stack.pop();
                boolean match = (peek == '(' && c == ')' )
                        || (peek == '[' && c == ']' )
                        || (peek == '{' && c == '}' );
                if (!match) {
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }

}
