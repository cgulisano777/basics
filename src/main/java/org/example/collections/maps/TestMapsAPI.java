package org.example.collections.maps;

public class TestMapsAPI {

    public static void hashMaps() throws InterruptedException {
        System.out.println("---------------------------------------");
        System.out.println(" * * * HashMap * * *");
        var hashMaps = new HashMaps();
        hashMaps.waysToCopy();

        hashMaps.playingWithOverride();

        hashMaps.parallelUpdates();
    }

    public static void concurrentHashMaps() throws InterruptedException {
        System.out.println("---------------------------------------");
        System.out.println(" * * * ConcurrentHashMap * * *");
        var concurrentHashMaps = new ConcurrentHashMaps();
        concurrentHashMaps.waysToCopy();

        concurrentHashMaps.parallelUpdates();
    }

    public static void treeMaps() throws InterruptedException {
        System.out.println("---------------------------------------");
        System.out.println(" * * * TreeMap * * *");
        var treeMaps = new TreeMaps();
        treeMaps.waysToCopy();

        treeMaps.ceilingEntry();
        treeMaps.ceilingKey();

        treeMaps.headMap();
        treeMaps.tailMap();

        treeMaps.higherKey();

        treeMaps.comparator();

        treeMaps.descendingKeySet();

        treeMaps.parallelUpdates();
    }

    public static void concurrentSkipListMap() {
        System.out.println("---------------------------------------");
        System.out.println(" * * * ConcurrentHashMap * * *");
        var concurrentSkipListMaps = new ConcurrentSkipListMaps();
        concurrentSkipListMaps.waysToCopy();
    }

    public static void linkedHashMap() {
        System.out.println("---------------------------------------");
        System.out.println(" * * * LinkedHashMap * * *");
        var linkedHashMaps = new LinkedHashMaps();
        linkedHashMaps.waysToCopy();

        linkedHashMaps.accessOrder();

        linkedHashMaps.removeOldestPattern();

        linkedHashMaps.putFirst();

        linkedHashMaps.reversed();
    }

    public static void sequencedMap() {
        System.out.println("---------------------------------------");
        System.out.println(" * * * SequencedMap * * *");
        var sequencedMaps = new SequencedMaps();

        sequencedMaps.testingSequencedMapInstantiatingLinkedHashMapWithOverride();
    }

    public static void weakMap() {
        System.out.println("---------------------------------------");
        System.out.println(" * * * WeakHashMap * * *");
        var weakHashMaps = new WeakHashMaps();

        weakHashMaps.garbageCollectorRemovesEntry();
    }
}
