package com.codegym.task.task31.task3110;
import com.codegym.task.task31.task3110.command.*;
import com.codegym.task.task31.task3110.exception.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import java.io.IOException;


public class Archiver {

    public static void main(String[] args) {
        
        Operation operation = null;
        
        while(operation != Operation.EXIT) {
            try {
        operation = askOperation();
        CommandExecutor.execute(operation);
            }
            catch(Exception e)
            {
                if (e instanceof NoSuchZipFileException)
                ConsoleHelper.writeMessage("You didn't select an archive or you selected an invalid file.");
                else
                ConsoleHelper.writeMessage("An error occurred. Please check the entered data.");
            }
        }
        
    }
        
        public static Operation askOperation() throws IOException
        {
           
            Operation op = null;
            ConsoleHelper.writeMessage("Select an operation:");
            ConsoleHelper.writeMessage(Operation.CREATE.ordinal() + " - Zip files into an archive");
            ConsoleHelper.writeMessage(Operation.ADD.ordinal() + " - Add a file to an archive");
            ConsoleHelper.writeMessage(Operation.REMOVE.ordinal() + " - Remove a file from an archive");
            ConsoleHelper.writeMessage(Operation.EXTRACT.ordinal() + " - Extract an archive");
            ConsoleHelper.writeMessage(Operation.CONTENT.ordinal() + " - View the contents of an archive");
            ConsoleHelper.writeMessage(Operation.EXIT.ordinal() + " - Exit");
            
            int operation = ConsoleHelper.readInt();
            
            for(Operation e : Operation.values())
            {
                if(e.ordinal() == operation)
                op = e;
            }
            return op;
        }
}
