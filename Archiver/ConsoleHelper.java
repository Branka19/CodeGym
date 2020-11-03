package com.codegym.task.task31.task3110;
import java.util.Scanner;
import java.io.IOException;

public class ConsoleHelper {
    

    
    public static void writeMessage(String message)
    {
        System.out.println(message);
    }
    
    public static String readString() throws IOException
    {
        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine();
        return line;
    }
    
    public static int readInt() throws IOException
    {
        Scanner scanner = new Scanner(System.in);
        int number = scanner.nextInt();
        return number;
    }
    
}
