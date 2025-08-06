package org.example.collections.maps;

import java.util.HashMap;
import java.util.IdentityHashMap;
import java.util.Map;

/**
 * Compares keys with == instead of .equals()
 *
 * IdentityHashMap is a Map implementation that compares keys (and values) using reference equality (==) instead of equals().
 * In other words:
 * HashMap uses .equals() to compare keys.
 * IdentityHashMap uses ==, which means it checks if both references point to the same object in memory.
 *
 * | Feature          | IdentityHashMap                                                                 |
 * | ---------------- | ------------------------------------------------------------------------------- |
 * | Equality check   | Uses `==` for both keys and values                                              |
 * | Hashing          | Uses `System.identityHashCode()` instead of `.hashCode()`                       |
 * | Allows null keys | ✅ Yes (only one, like `HashMap`)                                                |
 * | Performance      | Usually faster for small maps                                                   |
 * | Not thread-safe  | ❌ Like most `Map` implementations                                               |
 * | Use case         | Used in caching, serialization, object graph traversal (where identity matters) |
 *
 * Use Cases
 * Serialization frameworks: to detect object identity and avoid cycles.
 * Object reference tracking: like when walking through object graphs.
 * Performance: when you want fast lookup by reference identity.
 */
public class IdentityHashMaps {

    public void vsHashMap() {
        var hashMap = new HashMap<String, String>();
        var identityMap = new IdentityHashMap<String, String>();

        var a1 = new String("key");
        var a2 = new String("key"); // same content but different object

        hashMap.put(a1, "value1");
        hashMap.put(a2, "value2");

        identityMap.put(a1, "value1");
        identityMap.put(a2, "value2");

        System.out.println("HashMap size: " + hashMap.size());         // 1
        displayMap(hashMap);
        System.out.println("\nIdentityHashMap size: " + identityMap.size()); // 2
        System.out.println("trying to fetch 'key' but it is stored as object hash: " + identityMap.get("key"));
        displayMap(identityMap);
    }


    private <K, V> void displayMap(Map<K, V> map) {
        System.out.println("Displaying Map Entries");
        map.forEach((k,v) -> System.out.println("k: " + k + " | v: " + v));
    }
}
