package org.example.dyanamicProgramming;

import java.util.*;

public class Coins {
    public static void main(String[] args) {
        int sum = 0;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter total number of coins");
        int number = scanner.nextInt();
        Integer coins[] = new Integer[number;
        int coinCounter[] = new int[coins.length];

        System.out.println("Enter the coins");
        for (int i = 0; i < number; i++) {
            coins[i] = scanner.nextInt();
        }
        System.out.println("Enter the total amount");
        int amount = scanner.nextInt();
        Arrays.sort(coins, Collections.reverseOrder());
        for (int i = 0; i < number; i++) {
            if (coins[i] <= amount) {
                coinCounter[i] = amount / coins[i];
                amount = amount % coins[i];
            }
        }
        System.out.println("Coins" + " " + "NoOfCoins ");
        for (int i = 0; i < number; i++) {
            if (coinCounter[i] > 0) {
                sum = sum + coinCounter[i];
                System.out.println(coins[i] + "    " + coinCounter[i]);
            }
        }
        System.out.println("Total coins: " + sum);
    }
}
