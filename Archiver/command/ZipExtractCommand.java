package com.codegym.task.task31.task3110.command;
import com.codegym.task.task31.task3110.ConsoleHelper;
import com.codegym.task.task31.task3110.ZipFileManager;
import com.codegym.task.task31.task3110.exception.PathNotFoundException;

import java.nio.file.Path;
import java.nio.file.Paths;

public class ZipExtractCommand extends ZipCommand {
    
    @Override
    public void execute() throws Exception 
    {
        try {
            ConsoleHelper.writeMessage("Extracting an archive");
            ZipFileManager manager = getZipFileManager();
            ConsoleHelper.writeMessage("Enter the path to the extract directory");
            Path zipPath = Paths.get(ConsoleHelper.readString());
            manager.extractAll(zipPath);
            ConsoleHelper.writeMessage("Archive extracted.");
        } catch (PathNotFoundException e) {
            ConsoleHelper.writeMessage("You didn't correctly enter directory.");
        }
    }
}
