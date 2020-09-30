package com.codegym.task.task39.task3904;

import java.util.Arrays;

/* 
A boy runs up a flight of stairs consisting of n steps; in 1 bound, he can move up one, two or three steps.
Implement the numberOfPossibleAscents(int n) method, which should return the number of ways the boy can ascend an entire flight of stairs consisting of n steps.
P.S. If the flight of stairs consists of 0 steps, the method should return 1. For n < 0, return 0.

*/
public class Solution {
    private static int n = 70;
    public static void main(String[] args) {
        System.out.println("The number of possible ascents for " + n + " steps is: " + numberOfPossibleAscents(n));
    }

    public static long numberOfPossibleAscents(int n) {
        
        if(n == 0)
            return 1;
        
        else if (n < 1)
            return 0;
        
        long a = 1;
        long b = 1;
        long c = 2;
        for (int i = 3; i <= n; i++)
        {
            long result = a + b + c;
            a = b;
            b = c;
            c = result;
        }

        return c;
    }
}
