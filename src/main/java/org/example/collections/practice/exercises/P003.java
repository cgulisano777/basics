package org.example.collections.practice.exercises;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Problem Statement:
 * Group Anagrams by Frequency Signature
 * Given an array of strings, group all anagrams together — but using a frequency map
 * as the key (instead of sorting the string).
 */
public class P003 implements Exercise {
    private final String[] words = {
            "listen", "silent", "enlist",
            "rat", "tar", "art",
            "evil", "vile", "veil", "live",
            "bat", "tab", "tba",
            "dog", "god", "gdo"
    };

    private final LinkedHashMap<String, List<String>> resultMap = new LinkedHashMap<>();


    @Override
    public void resolveExercise() {
        // Solution here:

        // test key generation
//        Arrays.stream(words).forEach(item -> System.out.println(item + "  -> code: " + wordIntoKey(item)));

        // todo :  this is my solutio, it works but see AI soluc too
        Arrays.stream(words).forEach(item -> {
            var key = wordIntoKey(item);
            var val = resultMap.get(key);
            if (null == val) {
                val = new ArrayList<>();
            }
            val.add(item);

            resultMap.put(key, val);
        });

        // Result:
        resultMap.entrySet().forEach(System.out::println);
        resultMap.forEach((key, value) -> value.sort(String::compareTo));
        System.out.println("\nSorted: ");
        resultMap.entrySet().forEach(System.out::println);


        System.out.println("\n- - AI Solution - -");
        // Using Collectors.groupingBy
        Map<String, List<String>> grouped =
                Arrays.stream(words).collect(Collectors
                        .groupingBy(this::wordIntoKey,
                                LinkedHashMap::new,
                                Collectors.toList()));

        Map<String, List<String>> grouped2 =
                Arrays.stream(words).collect(Collectors
                        .groupingBy(this::wordIntoKey,
                                LinkedHashMap::new,
                                Collectors.toList()));

        // Using same iterator to both sort and display
        grouped.forEach((k, v) -> {
            Collections.sort(v);
            System.out.println(k + " -> " + v);
        });
    }

//    private String wordIntoKey(String word) {
//        int val = 0;
//        for (byte aByte : word.getBytes()) {
//            val = val + aByte;
//        }
//        return String.valueOf(val);
//    }

    /**
     * FreqKey generation suggested by AI to avoid grouping non-anagrams
     */
//    private String wordIntoKey(String word) {
//        int[] freq = new int[26]; // a-z
//
//        for (char c : word.toCharArray()) {
//            freq[c - 'a']++;
//        }
//
//        return Arrays.toString(freq); // key example: [1,0,0,0,...]
//    }

    /**
     * option 2 by AI
     */
    private String wordIntoKey(String word) {
        int[] freq = new int[26];
        for (char c : word.toCharArray()) {
            freq[c - 'a']++;
        }

        StringBuilder sb = new StringBuilder();
        for (int count : freq) {
            sb.append('#').append(count);
        }

        return sb.toString();
    }


}
