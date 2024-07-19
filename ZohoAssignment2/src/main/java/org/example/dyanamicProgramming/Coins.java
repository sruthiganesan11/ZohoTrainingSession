package org.example.dyanamicProgramming;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Coins {
    public static int coinChange(int[] coins, int amount) {
        // Initialize the dp array
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, amount + 1);
        dp[0] = 0;

        // Array to track the coins used
        int[] coinUsed = new int[amount + 1];

        for (int i = 1; i <= amount; i++) {
            for (int coin : coins) {
                if (coin <= i && dp[i - coin] + 1 < dp[i]) {
                    dp[i] = dp[i - coin] + 1;
                    coinUsed[i] = coin;
                }
            }
        }

        if (dp[amount] > amount) {
            return -1;
        }

        List<Integer> resultCoins = new ArrayList<>();
        for (int i = amount; i > 0; i -= coinUsed[i]) {
            resultCoins.add(coinUsed[i]);
        }
        return dp[amount];
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the coin denominations: ");
        String coinInput = scanner.nextLine();
        String[] coinStrings = coinInput.split(" ");
        int[] coins = new int[coinStrings.length];
        for (int i = 0; i < coinStrings.length; i++) {
            coins[i] = Integer.parseInt(coinStrings[i]);
        }

        System.out.print("Enter the amount: ");
        int amount = scanner.nextInt();

        // Compute and print the result
        int result = coinChange(coins, amount);
        if (result == -1) {
            System.out.println(result);
        } else {
            System.out.println(result);
        }

        scanner.close();

    }
}
