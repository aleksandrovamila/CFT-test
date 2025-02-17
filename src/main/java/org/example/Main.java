package org.example;

public class Main {
    public static void main(String[] args) {
        // Create an instance of ArgumentParser to handle and parse command-line arguments
        ArgumentParser parser = new ArgumentParser();
        parser.parse(args);// Parse the arguments passed to the program

        // Create an instance of FileProcessor, passing the parsed arguments
        FileProcessor processor = new FileProcessor(parser);
        // Process the files based on the parsed arguments
        processor.processFiles();

    }
}