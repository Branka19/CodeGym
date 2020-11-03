package com.codegym.task.task31.task3110.command;
import com.codegym.task.task31.task3110.ConsoleHelper;
import com.codegym.task.task31.task3110.ZipFileManager;
import com.codegym.task.task31.task3110.FileProperties;

public class ZipContentCommand extends ZipCommand {
    
    @Override
    public void execute() throws Exception 
    {
        //viewing an archive's contents
        ConsoleHelper.writeMessage("Viewing contents of the archive.");
        ZipFileManager manager = getZipFileManager();
        ConsoleHelper.writeMessage("Archive contents:");
        
        for(FileProperties property : manager.getFileList())
        {
            ConsoleHelper.writeMessage(property.toString());
        }
        ConsoleHelper.writeMessage("Archive contents viewed.");
    }
}
