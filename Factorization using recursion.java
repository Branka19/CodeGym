package com.codegym.task.task34.task3403;

/* 
Separate the whole number n, where n > 1, into its prime factors.
Display all of its factors in ascending order, separated by spaces.
Write a recursive method to calculate the prime factors.
Don't create additional fields in the Solution class.
*/

public class Solution {
    
    public void recurse(int n) {
    
    if(n <= 1){
        //nothing happens
        }
        
    if(n > 1)
    {
        for(int i = 2; i <= n; i++)
            {
                if(n % i == 0){
                System.out.print(i + " ");
                recurse(n/i);
                break;
                }
            }
        }
    
    }
}
