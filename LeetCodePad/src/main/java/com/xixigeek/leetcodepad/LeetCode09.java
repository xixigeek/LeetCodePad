package com.xixigeek.leetcodepad;

public class LeetCode09 {
    public static boolean isPalindrome(int x) {
        if (x < 0) {
            return false;
        }
        int calc = x;
        int reverse = 0;
        while (calc > 0) {
            reverse *= 10;
            reverse += calc % 10;
            calc = calc / 10;
        }
        return x == reverse;
    }
}
