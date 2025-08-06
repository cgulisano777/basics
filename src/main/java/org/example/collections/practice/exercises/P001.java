package org.example.collections.practice.exercises;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

/**
 * Given an Array of characters : B B B A C A B E F A C B A B C Z I
 * Obtain the N first most repeated chars
 */
public class P001 implements Exercise {
    // Solution here:
    private final String[] arr = "B B B A C A B E F A C B A B C Z I".split(" ");
    private final int k = 2;

    @Override
    public void resolveExercise() {
        System.out.println("Initial Values:");
        System.out.println(Arrays.stream(arr).toList());

        // Group counting
        var groupedVals = new HashMap<String, Integer>();
        Arrays.stream(arr).forEach(item -> groupedVals.merge(item, 1, Integer::sum));

        // Sort descending
        var sorted = new ArrayList<>(groupedVals.entrySet());
        sorted.sort((v1, v2) -> v2.getValue().compareTo(v1.getValue()));

        System.out.println("\nSOLUTION:");
        sorted.stream().limit(k).forEach(System.out::println);
    }
}
