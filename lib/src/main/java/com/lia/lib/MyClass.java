package com.lia.lib;


import java.util.Stack;

public class MyClass {
    public static void main(String[] args) {
        String str = "abcdef";
        char[] chars = str.toCharArray();

        Stack<Character> stack = new Stack();
        for (char aChar : chars) {
            stack.push(aChar);
        }

        char[] s = new char[chars.length];
        for (int i = 0; i < chars.length; i++) {
            s[i] = stack.pop();
        }

        System.out.println(new String(s));
    }
}

