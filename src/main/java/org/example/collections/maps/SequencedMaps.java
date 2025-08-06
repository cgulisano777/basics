package org.example.collections.maps;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.SequencedMap;

public class SequencedMaps {
    /**
     * ✅ Best practice (if clarity matters):
     * If your primary goal is to use removeEldestEntry() (for LRU-like caching), keep your reference type as LinkedHashMap.
     *
     * If you're writing general-purpose code and want to leverage sequencing features (like reverse views), use SequencedMap.
     */
    public void testingSequencedMapInstantiatingLinkedHashMapWithOverride() {
        SequencedMap<String, Integer> sequencedMap = new LinkedHashMap<>(16, 0.75f, true) {
            @Override
            protected boolean removeEldestEntry(Map.Entry<String, Integer> eldest) {
                return size() > 5; // Keep only 5 most recent entries
            }
        };

        sequencedMap.put("B", 2);
        sequencedMap.put("A", 1);
        sequencedMap.put("C", 3);
        sequencedMap.put("E", 4);
        sequencedMap.put("Z", 6);
        System.out.println(" ");
        System.out.println("Initial Map Entries:");
        displayMap(sequencedMap);

        sequencedMap.put("F", 5);
        System.out.println(" ");
        System.out.println("Adding 1 more entry to the Map Entries:");
        displayMap(sequencedMap);

        sequencedMap.get("C");
        System.out.println(" ");
        System.out.println("Accessing \"C\" more entry to the Map Entries:");
        displayMap(sequencedMap);
    }

    private <K, V> void displayMap(Map<K, V> map) {
        System.out.println("Displaying Map Entries");
        map.forEach((k,v) -> {
            System.out.println("k: " + k + " | v: " + v);
        });
    }
}
