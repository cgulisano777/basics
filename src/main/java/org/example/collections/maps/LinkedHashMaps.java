package org.example.collections.maps;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Maintains insertion order
 *
 * LinkedHashMap is a hash table + linked list implementation of the Map interface.
 * It preserves the insertion order (or access order if configured).
 * It extends HashMap and maintains a doubly-linked list that defines the iteration ordering.
 */
public class LinkedHashMaps {

    Map<String, Integer> linkedHashMap = new LinkedHashMap<>(
            Map.of("Apple", 1,
                    "Orange", 5,
                    "Banana", 3
            ));

    public void waysToCopy() {
        System.out.println(" ");
        System.out.println("Initial OG Map Entries:");
        displayMap(linkedHashMap);

        System.out.println(" ");
        System.out.println("Copying with putAll method");
        var newMap = new LinkedHashMap<String, Integer>();
        newMap.putAll(linkedHashMap);
        displayMap(newMap);

        System.out.println("Copying with argument");
        var newMap2 = new LinkedHashMap<>(linkedHashMap);
        displayMap(newMap2);
    }

    public void accessOrder() {
        var linkedHashMap = new LinkedHashMap<String, Integer>(16, 0.75f, true);
        linkedHashMap.put("Car", 1);
        linkedHashMap.put("Truck", 9);
        linkedHashMap.put("Bike", 2);

        System.out.println(" ");
        System.out.println("Initial Map Entries:");
        displayMap(linkedHashMap);

        System.out.println("Accessing Car {linkedHashMap.get(\"Car\")} :" + linkedHashMap.get("Car"));
        System.out.println("Displaying Entries after access:");
        displayMap(linkedHashMap);

        System.out.println("Accessing Bike {linkedHashMap.get(\"Bike\")} :" + linkedHashMap.get("Bike"));
        System.out.println("Displaying Entries after access:");
        displayMap(linkedHashMap);
    }

    /**
     * Override removeEldestEntry method (does nothing by default) to remove entries after 5 (the oldest)
     */
    public void removeOldestPattern() {
        var linkedHashMap = new LinkedHashMap<String, Integer>(16, 0.75f, true) {
            @Override
            protected boolean removeEldestEntry(Map.Entry<String, Integer> eldest) {
                return size() > 5; // Keep only 5 most recent entries
            }
        };
        linkedHashMap.put("Car", 4);
        linkedHashMap.put("Truck", 6);
        linkedHashMap.put("Bike", 2);
        linkedHashMap.put("Airplane", 1);
        linkedHashMap.put("Boat", 3);

        System.out.println(" ");
        System.out.println("Initial Map Entries:");
        displayMap(linkedHashMap);

        System.out.println("Adding {Horse=5} - Car is expected to be removed");
        System.out.println("Displaying Entries after addition:");
        linkedHashMap.put("Horse", 5);
        displayMap(linkedHashMap);
    }

    /**
     * Adds the Entry in the first position, if removeEldest override is declared it might be automatically deleted..
     */
    public void putFirst() {
        var linkedHashMap = new LinkedHashMap<String, Integer>(16, 0.75f, true);

        linkedHashMap.put("Second", 2);
        System.out.println(" ");
        System.out.println("Initial Map Entries:");
        displayMap(linkedHashMap);

        linkedHashMap.put("Third", 3);
        System.out.println("Adding Entry normally:");
        displayMap(linkedHashMap);

        linkedHashMap.put("Fourth", 4);
        linkedHashMap.put("Fifth", 5);
        linkedHashMap.put("Sixth", 6);
        System.out.println("Adding 3 Entries normally:");
        displayMap(linkedHashMap);

        System.out.println("Adding Entry in First position:");
        linkedHashMap.putFirst("First", 1);
        displayMap(linkedHashMap);
    }

    /**
     * Method reversed() provides a view of the reversed values of the map as a SequencedMap (view {{@code SequencedMaps}} )
     * In JAVA 21 LinkedHashMap implemented this Interface gaining the features of SequencedMap.
     * The difference is that Linked still can override methods while Sequenced can't so it is useful for cache LRU by using
     * override of removeEldestEntry() or the accessOrder
     */
    public void reversed() {
        var linkedHashMap = new LinkedHashMap<String, Integer>(16, 0.75f, true);
        linkedHashMap.put("Car", 1);
        linkedHashMap.put("Truck", 9);
        linkedHashMap.put("Bike", 2);
        var reversed = linkedHashMap.reversed();

        System.out.println(" ");
        System.out.println("Initial Map Entries:");
        displayMap(linkedHashMap);

        System.out.println("Reversed Map Entries:");
        displayMap(reversed);
    }

    private <K, V> void displayMap(Map<K, V> map) {
        System.out.println("Displaying Map Entries");
        map.forEach((k,v) -> {
            System.out.println("k: " + k + " | v: " + v);
        });
    }
}
