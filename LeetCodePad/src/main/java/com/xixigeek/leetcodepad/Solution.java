package com.xixigeek.leetcodepad;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

class Solution {
    public int reverse1(int x) {
        String a = Integer.toString(x);
        int b = 1;

        if (a.charAt(0) == '-') {
            a = a.substring(1);
            b = -1;
        }

        char[] chars = a.toCharArray();
        char[] results = new char[chars.length];

        for (int i = chars.length - 1; i >= 0; i--) {
            results[chars.length - 1 - i] = chars[i];
        }
        long longNum = Long.valueOf(new String(results));
        if (longNum > Integer.MAX_VALUE || longNum < Integer.MIN_VALUE) {
            return 0;
        }
        return (int) (b * longNum);
    }

    public int reverse2(int x) {
        long result = 0;
        while (x != 0) {
            result = result * 10 + x % 10;
            x = x / 10;
        }
        if (result > Integer.MAX_VALUE || result < Integer.MIN_VALUE) {
            return 0;
        }
        return (int) result;
    }

    public boolean isPalindrome(int x) {
        if (x < 0) {
            return false;
        }
        int origin = x;
        int result = 0;
        while (x != 0) {
            result = result * 10 + x % 10;
            x = x / 10;
        }
        return result == origin;
    }

    public int romanToInt(String s) {

        int result = 0;
        char[] romans = s.toCharArray();
        for (int i = 0; i < romans.length; i++) {

            if (romans[i] == 'M') {
                if (i > 0 && romans[i - 1] == 'C') {
                    result -= 200;
                }
                result += 1000;
            } else if (romans[i] == 'D') {
                if (i > 0 && romans[i - 1] == 'C') {
                    result -= 200;
                }
                result += 500;

            } else if (romans[i] == 'C') {
                if (i > 0 && romans[i - 1] == 'X') {
                    result -= 20;
                }
                result += 100;

            } else if (romans[i] == 'L') {
                if (i > 0 && romans[i - 1] == 'X') {
                    result -= 20;
                }
                result += 50;

            } else if (romans[i] == 'X') {
                if (i > 0 && romans[i - 1] == 'I') {
                    result -= 2;
                }
                result += 10;
            } else if (romans[i] == 'V') {
                if (i > 0 && romans[i - 1] == 'I') {
                    result -= 2;
                }
                result += 5;

            } else if (romans[i] == 'I') {
                result += 1;

            }
        }
        return result;
    }

    public int romanToIntByHash(String s) {
        Map<String, Integer> map = new HashMap<>();
        map.put("I", 1);
        map.put("IV", 4);
        map.put("V", 5);
        map.put("IX", 9);
        map.put("X", 10);
        map.put("XL", 40);
        map.put("L", 50);
        map.put("XC", 90);
        map.put("C", 100);
        map.put("CD", 400);
        map.put("D", 500);
        map.put("CM", 900);
        map.put("M", 1000);

        int result = 0;
        for (int i = 0; i < s.length(); i++) {
            if (i + 2 <= s.length() && map.containsKey(s.substring(i, i + 2))) {
                result += map.get(s.substring(i, i + 2));
                i++;
            } else {
                result += map.get(s.substring(i, i + 1));
            }
        }
        return result;
    }

    public String longestCommonString(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }
        if (strs.length == 1) {
            return strs[0];
        }
        String target = strs[0];
        String subString, ans = "";
        int autoLength;

        for (int i = 0; i < target.length(); i++) {
            autoLength = 1;
            while (i + autoLength <= target.length()) {
                subString = target.substring(i, i + autoLength);
                for (int j = 1; j < strs.length; j++) {
                    if (!strs[j].contains(subString)) {
                        break;
                    } else if (j == strs.length - 1) {
                        if (subString.length() > ans.length()) {
                            ans = subString;
                        }
                    }
                }
                if (!ans.equals(subString)) {
                    break;
                } else {
                    autoLength++;
                }
            }
        }
        return ans;
    }

    public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }
        if (strs.length == 1) {
            return strs[0];
        }
        String target = strs[0];
        String subString, ans = "";
        int autoLength;

        autoLength = 1;
        while (autoLength <= target.length()) {
            subString = target.substring(0, autoLength);
            for (int j = 1; j < strs.length; j++) {
                if (!strs[j].startsWith(subString)) {
                    break;
                } else if (j == strs.length - 1) {
                    if (subString.length() > ans.length()) {
                        ans = subString;
                    }
                }
            }
            if (!ans.equals(subString)) {
                break;
            } else {
                autoLength++;
            }
        }
        return ans;
    }

    public String longestCommonPrefix1(String[] strs) {
        if (strs.length == 0) {
            return "";
        }
        String prefix = strs[0];
        for (int i = 1; i < strs.length; i++) {
            while (strs[i].indexOf(prefix) != 0) {
                prefix = prefix.substring(0, prefix.length() - 1);
                if (prefix.isEmpty()) return "";
            }
        }
        return prefix;
    }

    public String longestCommonPrefix2(String[] strs) {
        if (strs.length == 0) {
            return "";
        }
        for (int i = 0; i < strs[0].length(); i++) {
            char t = strs[0].charAt(i);
            for (int j = 0; j < strs.length; j++) {
                if (i == strs[j].length() || strs[j].charAt(i) != t) {
                    return strs[0].substring(0, i);
                }
            }
        }

        return strs[0];
    }

    public boolean isValid(String s) {
        while (s.contains("{}") || s.contains("[]") || s.contains("()")) {
            s = s.replace("{}", "");
            s = s.replace("[]", "");
            s = s.replace("()", "");
        }
        return "".equals(s);
    }

    public boolean isValidByStack(String s) {
        Stack<Character> stack = new Stack<>();
        char[] chars = s.toCharArray();

        Map<Character, Character> map = new HashMap();
        map.put(')', '(');
        map.put('}', '{');
        map.put(']', '[');

        for (int i = 0; i < chars.length; i++) {
            if (map.containsKey(chars[i])) {
                char topElement = stack.isEmpty() ? '#' : stack.pop();
                if (topElement != map.get(chars[i])) {
                    return false;
                }
            } else {
                stack.push(chars[i]);
            }
        }

        return stack.empty();
    }


    //   Definition for singly-linked list.
    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        }
        if (l2 == null) {
            return l1;
        }

        if (l2.val > l1.val) {
            l1.next = mergeTwoLists(l1.next, l2);
            return l1;
        } else {
            l2.next = mergeTwoLists(l1, l2.next);
            return l2;
        }
    }

    public ListNode deleteDuplicates(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        if (head.next.val == head.val) {
            head.next = head.next.next;
            head = deleteDuplicates(head);
        } else {
            head.next = deleteDuplicates(head.next);
        }
        return head;
    }

    public ListNode deleteDuplicates2(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode preHead = new ListNode(-1);
        ListNode prior = preHead;
        preHead.next = head;
        ListNode current = head;

        while (current.next != null) {
            if (current.next.val != current.val) {
                prior = current;
                current = current.next;
            } else {
                while (current.next != null && current.val == current.next.val) {
                    current = current.next;
                }
                if (current.next != null) {
                    prior.next = current.next;
                    current = current.next;
                } else {
                    prior.next = null;
                }
            }
        }
        return preHead.next;
    }

    public ListNode deleteDuplicates3(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        if (head.next.val == head.val) {
            while (head.next != null && head.next.val == head.val) {
                head = head.next;
            }
            return deleteDuplicates(head.next);
        } else {
            head.next = deleteDuplicates(head.next);
        }

        return head;
    }

    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(target - nums[i])) {
                return new int[]{map.get(target - nums[i]), i};
            } else {
                map.put(nums[i], i);
            }
        }
        throw new IllegalArgumentException("Not match nums");
    }


    public int removeDuplicates(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        int targetIndex = 0;
        for (int i = 1; i < nums.length; i++) {
            if (nums[targetIndex] != nums[i]) {
                targetIndex++;
                nums[targetIndex] = nums[i];
            }
        }
        return targetIndex + 1;
    }

    public int strStr(String haystack, String needle) {
        int targetIndex = 0;
        char[] haystackChars = haystack.toCharArray();
        char[] needleChars = needle.toCharArray();
        for (int i = 0; i < haystackChars.length - needleChars.length + 1; i++) {
            int j = 0;
            for (; j < needleChars.length; j++) {
                if (haystackChars[i + j] != needleChars[j]) {
                    break;
                }
            }
            if (j == needleChars.length - 1) {
                return i;
            }
        }
        return -1;
    }

    public int hammingDistance(int x, int y) {
        if (x == y) {
            return 0;
        }
        String xByte = Integer.toBinaryString(x);
        String yByte = Integer.toBinaryString(y);

        int subLength = Math.abs(xByte.length() - yByte.length());

        if (xByte.length() > yByte.length()) {
            yByte = getString(yByte, subLength);
        } else {
            xByte = getString(xByte, subLength);
        }
        char[] xChar = xByte.toCharArray();
        char[] yChar = yByte.toCharArray();
        int result = 0;
        for (int i = 0; i < xByte.length(); i++) {

            if (xChar[i] != yChar[i]) {
                result++;
            }
        }
        return result;
    }

    private String getString(String xByte, int subLength) {
        String head = "";
        for (int i = 0; i < subLength; i++) {
            head += "0";
        }
        xByte = head + xByte;
        return xByte;
    }
}
