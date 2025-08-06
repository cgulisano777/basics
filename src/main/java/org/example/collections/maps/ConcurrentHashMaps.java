package org.example.collections.maps;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 🔍 What is ConcurrentHashMap?
 * ConcurrentHashMap is a thread-safe variant of HashMap introduced in Java 5 (java.util.concurrent).
 * It allows concurrent access for read and write operations without locking the entire map.
 *
 * 📌 Key point: Unlike Collections.synchronizedMap(), which locks the whole map for every operation,
 * ConcurrentHashMap allows concurrent operations with fine-grained locking, improving scalability
 * and performance in multi-threaded apps.
 *
 * It uses a lock-free (non-blocking) read mechanism using volatile and CAS (Compare-And-Swap).
 *
 * Write operations use bucket-level locking via segments or synchronized blocks inside bins.
 */
public class ConcurrentHashMaps {

    private final ConcurrentHashMap<String, Integer> concurrentHashMap =
            new ConcurrentHashMap<>(Map.of(
                    "Apple", 1,
                    "Orange", 5,
                    "Banana", 3
            ));

    public void waysToCopy() {
        System.out.println(" ");
        System.out.println("Initial OG Map Entries:");
        displayMap(concurrentHashMap);

        System.out.println(" ");
        System.out.println("Copying with putAll method");
        var newMap = new ConcurrentHashMap<>();
        newMap.putAll(concurrentHashMap);
        displayMap(newMap);

        System.out.println("Copying with argument");
        var newMap2 = new ConcurrentHashMap<>(concurrentHashMap);
        displayMap(newMap2);
    }

    /**
     * Update map with parallel threads
     * ConcurrentHashMap has a fine-grained lockage mechanism while regular HashMap needs manual syclocks the entire Map
     *
     * For comparison same method is used in HashMaps class, there it takes 3ms or fail for lockage while
     * it works and takes 1ms in ConcurrentHashMaps
     */
    public void parallelUpdates() throws InterruptedException {
        System.out.println(" ");
        System.out.println("Parallel Execution Starts: ");

        long start = System.nanoTime(); // Start timer

        ConcurrentHashMap<String, Integer> freqMap = new ConcurrentHashMap<>();
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

    private static void getMerge(String item, ConcurrentHashMap<String, Integer> freqMap) {
        var threadName = Thread.currentThread().getName();
        System.out.println(threadName + " " + "Thread Running");
        System.out.println(threadName + " " + freqMap);
        freqMap.merge(item, 1, Integer::sum);
        System.out.println(threadName + " " + freqMap);
    }

    private <K, V> void displayMap(Map<K, V> map) {
        System.out.println("Displaying Map Entries");
        map.forEach((k,v) -> {
            System.out.println("k: " + k + " | v: " + v);
        });
    }
}
