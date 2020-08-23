package com.codegym.task.task32.task3213;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;

/* 
Caesar cipher

*/

public class Solution {
    public static void main(String[] args) throws IOException {
        StringReader reader = new StringReader("Khoor#Dpljr#&C,₷B'3");
        System.out.println(decode(reader, -3));  // Hello Amigo #@)₴?$0
    }

    public static String decode(StringReader reader, int key) throws IOException {
        StringBuilder result = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(reader)) {
            int character;
            while ((character = bufferedReader.read()) > 0) {
                result.append((char)(character + key));
            }
        }

        catch(Exception e)
        {
            return new String(" ");
        }
        return result.toString();
    }
}
