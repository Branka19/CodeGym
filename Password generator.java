package com.codegym.task.task32.task3204;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Random;

/* 
Password generator

Implement the getPassword() method, which must return a ByteArrayOutputStream with the bytes comprising the password.
Password requirements:
1) 8 characters.
2) Only numbers and uppercase and lowercase Latin letters.
3) At least one numeral, one lowercase letter, and one uppercase letter.
All generated passwords must be unique.

Example of a valid password:
wMh7smNu

*/
public class Solution {
    public static void main(String[] args) throws IOException {
        ByteArrayOutputStream password = getPassword();
        System.out.println(password.toString());
    }

    public static ByteArrayOutputStream getPassword() throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        StringBuilder sb = new StringBuilder();

        Random random = new Random();
        int number = random.ints(48,58).findFirst().getAsInt();
        int lowerCase = random.ints(97,123).findFirst().getAsInt();
        int upperCase = random.ints(65,91).findFirst().getAsInt();
        sb.append((char) number).append((char)lowerCase).append((char)upperCase);

        for(int i = 0; i < 5; i++)
        {
            int x = (int)(Math.random() * 3);
            switch(x)
            {
                case 0:
                    number = random.ints(48,58).findFirst().getAsInt();
                    sb.append((char) number);
                    break;
                case 1:
                    lowerCase = random.ints(97,123).findFirst().getAsInt();
                    sb.append((char) lowerCase);
                    break;
                case 2:
                    upperCase = random.ints(65,91).findFirst().getAsInt();
                    sb.append((char) upperCase);
                    break;
            }
        }
        byte[] array = sb.toString().getBytes();
        byteArrayOutputStream.write(array);
        return byteArrayOutputStream;
    }
}
