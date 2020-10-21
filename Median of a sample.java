package com.codegym.task.task26.task2601;

/* 
Implement the logic of the sort() method. It must sort the data in the array according to its distance from the median.
Return the array sorted from the least to greatest distance.
If several numbers are equally distant, then sort them in ascending order.
*/

import java.util.*;
import java.lang.*;

public class Solution {

    public static void main(String[] args) {
        
        Integer[] array = {13, 8, 15, 5, 17};
        sort(array);
    }

    public static Integer[] sort(Integer[] array) {
        Arrays.sort(array);
        int n = array.length;
        double median = findMedian(array, n);
        
        Comparator<Integer> compare = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                int result = 0;
                result = (int)(Math.abs(o1 - median) - Math.abs(o2 - median));
                if(result == 0)
                result = o1 - o2;
                return result;
            }
        };
        Arrays.sort(array, compare);
        
        return array;
    }
    
    public static double findMedian(Integer a[], int n) 
    { 

        double median = 0;
        if((n %2) != 0)
            median = a[n/2];
        else{
            median = (a[n/2]+(double)a[(n/2)-1])/2;
        }
        return median;
    }

}
