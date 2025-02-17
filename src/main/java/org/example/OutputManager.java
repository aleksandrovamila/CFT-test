package org.example;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class OutputManager {
    private final ArgumentParser args; // ArgumentParser object to hold parsed arguments
    private final Map<String, BufferedWriter> writers = new HashMap<>(); // A map to hold BufferedWriter instances for each file

    // Constructor that initializes the OutputManager with parsed arguments
    public OutputManager(ArgumentParser args) {
        this.args = args;
        initializeFiles();// Initialize files by either creating new or clearing existing ones
    }

    // Method to initialize output files, deleting them if necessary (based on the appendMode flag)
    private void initializeFiles() {
        if (!args.appendMode) { // Only clear the files if the append mode (-a) is NOT set
            // Loop through all file types and delete them if they already exist
            for (String fileType : new String[]{"integers.txt", "floats.txt", "strings.txt"}) {
                File file = new File(getFileName(fileType));
                if (file.exists()) {
                    file.delete(); // Delete the file if it exists
                }
            }
        }
    }

    // Method to write integer data to the corresponding output file
    public void writeInteger(String data) {
        writeToFile(getFileName("integers.txt"), data);
    }

    // Method to write float data to the corresponding output file
    public void writeFloat(String data) {
        writeToFile(getFileName("floats.txt"), data);
    }

    // Method to write string data to the corresponding output file
    public void writeString(String data) {
        writeToFile(getFileName("strings.txt"), data);
    }

    // Method to get the full file name, including directory and prefix, with fallback to default file name
    private String getFileName(String defaultName) {
        String directory = args.outputPath;

        // Convert Unix-style absolute path to a valid system path (handles Windows compatibility)
        if (directory.startsWith("/")) {
            directory = System.getProperty("user.dir") + directory.replace("/", File.separator);
        }

        // Check if the directory exists; if not, try to create it
        File dir = new File(directory);
        if (!dir.exists() && !dir.mkdirs()) {
            System.out.println("Error: Unable to create output directory: " + directory);
            return defaultName;// Return the default file name if directory creation fails
        }

        return directory + File.separator + args.prefix + defaultName;
    }

    // Method to write data to a file, ensuring that a BufferedWriter is used for efficiency
    private synchronized void writeToFile(String fileName, String data) {
        try {
            // If the writer for this file doesn't already exist, create a new one
            BufferedWriter writer = writers.computeIfAbsent(fileName, this::createWriter);
            if (writer != null) {
                writer.write(data);// Write the data to the file
                writer.newLine();// Add a new line after the data
                writer.flush();// Flush the writer to ensure data is actually written
            }
        } catch (IOException e) {
            System.out.println("Error writing to file: " + fileName);// Handle errors in writing to the file
        }
    }

    // Method to create a BufferedWriter for a given file
    private BufferedWriter createWriter(String fileName) {
        try {
            // Create a BufferedWriter that appends data if appendMode is true
            return new BufferedWriter(new FileWriter(fileName, args.appendMode));
        } catch (IOException e) {
            System.out.println("Error opening file for writing: " + fileName);// Handle errors in opening the file
            return null;
        }
    }
}
