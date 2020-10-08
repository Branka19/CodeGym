package com.codegym.task.task22.task2211;

import java.io.IOException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.charset.Charset;

/* 
The first parameter of the main method is
the name of a file encoded in Windows-1251.
The second parameter of the main method is
the name of a file to which you need to write
the contents of the first file, but encoded in UTF-8.
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        
        //1. First parameter in Windows-1251
        String file1 = args[0];
        
        //2. Second parameter in UTF-8
        String file2 = args[1];
        
        //3. Write the file1 to file2 in UTF-8
        FileInputStream is = new FileInputStream(file1);
        FileOutputStream os = new FileOutputStream(file2);
        
        byte[] buffer = new byte[is.available()];
        Charset utf_8 = Charset.forName("UTF-8");

        while(is.available() > 0)
        {
        is.read(buffer);
        String s = new String(buffer);
        buffer = s.getBytes(utf_8);
        os.write(buffer);
        }
    }
}
