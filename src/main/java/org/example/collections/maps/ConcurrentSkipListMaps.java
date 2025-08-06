package org.example.collections.maps;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * thread-safe and sorted, alternative to TreeMap for concurrency
 *
 * ConcurrentSkipListMap<K, V> is:
 * A concurrent version of TreeMap
 * Sorted naturally or by a provided Comparator
 * Thread-safe without locking the entire map
 * Backed by a Skip List data structure (instead of a balanced tree)
 * It’s part of java.util.concurrent and implements ConcurrentNavigableMap<K, V>.
 *
 * Thread-safe        Safe to use from multiple threads without external synchronization
 * Sorted	          Maintains keys in ascending order
 * Lock-free reads    Reads are usually non-blocking (like get, containsKey)
 * Concurrent updates Supports scalable multi-threaded inserts/removals
 * Navigable          Like TreeMap, provides methods like ceilingEntry(), floorKey()
 */
public class ConcurrentSkipListMaps {

    private final ConcurrentSkipListMap<String, Integer> skipListMap = new ConcurrentSkipListMap<>(
            Map.of("K",2,
                    "A", 1,
                    "O", 3
            ));

    public void waysToCopy() {
        System.out.println(" ");
        System.out.println("Initial OG Map Entries:");
        displayMap(skipListMap);

        System.out.println(" ");
        System.out.println("\nCopying with putAll method");
        var skipListMap2 = new ConcurrentSkipListMap<String, String>();
        skipListMap2.putAll((Map) skipListMap);
        displayMap(skipListMap2);

        System.out.println("\nCopying with argument");
        var skipListMap3 = new ConcurrentSkipListMap<>(skipListMap);
        displayMap(skipListMap3);

        ConcurrentSkipListMap<String, Integer> concurrentSkipListMap = new ConcurrentSkipListMap<>(Comparator.reverseOrder());
        concurrentSkipListMap.put("K", 2);
        concurrentSkipListMap.put("A", 1);
        concurrentSkipListMap.put("O", 3);

        System.out.println("\nNew ConcurrentSkipListMap with put() and a provided reverseOrder Comparator");
        displayMap(concurrentSkipListMap);

        System.out.println("\nNew CLONED ConcurrentSkipListMap");
        var clonedMap = concurrentSkipListMap.clone();
        displayMap(clonedMap);
    }

    /**
     * Update map with parallel threads
     * ConcurrentSkipListMaps has a fine-grained lockage mechanism
     * we need to manually synchronize it
     */
    public void parallelUpdates() {
        System.out.println(" ");
        System.out.println("Parallel Execution Starts: ");

        List<String> items = List.of("A", "B", "A", "C", "B", "A", "B", "A", "C", "B", "A", "B", "A", "C", "B", "A", "B", "A", "C", "B", "A");
        var freqMap = new ConcurrentSkipListMap<String, Integer>();

        long start = System.nanoTime(); // Start timer

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

    public void descendingMap() {
        ConcurrentSkipListMap<String, Integer> concurrentSkipListMap = new ConcurrentSkipListMap<>();
        concurrentSkipListMap.put("K", 2);
        concurrentSkipListMap.put("A", 1);
        concurrentSkipListMap.put("O", 3);

        System.out.println(" ");
        System.out.println("Descending Map");

        System.out.println("\nOG ConcurrentSkipListMap");
        displayMap(concurrentSkipListMap);

        System.out.println("\nNew Descending Map form ConcurrentSkipListMap");
        var descendingMap = concurrentSkipListMap.descendingMap();
        displayMap(descendingMap);

        System.out.println("\nNew Descending Map form the previous Descending Map");
        var descFromDesc = descendingMap.descendingMap();
        displayMap(descFromDesc);

        concurrentSkipListMap.remove("K");
        concurrentSkipListMap.put("B", 2);
        System.out.println("\nAffecting OG map (replacing K by B), expecting changes to be reflected on views:");
        displayMap(concurrentSkipListMap);
        System.out.println("New Descending Map form ConcurrentSkipListMap");
        displayMap(descendingMap);
        System.out.println("New Descending Map form the previous Descending Map");
        displayMap(descFromDesc);
    }

    private static void getMerge(String item, ConcurrentSkipListMap<String, Integer> freqMap) {
        var threadName = Thread.currentThread().getName();
        System.out.println(threadName + " " + "Thread Running");
        System.out.println(threadName + " " + freqMap);
        freqMap.merge(item, 1, Integer::sum);
        System.out.println(threadName + " " + freqMap);
    }

    private <K, V> void displayMap(Map<K, V> map) {
        System.out.println("Displaying Map Entries");
        map.forEach((k,v) -> System.out.println("k: " + k + " | v: " + v));
    }
}
