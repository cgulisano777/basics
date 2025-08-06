package org.example.collections.maps;

public class TestMapsAPI {

    public static void hashMaps() {
        System.out.println("---------------------------------------");
        System.out.println(" * * * HashMap * * *");
        var hashMaps = new HashMaps();
        hashMaps.waysToCopy();

        hashMaps.playingWithOverride();

        hashMaps.parallelUpdates();
    }

    public static void concurrentHashMaps() {
        System.out.println("---------------------------------------");
        System.out.println(" * * * ConcurrentHashMap * * *");
        var concurrentHashMaps = new ConcurrentHashMaps();
        concurrentHashMaps.waysToCopy();

        concurrentHashMaps.parallelUpdates();
    }

    public static void treeMaps() {
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
        System.out.println(" * * * ConcurrentSkipListMap * * *");
        var concurrentSkipListMaps = new ConcurrentSkipListMaps();
        concurrentSkipListMaps.waysToCopy();
        concurrentSkipListMaps.parallelUpdates();
        concurrentSkipListMaps.descendingMap();
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

    public static void identityHashMap() {
        System.out.println("---------------------------------------");
        System.out.println(" * * * IdentityHashMap * * *");
        var identityHashMaps = new IdentityHashMaps();
        identityHashMaps.vsHashMap();
    }

    public static void enumMap() {
        System.out.println("---------------------------------------");
        System.out.println(" * * * EnumMap * * *");
        var enumMaps = new EnumMaps();
        enumMaps.example1();
        enumMaps.example2();
    }
}
