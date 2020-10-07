package com.codegym.task.task22.task2202;

/* 
The getPartOfString method must return the substring that
begins with the character after the first space and ends
with the end of the word that follows the 4th space.

Example:
"CodeGym is the best place to learn Java."

Result:
"is the best place"

Example:
"Amigo and Diego are best friends!"

Result:
"and Diego are best"

If the input data is invalid, throw a StringTooShortException (create an exception).
*/

public class Solution {
    public static void main(String[] args) {
        System.out.println(getPartOfString("CodeGym is the best place to learn Java."));
    }

    public static String getPartOfString(String string) {
        try{
       String[] words = string.split(" ");
       StringBuilder sb = new StringBuilder();
        for(int i = 1; i<5; i++){
            sb.append(words[i]);
            sb.append(" ");
        }
        return sb.toString().trim();
        }
    catch(Exception e){
        throw new StringTooShortException();
    }
}
    public static class StringTooShortException extends RuntimeException {
    }
}
