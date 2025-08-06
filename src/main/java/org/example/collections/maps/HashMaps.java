package org.example.collections.maps;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class HashMaps {
    private final Map<String, String> hashMap = Map.of(
            "T1","A",
            "T2","B",
            "T3","C"
    );

    public void waysToCopy() {
        System.out.println(" ");
        System.out.println("Initial OG Map Entries:");
        displayMap(hashMap);

        System.out.println(" ");
        System.out.println("Copying with putAll method");
        var newMap = new HashMap<String, String>();
        newMap.putAll(hashMap);
        displayMap(newMap);

        System.out.println("Copying with argument");
        var newMap2 = new HashMap<>(hashMap);
        displayMap(newMap2);
    }

    /**
     * Update map with parallel threads
     * ConcurrentHashMap has a fine-grained lockage mechanism while HashMap is not ThreadSafe
     * we need to manually synchronize it
     */
    public void parallelUpdates() {
        System.out.println(" ");
        System.out.println("Parallel Execution Starts: ");

        long start = System.nanoTime(); // Start timer

//        HashMap<String, Integer> freqMap = new HashMap<>();
        Map<String, Integer> freqMap = Collections.synchronizedMap(new HashMap<>());

        List<String> items = List.of("A", "B", "A", "C", "B", "A", "B", "A", "C", "B", "A", "B", "A", "C", "B", "A", "B", "A", "C", "B", "A");

        try (var executor = Executors.newFixedThreadPool(4)) {
            for (String item : items) {
                executor.execute(() -> getMerge(item, freqMap));
            }

            executor.shutdown();
            executor.awaitTermination(1, TimeUnit.MINUTES);
        } catch (Exception e) {
            System.out.println("Something went terribly wrong: " + e.getMessage());
        }

        long end = System.nanoTime(); // End timer
        long durationMillis = TimeUnit.NANOSECONDS.toMillis(end - start);

        System.out.println(freqMap); // Expected counts
        System.out.println("Execution Time: " + durationMillis + " ms");
    }

    /**
     * We can override methods to customize behavior
     */
    public void playingWithOverride() {
        System.out.println(" ");
        System.out.println("Overriding the get method to also display info: ");
        var hashMap = new HashMap<>(Map.of("ExpectedResult", 1)) {
            @Override
            public Integer get(Object key) {
                System.out.println("Accessing key: " + key);
                return super.get(key);
            }
        };
        hashMap.get("ExpectedResult");
    }

    private static void getMerge(String item, Map<String, Integer> freqMap) {
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
