package org.example;

import java.io.*;

public class FileProcessor {
    private final ArgumentParser args; // ArgumentParser object for command line arguments
    private final OutputManager outputManager; // OutputManager object to manage output files
    private final StatisticsCollector statisticsCollector; // StatisticsCollector to track statistics of processed data

    // Constructor that initializes the required objects
    public FileProcessor(ArgumentParser args) {
        this.args = args;
        this.outputManager = new OutputManager(args); // Initialize OutputManager with parsed arguments
        this.statisticsCollector = new StatisticsCollector(); // Initialize StatisticsCollector
    }

    // Method to process each input file
    public void processFiles() {
        // Iterate over all input files provided by the user
        for (String fileName : args.inputFiles) {
            File file = new File(fileName); // Create a File object for the current file
            if (!file.exists() || !file.canRead()) { // Check if the file exists and is readable
                System.out.println("Error: File " + fileName + " not found."); // Show an error message if not found
                continue; // Skip the current file and move to the next one
            }
            processFile(file); // Process the file if it exists and is readable
        }
        // Print statistics (full or short based on user input)
        statisticsCollector.printStatistics(args.fullStats);
    }

    // Method to process a single file
    private void processFile(File file) {
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) { // Use BufferedReader to read the file
            String line;
            while ((line = reader.readLine()) != null) { // Read each line of the file
                processLine(line); // Process the current line
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + file.getName()); // Show an error if the file cannot be read
        }
    }

    // Method to process a line of text
    private void processLine(String line) {
        // Check if the line contains an integer (integer regular expression)
        if (line.matches("-?\\d+")) {
            try {
                long number = Long.parseLong(line); // Try to parse the number as a long
                outputManager.writeInteger(line); // Write the integer to the output file
                statisticsCollector.addInteger(number); // Add the integer to the statistics collector
            } catch (NumberFormatException e) {
                System.out.println("Warning: Number too large for long: " + line); // Show a warning if the number is too large for a long
            }
        }
        // Check if the line contains a floating-point number (floating-point regular expression)
        else if (line.matches("-?(\\d+\\.\\d*|\\.\\d+)([eE][-+]?\\d+)?")) {
            outputManager.writeFloat(line); // Write the float to the output file
            statisticsCollector.addFloat(Double.parseDouble(line)); // Add the float to the statistics collector
        }
        // If the line is neither an integer nor a float, treat it as a string
        else {
            outputManager.writeString(line); // Write the string to the output file
            statisticsCollector.addString(line); // Add the string to the statistics collector
        }
    }
}
