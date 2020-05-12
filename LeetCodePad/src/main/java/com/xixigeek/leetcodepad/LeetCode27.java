package com.xixigeek.leetcodepad;

public class LeetCode27 {
    public int removeElement(int[] nums, int val) {
        int targetIndex = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == val) {
                for (int j = i; j < nums.length - 1; j++) {
                    int temp = nums[j];
                    nums[j] = nums[j + 1];
                    nums[j + 1] = temp;
                }
                i--;
                targetIndex++;
            }
            if (i == nums.length - targetIndex - 1) {
                break;
            }
        }
        return nums.length - targetIndex;
    }

    public int removeElement2(int[] nums, int val) {
        int i = 0;
        for (int j = 0; j < nums.length; j++) {
            if (nums[j] != val) {
                nums[i] = nums[j];
                i++;
            }
        }
        return i;
    }


}
