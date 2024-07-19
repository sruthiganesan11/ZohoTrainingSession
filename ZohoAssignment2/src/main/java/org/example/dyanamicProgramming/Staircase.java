package org.example.dyanamicProgramming;

import java.util.Scanner;

public class Staircase {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the staircase number: ");
        int stairs = scanner.nextInt();

        if (stairs <= 0) {
            System.out.println("The number of ways to climb the staircase is 0.");
            return;
        }
        
        int count[] = new int[stairs+1];
        count[0] = 1;
        count[1] = 1;
        for (int i = 2; i < count.length; i++)
        {
            count[i] = count[i-1] + count[i-2];
        }
        int result = count[count.length-1];
        System.out.println("There are " + result + " no of ways to climb the staircase.");
    }
}
