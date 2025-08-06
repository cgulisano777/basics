package org.example.collections.practice.exercises;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Problem Statement:
 * Given an array of strings, return the top K most frequent words, sorted:
 * First by frequency (descending).
 * Then alphabetically if frequencies are equal.
 */
public class P004 implements Exercise {
    @Override
    public void resolveExercise() {
        String[] words = {
                "apple", "banana", "apple", "orange", "banana", "apple", "kiwi", "kiwi", "kiwi", "banana"
        };
        int k = 3;

        // Solution here:
        var sortedArr = Arrays.stream(words).sorted().toList();

        var grouped = sortedArr.stream()
                .collect(Collectors.groupingBy(
                        String::valueOf,
                        LinkedHashMap::new,
                        Collectors.counting()));

        var result = grouped.sequencedEntrySet().stream()
                .sorted((v1,v2) -> v2.getValue().compareTo(v1.getValue()))
                .toList();

        result.stream()
                .limit(k)
                .forEach(entry -> System.out.println(entry.getKey()));


        /**
         * While my solution works, there are some suggestions from AI:
         * 1- Sort only once (I am sorting by name then number)
         * 2- Limit the result before displaying it, so we save memory and CPU
         * 3- Use Comparator instead of lambda
         */
        System.out.println("\n- - - AI solution - - -");
        List<String> result2 = Arrays.stream(words)
                // This part creates a map  { K: String V: Count }
                .collect(Collectors.groupingBy(
                        word -> word,
                        Collectors.counting()
                ))
                // This part does the sorting by using a stream on the entrySet and applying a Comparator
                //   first reversing it and comparing the Values and then comparing by Key
                .entrySet().stream()
                .sorted(
                        Comparator.comparing(Map.Entry<String, Long>::getValue).reversed()
                                .thenComparing(Map.Entry::getKey)
                )
                // This limits the result to the desired number
                .limit(k)
                // Then here the result is collected as a List of sorted Keys
                .map(Map.Entry::getKey)
                .toList();

        // Then displaying is more simple...
        result2.forEach(System.out::println);


        /**
         * Another suggestion of the AI was to use record
         */
        System.out.println("\n- - - AI solution: with record - - -");
        List<WordFrequency> result3 = Arrays.stream(words)
                // This part creates a map  { K: String V: Count }
                .collect(Collectors.groupingBy(
                        word -> word,
                        Collectors.counting()
                ))
                // This part does the sorting by using a stream on the entrySet and applying a Comparator
                //   first reversing it and comparing the Values and then comparing by Key
                .entrySet().stream()
                // HERE is where it maps the data stream into a record
                .map(entry -> new WordFrequency(entry.getKey(), entry.getValue()))
                .sorted(
                        Comparator.comparing(WordFrequency::count).reversed()
                                .thenComparing(WordFrequency::word)
                )
                // This limits the result to the desired number
                .limit(k)
                // Then here the result is collected as a List WordFrequency (record)
                .toList();

        // Then displaying using the record object
        result3.forEach(wf -> System.out.println(wf.word() + ": " + wf.count()));
    }

    /**
     * A record in Java (introduced in Java 14 as a preview, finalized in Java 16) is a concise way to define an immutable data class. It automatically provides:
     * - final fields
     * - Constructor
     * - Getters
     * - toString(), equals(), and hashCode()
     * You can define a record called WordFrequency to hold each word and its count. This gives more semantic meaning than Map.Entry<String, Long>.
     * (in this case I am defining it inside the same file/class... but it should go separately)
     */
    record WordFrequency(String word, long count) { }
}
