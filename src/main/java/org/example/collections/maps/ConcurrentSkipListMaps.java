package org.example.collections.maps;

import java.util.Map;
import java.util.concurrent.ConcurrentSkipListMap;

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
            Map.of("kiwi",3,
                    "Apple", 1,
                    "Orange", 2
            ));

    public void waysToCopy() {
        System.out.println(" ");
        System.out.println("Initial OG Map Entries:");
        displayMap(skipListMap);
    }

    private <K, V> void displayMap(Map<K, V> map) {
        System.out.println("Displaying Map Entries");
        map.forEach((k,v) -> {
            System.out.println("k: " + k + " | v: " + v);
        });
    }
}
