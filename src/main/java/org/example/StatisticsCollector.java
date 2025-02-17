package org.example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class StatisticsCollector {
    // Lists to store different data types for statistics collection
    private final List<Long> integers = new ArrayList<>();
    private final List<Double> floats = new ArrayList<>();
    private final List<String> strings = new ArrayList<>();

    // Method to add an integer value to the list
    public void addInteger(long value) {
        integers.add(value);
    }

    // Method to add a float value to the list
    public void addFloat(double value) {
        floats.add(value);
    }

    // Method to add a string value to the list
    public void addString(String value) {
        strings.add(value);
    }

    // Method to print statistics based on the 'full' flag
    public void printStatistics(boolean full) {
        System.out.println("Statistics:");

        printIntegerStats(full);
        printFloatStats(full);
        printStringStats(full);
    }

    // Method to print integer statistics
    private void printIntegerStats(boolean full) {
        System.out.println("Integers: " + integers.size());
        // If 'full' is true and the list is not empty, calculate and print detailed stats
        if (full && !integers.isEmpty()) {
            try {
                long sum = integers.stream().mapToLong(Long::longValue).sum();
                double average = sum / (double) integers.size();
                long min = Collections.min(integers);
                long max = Collections.max(integers);

                // Print the calculated statistics
                System.out.println("  Min: " + min);
                System.out.println("  Max: " + max);
                System.out.println("  Sum: " + sum);
                System.out.println("  Average: " + average);
            } catch (Exception e) {
                System.out.println("  Error processing integer statistics: " + e.getMessage());
            }
        }
    }

    // Method to print float statistics
    private void printFloatStats(boolean full) {
        System.out.println("Floats: " + floats.size());

        // If 'full' is true and the list is not empty, calculate and print detailed stats
        if (full && !floats.isEmpty()) {
            try {
                double sum = floats.stream().mapToDouble(Double::doubleValue).sum();
                double average = sum / floats.size();
                double min = Collections.min(floats);
                double max = Collections.max(floats);

                System.out.println("  Min: " + min);
                System.out.println("  Max: " + max);
                System.out.println("  Sum: " + sum);
                System.out.println("  Average: " + average);
            } catch (Exception e) {
                System.out.println("  Error processing float statistics: " + e.getMessage());
            }
        }
    }

    // Method to print string statistics
    private void printStringStats(boolean full) {
        System.out.println("Strings: " + strings.size());

        // If 'full' is true and the list is not empty, calculate and print detailed stats
        if (full && !strings.isEmpty()) {
            try {
                Optional<String> shortest = strings.stream().min((a, b) -> Integer.compare(a.length(), b.length()));
                Optional<String> longest = strings.stream().max((a, b) -> Integer.compare(a.length(), b.length()));

                // Print the shortest and longest strings (if available)
                System.out.println("  Shortest: " + shortest.orElse("N/A"));
                System.out.println("  Longest: " + longest.orElse("N/A"));
            } catch (Exception e) {
                System.out.println("  Error processing string statistics: " + e.getMessage());
            }
        }
    }
}
