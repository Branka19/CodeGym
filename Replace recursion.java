package com.codegym.task.task29.task2913;

import java.util.Random;

/* 
Two integers, A and B, are randomly generated by the program. 
You need to display all integers from A to B inclusive, in ascending order, if A is less than B, or in descending order otherwise.

The task is implemented using recursion.
Sometimes the program throws an Exception (java.lang.StackOverflowError) on "main" thread.

Here's your task: rewrite the code so that it does not use recursion.
Rename the recursion() method to getAllNumbersBetween().

*/

public class Solution {
    private static int numberA;
    private static int numberB;

        public static String getAllNumbersBetween(int a, int b) {
        StringBuilder sb = new StringBuilder();
        if (a == b){
            return Integer.toString(a);
        }
        else if (a > b){
            for (int i = a; i >= b; i--){
                sb.append((i)).append(" ");
            }
        }
        else {
            for (int i = a; i <= b ; i++){
                sb.append(i).append(" ");
            }
        }
        return sb.toString().trim();
    }

        public static void main(String[] args) {
        Random random = new Random();
        numberA = random.nextInt(1000);
        numberB = random.nextInt(1000);
        System.out.println(getAllNumbersBetween(numberA, numberB));
        System.out.println(getAllNumbersBetween(numberB, numberA));
    }
}
