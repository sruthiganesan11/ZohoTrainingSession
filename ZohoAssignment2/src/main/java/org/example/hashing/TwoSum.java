package org.example.hashing;


import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class TwoSum {
    public static int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> numToIndex = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];

            if (numToIndex.containsKey(complement)) {
                return new int[]{numToIndex.get(complement), i};
            }

            numToIndex.put(nums[i], i);
        }

        throw new IllegalArgumentException("No two sum solution");
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Input target value
        System.out.print("Enter the target value: ");
        int target = scanner.nextInt();

        // Input array elements
        System.out.println("Enter the elements of the array (non-integer to stop):");
        int[] nums = new int[100];
        int index = 0;

        while (scanner.hasNextInt()) {
            if (index == nums.length) {
                int[] newNums = new int[nums.length * 2];
                System.arraycopy(nums, 0, newNums, 0, nums.length);
                nums = newNums;
            }
            nums[index++] = scanner.nextInt();
        }

        int[] finalNums = new int[index];
        System.arraycopy(nums, 0, finalNums, 0, index);

        try {
            int[] result = twoSum(finalNums, target);

            System.out.println("Indices of the two numbers that add up to the target are: [" + result[0] + ", " + result[1] + "]");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

        scanner.close();
    }
}

