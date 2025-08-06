package org.example.collections.maps;

import java.util.Map;
import java.util.WeakHashMap;

/**
 * Keys are weak references (GC collected if no strong ref)
 *
 * Property	            Description
 * Key Reference Type	Keys are wrapped in java.lang.ref.WeakReference
 * Automatic Cleanup	Entries disappear automatically when key is GC-ed
 * Use Case	            Useful for caches, listeners, or metadata storage
 * Not Thread-safe	    Must be wrapped or externally synchronized for multithreaded access
 * Allows nulls	        Yes, both null keys and values are allowed
 *
 * | Use Case                | Why `WeakHashMap` is a Good Fit                           |
 * | ----------------------- | --------------------------------------------------------- |
 * | Caching objects by keys | Automatically clears unused keys                          |
 * | Storing metadata        | Attaches data to objects without affecting their lifetime |
 * | Event listener systems  | Prevents memory leaks when listeners go out of scope      |
 */
public class WeakHashMaps {

    /**
     * Example of GC removing entries that are no longer reachable
     *
     * Only keys are weakly referenced — values are held strongly.
     * If you use a String literal or other strongly referenced object as a key, GC will never remove it.
     * Not good for use cases where predictable lifetime of entries is required.
     */
    public void garbageCollectorRemovesEntry() {
        Map<Object, String> weakMap = new WeakHashMap<>();

        // Add the Entry with the Object reference
        var key = new Object();
        var key2 = new Object();
        var key3 = new Object();
        weakMap.put(key, "Some value");
        weakMap.put(key2, "Some value");
        weakMap.put(key3, "Some value");

        System.out.println(" ");
        System.out.println("Before GC:");
        displayMap(weakMap);

        // Making the objects "no longer strongly reachable" so GC can collect them
        key = null;
        key3 = null;
        System.gc(); // Hint to the GC

        try {
            Thread.sleep(100); // Give GC a moment to run
        } catch (Exception ex) {
            System.out.println("There was a problem: " + ex.getMessage());
        }

        // key2 is not null so it is reachable and GC did not collect it
        System.out.println("After GC:");
        displayMap(weakMap);
    }

    private <K, V> void displayMap(Map<K, V> map) {
        System.out.println("Displaying Map Entries");
        map.forEach((k,v) -> {
            System.out.println("k: " + k + " | v: " + v);
        });
    }
}
