<strong>Archiver Part 1:</strong>

Let's write an archiver. At a minimum, an archiver should be able to zip and unzip files.

Let's start with the first one.
We need an archive manager. It will perform operations on the archive file (a file that will be stored on disk and have the zip extension). The class that will handle this will be called ZipFileManager. But the most important class will be called Archiver.
Programming and other fields have a concept called full (absolute) and relative paths. First off, let's wrap our heads around what a path is, in general. A path is a set of symbols that indicates where in the operating system a file or folder is located.
A full or absolute path is a path that starts with the root directory. In Windows, a disk is generally considered the root directory.
An example of a full path in Windows is: C:\user\zips\Test1.zip.
A relative path is a path relative to a directory. For example, zips\Test1.zip is the relative path of the file Test1.zip in relation to the C:\user directory (folder). The path relative to the C:\user\zips directory would just be Test1.zip, and would match the file name.
Note that, by default, both the full and relative path to a file include the name of the file.
1. Create a ZipFileManager class
2. Add a private Path zipFile variable to the class. We will use it to store the full path to the archive that we will be working with.
3. Add a ZipFileManager(Path zipFile) constructor. Initialize the class's zipFile field.
4. Declare a public createZip(Path source) throws Exception method with, for now, an empty implementation.
The Path source parameter is a path to something that we will zip.
5. Create an Archiver class and add a main method to it.
6. In the main method:
6.1 Ask the user to enter the full path to the archive from the keyboard. Don't forget that the file name is also part of the full path.
6.2 Create a ZipFileManager object, passing in the archive's file name. Figure out how to get a Path from a String.

Hint: look into the Paths class's get() method.

6.3 Ask the user to enter the path to the file to be zipped. Don't confuse this with the archive file, which we already entered. This time, we need the file that we'll compress, not the one we're going to store the compressed data in.
6.4 Call the ZipFileManager object's createZip method, passing the path to be zipped.

<strong>Archiver Part 2:</strong>

Now let's implement the createZip(Path source) method, which will zip the file specified by the source argument.
Java has a special ZipOutputStream class in the java.util.zip package. It compresses (zips)
the data passed to it. To keep multiple files from blending together when they are compressed into one archive, we create a special entity called a ZipEntry for each of them. In other words, we first put a ZipEntry in the ZipOutputStream, and then we write the file's contents. When the file is written, it is automatically compressed, and when it is read, it is automatically decompressed. A ZipEntry doesn't have to be a file; it can also be a folder.

To zip a file (create a new archive and add the file to it):
1. Create a new ZipOutputStream using the zipFile variable and the Files class's newOutputStream method.
2. Create a new ZipEntry. Pass a String containing the new entry's name to the ZipEntry constructor.
You need to get the name from the full path source. Get only the file name and convert it to a String. 3. Add the created zip entry to the zip stream.
4. Copy the data from the file we are zipping to the zip stream. To do this:
4.1. Create an InputStream for the source file using the Files class's newInputStream method.
4.2. Make a loop that will read data from the InputStream (created in 4.1) as long as it has data and write that data to the ZipOutputStream (created in step 1)
4.3. Close the InputStream using a try-with-resources
5. Close the zip stream's zip entry
6. Close the zip stream using a try-with-resources
7. Run the program and verify that a file can be zipped

<strong>Archiver Part 3:</strong>

As you can see, zipping isn't that bad. But our archiver is somehow
too primitive. A real archiver should be able to do much more: extract an archive,
add a new file to an existing archive, remove a file from an archive, and view the contents
of an archive. Now let's improve our archiver. To do that, we have to write a few new
classes. First, let's create an Operation enum, which will contain all the commands that
our archiver supports.
It would also be convenient to use ConsoleHelper to work with the console so that everything console-related
is gathered into the same class. Going forward, unless indicated otherwise, all input and output should happen through ConsoleHelper.

1. Declare the Operation enum, and add the following commands to it:
1.1. CREATE - Create an archive
1.2. ADD - Add a file to an archive
1.3. REMOVE - Remove a file from an archive
1.4. EXTRACT - Extract an archive's contents
1.5. CONTENT - View an archive's contents
1.6. EXIT - Exit the program
2. Create a ConsoleHelper class and implement the following static public methods in it:
2.1. void writeMessage(String message) - Display a message on the console
2.2. String readString() - Read a String from the console
2.3. int readInt() - Read a number from the console
Methods that read from the console can throw an IOException if an input error occurs. Account for this in their declarations.

<strong>Archiver Part 4:</strong>

Each command implies the execution of some action. Create a Command interface with an execute() method.
We'll create a separate class for each command. All of the command classes must implement (inherit) the Command interface.
The simplest command is EXIT, so let's start with that.

1. Create the command package
2. Declare the Command interface in it
3. Add a void execute() throws Exception method to the Command interface
4. Declare an ExitCommand class that implements the Command interface
5. Implement the execute() method in the ExitCommand class. It should use the ConsoleHelper class to display "Bye!"
6. At the very end of the Archiver class's main method, add code that creates an ExitCommand object and calls its execute() method
7. Check that it all works
Note that all the project files must be encoded in UTF-8. You can set the encoding in IntelliJ IDEA under Settings -> Editor -> File Encodings. Be sure that all three fields responsible for encoding are set to UTF-8.

<strong>Archiver Part 5:</strong>

Let's divide the commands into two types: those that work directly with the archive, and helpers
(for example, EXIT). All the commands of the first type will have shared functionality. It's convenient to pull this functionality into a common base class. Let's call this class ZipCommand. Like all command classes, it must implement the Command interface. All commands that work with an archive must inherit the ZipCommand class. We won't create any ZipCommand objects, so let's make it abstract.

1. Create an abstract ZipCommand class that implements the Command interface
2. Create a class for each command. All of the listed commands must inherit ZipCommand and have an empty execute() method. We'll write the implementation for each command separately as we implement our archiver.
2.1. ZipCreateCommand is the command for creating an archive (zipping files into an archive)
2.2. ZipContentCommand is the command for viewing an archive's contents
2.3. ZipExtractCommand is the command for extracting an archive
2.4. ZipAddCommand is the command for adding a file to an archive
2.5. ZipRemoveCommand is the command for removing a file from an archive

<strong>Archiver Part 6:</strong>

Suppose the user uses the Operation operation variable to let us know what he or she wants to do. Then we need to check the variable, create a corresponding command object, and call its execute() method. To avoid creating the required command object every time, we need to store it somewhere. Let's create a CommandExecutor class for this. We'll give it just one public static execute(Operation operation) method, which will find the required command and call its execute method.

1. Create a CommandExecutor class. It should be located at the root of the task. Don't add it to the command package.
2. Prevent direct calls to the class's constructor
3. Add a private static constant Map<Operation, Command>
allKnownCommandsMap to the class to store the commands.
4. Initialize the allKnownCommandsMap field so that each field of the Operation enum corresponds to the correct command.
5. Implement the public static execute(Operation operation) throws Exception method, which must select the required command from allKnownCommandsMap and call its execute method.
Whether or not you realized it, you just implemented the command pattern!

<strong>Archiver Part 7:</strong>

We've done a lot and can take a quick break.
Let's create an exception package and add two classes to it: PathNotFoundException and NoSuchZipFileException.
We'll throw a PathNotFoundException exception if we can't find the path needed to extract the archive, or the path to the file you want to zip, or any other path.
A NoSuchZipFileException will be thrown if there is an attempt to do something with an archive that doesn't exist.

1. Create the exception package
2. Add to it a PathNotFoundException class that inherits Exception
3. Add a NoSuchZipFileException class that also inherits Exception

<strong>Archiver Part 8:</strong>

To find out which command the user currently wants to execute, let's add an Operation
askOperation() method to the Archiver class. This method should display a list of available commands and
ask the user to choose one of them. For convenience, we will ask the user to enter the command's number, which is its
ordinal number in the Operation enum. You can get an enum field's ordinal number using the ordinal() method.
Now everything is ready to rewrite the main method using our latest scientific advancements, specifically
the CommandExecutor class and the askOperation() method.
1. Add a public static Operation askOperation() throws IOException method to the Archiver class.
It must: 1.1. Use the ConsoleHelper class's methods
1.2. Ask the user for the number of the desired operation.

Hint:
To display the number of the "Create archive" operation, use: Operation.CREATE.ordinal()

1.3. Return the selected operation.
Example output of the askOperation() method:

Select an operation:0 - Zip files into an archive
1 - Add a file to an archive
2 - Remove a file from an archive
3 - Extract an archive
4 - View the contents of an archive
5 - Exit
2. Rewrite the main() method:
2.1. Declare a local Operation variable
2.2. In a loop, use the askOperation() method to ask for a new value for the variable in step 2.1, and use CommandExecutor.execute() to perform the operation.
2.3. Allow the user to exit the loop by selecting the Operation.EXIT operation
2.4. Wrap the askOperation() and execute(operation) calls in a try-catch block. If a NoSuchZipFileException occurs, use ConsoleHelper to display "You didn't select an archive or you selected an invalid file." If any other exceptions occur, display "An error occurred. Please check the entered data."
2.5. Be sure that the program continues running (continues to a new iteration of the loop) after handling exceptions.
3. Run the program and verify that the "exit" command works.

<strong>Archiver Part 9:</strong>

Now let's take care of another part of our archiver that is no less important.
Frequently, users want to create an archive from not just one file, but from an entire folder. In this case, zipping amounts to successively adding a ZipEntry for each file to the archive.
We need to write a FileManager class to get a list of all of the files in some folder. It must have a constructor that has a Path rootPath parameter that indicates the root (the folder containing the files we are interested in), and a List<Path> getFileList() method that should return a list of relative paths for all of the files in rootPath (including files in subfolders). The directory we will search can only be set once in the constructor. So, we can generate the list of files (relative paths) right in the constructor and save it in the class's List<Path> fileList variable.
We just have to decide how to gather up all of the files, even those that might be inside a folder, inside a folder, inside a folder, inside the folder we're looking at. Whoa, we almost entered a recursive loop. But maybe recursion can help us! Let's make a collectFileList(Path path) method that adds all the files inside the path parameter to the class's fileList variable, and calls itself recursively on subfolders.

1. Create a FileManager class with a FileManager(Path rootPath) throws IOException constructor
2. Declare and initialize the following private instance variables:
2.1. Path rootPath - The root path of the directory whose files we are interested in
2.2. List<Path> fileList - The list of relative paths of files inside rootPath
3. Create a getter for fileList
4. Implement a void collectFileList(Path path) throws IOException method that must:
4.1. Check to see if the path parameter is a regular file (use the Files.isRegularFile method), then get its path relative to rootPath and add it to fileList.
4.2. If the path parameter is a directory (determine this using the Files.isDirectory method), then go through all of the directory's contents and call collectFileList(Path path), passing in the discovered elements.
You can go through the all of the directory's contents by first getting a DirectoryStream using the Files class's newDirectoryStream method. Don't forget to close the DirectoryStream.
5. Call the collectFileList(rootPath) method inside the FileManager constructor.
6. Apply everything you've learned about encapsulation to this class.
In completing this task, you wrote an algorithm that traverses a file tree. But Java has a special FileVisitor interface for this. I highly recommend you learn how to use it.

<strong>Archiver Part 10:</strong>

It's time to refactor the ZipFileManager class. The createZip method has code that we're also going to need in the methods that will add files to or remove them from an archive, extract files, etc. We'll implement those methods later, but we can move the common code to separate methods now.
Additionally, the createZip method can only create an archive from a single file, but we would like to be able to zip an entire folder. We won't create a separate method for this, since you can pass both a directory and a regular file to the createZip(Path source) method.

Today's tasks:
1. Implement a private void addNewZipEntry(ZipOutputStream zipOutputStream, Path filePath, Path fileName) throws Exception method in the ZipFileManager class.
It must:
1.1. Create an InputStream for the file named fileName located in the filePath directory
1.2. Create a new ZipEntry, using fileName as the name after converting it to a String
1.3. Copy the data from the InputStream (from step 1.1) to the zipOutputStream parameter
1.4. Close the zip entry
1.5. Close the InputStream using try-with-resources
2. Replace part of the createZip method's code with a call to the addNewZipEntry method. Pass source.getParent() as the filePath parameter, and source.getFileName() as the filename.
3. Implement a private void copyData(InputStream in, OutputStream out) throws Exception method. It must read data from "in" and write to "out", until it reads through everything.
4. Replace part of the addNewZipEntry method's code with a call to the copyData method.
5. Let's return to createZip:
5.1. At the beginning of the method, check to see if the directory (zipFile.getParent()) where we will create zipFile exists; if not, then create it.
5.2. If source is a regular file (use Files.isRegularFile to check), then simply call addNewZipEntry.
5.3. If source is a directory (use Files.isDirectory to check), then:
5.3.1. Create a FileManager object, passing source to the constructor
5.3.2. Get a list of files from the file manager, and save them to the fileNames variable
5.3.3. Call addNewZipEntry(zipOutputStream, source, fileName) for each element of fileNames.
5.4. If source isn't a folder or a file, throw a PathNotFoundException.

<strong>Archiver Part 11:</strong>

Finally, we'll try to provide a decent implementation for the ZipCreateCommand class's execute() method that we added previously.

To do this, we need to:
1. Display "Creating an archive." at the beginning of the method. Don't forget that we are using the ConsoleHelper class to work with the console.
2. Create a new ZipFileManager object. Since we will also need to create this object in other commands, move the creation logic to a separate ZipFileManager getZipFileManager() throws Exception method in the ZipCommand class.
This method should:
2.1. Ask the user to enter the full path to the archive
2.2. Read the path into a String variable
2.3. Use the entered String to create a Path variable
2.4. Create a ZipFileManager object, passing the obtained path to the constructor
2.5. Return the created object
3. Ask the user to enter the full path to the file or directory to be zipped
4. Create a Path using the entered String
5. Call the ZipFileManager object's createZip() method, passing the path from step 4 to it
6. Display "Archive created."
7. Wrap the contents of the ZipCreateCommand's execute() method in a try-catch block and catch PathNotFoundException exceptions. If one occurs, display the message: "You didn't correctly enter a file name or directory."
8. Run the program and verify that the "Zip files into an archive" command works

<strong>Archiver Part 12:</strong>

Today we'll prepare to implement ZipContentCommand. It will be responsible for getting an archive's contents. The archive's contents are compressed files and folders, but we're interested in knowing not only the names of the archive's objects, but also their size before and after compression, their compression ratio, and the compression method used.
We'll create a FileProperties class that will be responsible for the properties of each file in the archive. Out set of properties consists of the following: file name, file size before and after compression, and compression method.
1. Create the FileProperties class
2. Add the following private instance variables to it:
2.1. String name - The file name
2.2. long size - The size in bytes
2.3. long compressedSize - The size after compression in bytes
2.4. int compressionMethod - The compression method
3. Add getters for them
4. Add a FileProperties(String name, long size, long compressedSize, int compressionMethod) constructor
5. Add a long getCompressionRatio() method that will calculate the compression ratio as: 100 - ((compressedSize * 100) / size)
6. Override the String toString() method so that it returns a String formatted as follows:
"name size KB (compressedSize KB) compression: compressionRatio%" if the file size is greater than 0. Otherwise, it should only return the file name. For example, a directory might have a size of 0. Don't forget to convert bytes to kilobytes. Note that the number of bytes in a kilobyte isn't the same as the number of grams in a kilogram. Nor is it the same as the number of chips in that bag... Hmm, maybe it's time for me to grab a snack…

<strong>Archiver Part 13:</strong>

Let's continue to move toward getting the contents of an archive. Let's write a getFileList() method inside the ZipFileManager class. It will return a list of the files in the archive, or rather a list of the properties of these files (we already implemented the FileProperties class).
Let's begin:
1. Add a List<FileProperties> getFileList() throws Exception method to the ZipFileManager class.
2. Inside that method, check whether zipFile is a regular file using a suitable method from the Files class. If it is not a file, throw a NoSuchZipFileException.
3. Create a list of FileProperties objects. We'll add the file properties to it.
4. Create a ZipInputStream for the file represented by the zipFile variable. As we've done before, wrap the creation of this object in a try-with-resources.
5. Go through all of the ZipEntries in the ZipInputStream
6. For each ZipEntry, read its contents (otherwise, we won't have information about its size). You can't find the size of a file in an archive without reading it. This is super easy to do using the copyData method and a temporary ByteArrayOutputStream buffer.
7. Get the name, size, compressed size, and compression method of the zip entry. Take a look at what else you can learn about the entry.
8. Create a FileProperties object using the obtained file data.
9. Add the object created in step 8 to the list from step 3.
10. Return the gathered information to the method that called the getFileList method.

<strong>Archiver Part 14:</strong>

Everything is ready to implement the ZipContentCommand class's execute() method:
1. Display "Viewing contents of the archive."
2. Create a ZipFileManager object using the getZipFileManager() method
3. Display "Archive contents:"
4. Get a list of the archive's files using the getFileList() method
5. Display the properties of each file. The previously implemented FileProperties.toString() method will come in handy
6. Display "Archive contents viewed."
7. Run the program and verify that the "View the contents of an archive" command works

<strong>Archiver Part 15:</strong>

It's time to try to extract something. To do this, add a public void extractAll(Path outputFolder) throws Exception method to the ZipFileManager class. The Path outputFolder parameter is the path to which we will extract our archive. You already have extensive experience working with zip entries and streams, so I'll only give you some hints on how to implement this method. You'll have to think carefully about how to do the following:
1. Check whether the zip file exists
2. If the outputFolder directory does not exist, then it needs to be created, along with all the folders that contain it.
3. Inside the archive, some files may be inside folders. In this case, the ZipEntry.getName() method doesn't exactly return the file name, as might be expected from the method name. Instead, it returns the file's relative path inside the archive. This relative path should be retained after the extraction process is complete, but it will become relative to the directory where we have extracted the archive
4. Implement the ZipExtractCommand class's execute() method by analogy with our implementation of ZipCreateCommand. Make a similar try-catch block, and simply change the displayed messages so that the user knows we are currently extracting an archive, and that we need the archive's full name and the directory to which we will extract the archive. Don't forget to call the ZipFileManager class's extractAll method, and not createZip like we did in the ZipCreateCommand
5. Run the program and enjoy the extracted results

Note:
Use Files.newInputStream(Path path) and Files.newOutputStream(Path path) to get read and write streams.

<strong>Archiver Part 16:</strong>

It's time to remove something from an archive. An archive is a tricky thing—you can't just go and remove an element.
Why? Imagine we decided to invent our own text compression algorithm. After examining the source text, we see that the phrase "being a programmer is cool" occurs often. In the second and subsequent instances of our phrase, we can replace the phrase with a note that this location represents the same phrase that was used in line S, starting from character K and N characters long. As we replace a lot of repeated phrases, the text shrink noticeably, but becomes unreadable to those not familiar with our compression algorithm.
Of course, we can recover the text (extract it). Now imagine what we need to remove the part of the text that is referenced by the compressed fragments. If we did this, our entire archive would become meaningless. That's why you can't simply remove part of an archive. This is a highly simplified description of a compression technique; in reality, everything is far more complicated.
As a result, to remove something from an archive, you need to create a new archive, and copy over everything except the removed file. Then you replace the old archive with the newly created one.

1. Add a public void removeFiles(List<Path> pathList) throws Exception method to the ZipFileManager class. It will remove files from an archive. A list of the relative paths to the files inside the archive will be passed in pathList.
It must:
1.1. Throw a NoSuchZipFileException if the archive file doesn't exist.
1.2. Create a temporary archive file in the default directory using the Files class's createTempFile() method.
1.3. Go through all the files of the original archive, and check whether the current file is in the list for removal.
- If the file is in the list, display a message indicating that we have removed the corresponding file and move on to the next file.
- If the file is not in the removal list, copy it to the new archive
1.4. Replace the original archive file with the temporary file we wrote all of the required files to. You need to do this using the Files class's move() method
2. Add a public void removeFile(Path path) throws Exception method to the ZipFileManager class. It should call the removeFiles method, creating a list out of a single element. This can be done using the Collections class's singletonList() method. Look up how it works.
3. Implement the ZipRemoveCommand class's execute() method: create a ZipFileManager object, ask the user which file to remove from which archive, and call the removeFile() method.
Do everything else as we did in the other commands. You don't have to catch the PathNotFoundException, since the removeFile() method shouldn't throw it.
4. Run the program and verify that removing a file from an archive works.

<strong>Archiver Part 17:</strong>

There's just one small trifle left: adding a file to an archive. It sounds shady, but that's exactly what we're going to do.
Adding files is similar to removing them: we create a temporary archive file, copy over all of the old archive's contents, and then add the new files. Then we replace the old archive file with the new one.
1. Add a public void addFiles(List<Path> absolutePathList) throws Exception method to the ZipFileManager class, where absolutePathList is a list of absolute paths to the files to be added.
This method must:
1.1. As usual, throw a NoSuchZipFileException if the archive file doesn't exist
1.2. Create a temporary archive file
1.3. Go through all the files in the original archive. Copy each file to the new archive, and add its name to a local list.
1.4. Go through the list of files to add.
1.5. Check whether each file exists and whether it is a regular file. If something isn't right, then throw a PathNotFoundException.
1.6. Check whether the file to be added is already in the archive (use the list in step 1.3). This is possible if the user has already added it.
- If the file isn't in the list, add it to the new archive and display a message indicating that the file was added to the archive
- If the file is in the list, just tell the user that the file already exists in the archive
1.7. Replace the original archive file with the temporary file, which already has all of the added files.
2. Add a public void addFile(Path absolutePath) throws Exception method to the ZipFileManager class, and implement it by calling the addFiles(), method—by analogy with what we did for removing a file.
3. Implement the ZipAddCommand class's execute() method. It's business as usual here, but don't forget to ask the user which file to add to which archive, and handle the PathNotFoundException that might be thrown by the addFile() method.
4. Run the program and verify that adding a file now works.

<strong>Archiver Part 18:</strong>

You're an excellent student! I've slightly tweaked your code. You can use this archiver for zipping files in everyday life. If you have the time and desire, try adding an operation that varies the zip compression level!

