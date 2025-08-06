package org.example.collections.maps;

import java.util.*;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Sorted by keys (uses Red-Black Tree)
 */
public class TreeMaps {

    /**
     * See NavigableMap / SortedMap	Interfaces implemented by TreeMap for range queries, etc.
     */


    /**
     * ⚠️ Note: This is double-brace initialization, which creates an anonymous subclass
     * — not recommended for production code due to memory leaks and serialization issues.
     */
    private final TreeMap<String, Integer> badInitializedMap = new TreeMap<>() {{
        put("Apple", 1);
        put("Orange", 3);
        put("Banana", 2);
    }};

    /**
     * Immutable TreeMap initialization
     */
    Map<String, Integer> map = Map.of(
            "Apple", 1,
            "Orange", 3,
            "Banana", 2
    );
    TreeMap<String, Integer> sortedMap = new TreeMap<>(map);



    public void waysToCopy() {
        System.out.println(" ");
        System.out.println("Initial OG bad initialized TreeMap:");
        displayMap(badInitializedMap);

        System.out.println(" ");
        System.out.println("Initial OG initialized by argument TreeMap:");
        displayMap(sortedMap);

        var clonedTreeMap = (TreeMap<String, String>) sortedMap.clone();
        System.out.println(" ");
        System.out.println("Cloned TreeMap:");
        displayMap(clonedTreeMap);

        var submapTreeMap = sortedMap.subMap("Apple", "Orange");
        System.out.println(" ");
        System.out.println("SubMap TreeMap:");
        displayMap(submapTreeMap);

        var inclusiveSubmapTreeMap = sortedMap.subMap("Apple", true, "Orange", true);
        System.out.println(" ");
        System.out.println("Inclusive SubMap TreeMap:");
        displayMap(inclusiveSubmapTreeMap);

        var exclusiveSubmapTreeMap = sortedMap.subMap("Apple", false, "Orange", false);
        System.out.println(" ");
        System.out.println("Exclusive SubMap TreeMap:");
        displayMap(exclusiveSubmapTreeMap);
    }

    /**
     * Get the entry set for the least key greater or equal to the one provided
     */
    public void ceilingEntry() {
        System.out.println(" ");
        System.out.println("TreeMap:");
        var ceilingEntry = sortedMap.ceilingEntry("App");
        System.out.println("Displaying ceilingEntry using \"App\" as arg: " + ceilingEntry);
    }

    /**
     * Get the least key greater or equal to the one provided
     * Use case:
     * Autocompletion, suggesting the closest matches to input.
     */
    public void ceilingKey() {
        System.out.println(" ");
        System.out.println("TreeMap:");
        var ceilingKey = sortedMap.ceilingKey("Ban");
        System.out.println("Displaying ceilingKey using \"Ban\" as arg: " + ceilingKey);
    }

    /**
     * Get all keys less than or greater than or equal to a value.
     */
    public void headMap() {
        System.out.println(" ");
        System.out.println("TreeMap:");
        var headMap = sortedMap.headMap("Orange");
        System.out.println("Displaying headMap using \"Orange\" as arg: " + headMap);
    }

    /**
     * Get all keys less than or greater than or equal to a value.
     */
    public void tailMap() {
        System.out.println(" ");
        System.out.println("TreeMap:");
        var tailMap = sortedMap.tailMap("Banana");
        System.out.println("Displaying tailMap using \"Banana\" as arg: " + tailMap);
    }

    /**
     * Get Strictly greater or smaller than key.
     * Use case:
     * Navigating ordered datasets (e.g., calendars, leaderboards).
     */
    public void higherKey() {
        System.out.println(" ");
        System.out.println("TreeMap:");
        var higherKey = sortedMap.higherKey("Apple");
        System.out.println("Displaying higherKey using \"Apple\" as arg: " + higherKey);
    }

    /**
     * Check what comparator is being used (if any):
     */
    public void comparator() {
        var sortedMapWithComparator = new TreeMap<String, Integer>(Comparator.reverseOrder());
        sortedMapWithComparator.putAll(sortedMap);
        System.out.println(" ");
        System.out.println("TreeMap:");
        var comparator = sortedMapWithComparator.comparator();
        System.out.println("Displaying comparator: " + comparator.getClass().getName());
        System.out.println("Displaying reverse order sorted map: " + sortedMapWithComparator);
    }

    /**
     * Get descending keySet
     * Use case:
     * You need sorted keys for iteration in either direction.
     */
    public void descendingKeySet() {
        System.out.println(" ");
        System.out.println("TreeMap:");

        var descKeySet = sortedMap.descendingKeySet();

        System.out.println("Displaying DescendingKeySet");
        descKeySet.forEach(item -> {
            System.out.println("v: " + item);
        });
    }

    /**
     * Update map with parallel threads
     * ConcurrentHashMap has a fine-grained lockage mechanism while TreeMap is not ThreadSafe
     * we need to manually synchronize it
     */
    public void parallelUpdates() throws InterruptedException {
        System.out.println(" ");
        System.out.println("Parallel Execution Starts: ");

        long start = System.nanoTime(); // Start timer

//        TreeMap<String, Integer> freqMap = new TreeMap<>();
        SortedMap<String, Integer> freqMap = Collections.synchronizedSortedMap(new TreeMap<>());

        List<String> items = List.of("A", "B", "A", "C", "B", "A", "B", "A", "C", "B", "A", "B", "A", "C", "B", "A", "B", "A", "C", "B", "A");

        try (var executor = Executors.newFixedThreadPool(4)) {
            for (String item : items) {
                executor.execute(() -> getMerge(item, freqMap));
            }

            executor.shutdown();
            executor.awaitTermination(1, TimeUnit.MINUTES);
        }

        long end = System.nanoTime(); // End timer
        long durationMillis = TimeUnit.NANOSECONDS.toMillis(end - start);

        System.out.println(freqMap); // Expected counts
        System.out.println("Execution Time: " + durationMillis + " ms");
    }

    private static void getMerge(String item, SortedMap<String, Integer> freqMap) {
        var threadName = Thread.currentThread().getName();
        System.out.println(threadName + " " + "Thread Running");
        System.out.println(threadName + " " + freqMap);
        synchronized (freqMap) {
            freqMap.merge(item, 1, Integer::sum);
        }
        System.out.println(threadName + " " + freqMap);
    }


    private <K, V> void displayMap(Map<K, V> map) {
        System.out.println("Displaying Map Entries");
        map.forEach((k,v) -> {
            System.out.println("k: " + k + " | v: " + v);
        });
    }
}
