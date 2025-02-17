package org.example;

import java.util.ArrayList;
import java.util.List;

public class ArgumentParser {
    public String outputPath = "."; // Default output directory (-o), current directory if not specified
    public String prefix = ""; // Prefix for output file names (-p)
    public boolean appendMode = false; // Append mode (-a), if true data is appended to files, otherwise files are overwritten
    public boolean fullStats = false; // Full statistics mode (-f), if true, detailed statistics are shown
    public boolean shortStats = false; // Short statistics mode (-s), if true, only basic statistics are shown
    public List<String> inputFiles = new ArrayList<>(); // List of input files to process

    // Method to parse command line arguments
    public void parse(String[] args) {
        for (int i = 0; i < args.length; i++) {
            switch (args[i]) {
                case "-o":
                    // Handle output directory argument
                    if (i + 1 < args.length) {
                        outputPath = args[++i];
                    } else {
                        showError("Option -o requires an argument."); // Show error if no directory is provided
                    }
                    break;
                case "-p":
                    // Handle prefix argument
                    if (i + 1 < args.length && !args[i + 1].startsWith("-")) {
                        String potentialPrefix = args[++i];

                        // Check if prefix resembles a filename (should not contain dot or path separator)
                        if (potentialPrefix.contains(".") || potentialPrefix.contains("/") || potentialPrefix.contains("\\")) {
                            showError("Option -p requires an argument.");
                        }

                        prefix = potentialPrefix;// Set prefix for output files
                    } else {
                        showError("Option -p requires an argument.");
                    }
                    break;
                case "-a":
                    // Handle append mode argument
                    appendMode = true;
                    break;
                case "-s":
                    // Handle short statistics mode argument
                    shortStats = true;
                    break;
                case "-f":
                    // Handle full statistics mode argument
                    fullStats = true;
                    break;
                default:
                    // If the argument is not recognized, assume it's an input file
                    inputFiles.add(args[i]);
                    break;
            }
        }

        // Check if both -s and -f options are selected together (not allowed)
        if (shortStats && fullStats) {
            showError("Options -s and -f cannot be used together.");
        }

        // Ensure at least one input file is provided
        if (inputFiles.isEmpty()) {
            showError("No input files specified.");
        }
    }

    // Helper method to display an error message and exit the program
    private void showError(String message) {
        System.err.println("Error: " + message);
        System.exit(1);
    }
}
