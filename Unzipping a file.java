package com.codegym.task.task31.task3106;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.io.IOException;

/*
Unzipping a file

The main method accepts a list of arguments.
The first argument, resultFileName, is the name of the resulting file. The remaining arguments are the names of files for fileNamePart.
Each fileNamePart file is a part of a zip file. You need to unzip the entire file by assembling it from its parts.
Write the unzipped file to resultFileName.
Files inside the archive can be large, e.g. 50MB.
Files inside the archive can have any name.

Example input. The archive has one file named abc.mp3:
C:/result.mp3
C:/pathToTest/test.zip.003
C:/pathToTest/test.zip.001
C:/pathToTest/test.zip.004
C:/pathToTest/test.zip.002

*/
public class Solution {
    public static void main(String[] args) throws IOException {
        String resultFile = args[0];
        List<String> inFiles = new ArrayList<>();
        for (int i = 1; i < args.length; i++) {
            inFiles.add(args[i]);
        }
        Collections.sort(inFiles);

        List<FileInputStream> files = new ArrayList<>();
        for (String s: inFiles) {
            files.add(new FileInputStream(s));
        }

        OutputStream outputStream = new BufferedOutputStream(new FileOutputStream(resultFile));
        ZipInputStream zipInputStream = new ZipInputStream(new SequenceInputStream(Collections.enumeration(files)));
        ZipEntry zipEntry = zipInputStream.getNextEntry();
        byte[] buffer = new byte[1024];
        while (zipEntry != null) {
            File newFile = new File(resultFile);
            FileOutputStream fos = new FileOutputStream(newFile);
            int len;
            while ((len = zipInputStream.read(buffer)) > 0) {
                fos.write(buffer, 0, len);
            }
            fos.close();
            zipEntry = zipInputStream.getNextEntry();
        }
        zipInputStream.closeEntry();
        zipInputStream.close();
    }

}
