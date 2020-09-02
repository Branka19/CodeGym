package com.codegym.task.task34.task3402;

/* 
Factorials using recursion

Implement a recursive factorial method, where n is the number whose factorial needs to be calculated.
Don't create additional fields in the Solution class.

*/
public class Solution {
    public static void main(String[] args) {
        Solution solution = new Solution();

        System.out.println(solution.factorial(9));     // 362880
        System.out.println(solution.factorial(0));     // 1
        System.out.println(solution.factorial(1));     // 1
    }

    public int factorial(int n) {
        
        if(n == 0 || n == 1) return 1;
        else {
            return n * factorial(n - 1);
        }
    }
}
