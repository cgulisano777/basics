package org.example;

import org.example.collections.maps.TestMapsAPI;
import org.example.collections.practice.PracticeAPI;


import static org.example.collections.practice.exercises.Exercises.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("Initializing BasicApp");

//        testDifferentMaps();
        executeExercises();
    }

    /**
     * Test different Maps
     */
    private static void testDifferentMaps() {
//        TestMapsAPI.hashMaps();
//        TestMapsAPI.concurrentHashMaps();
//        TestMapsAPI.treeMaps();
//        TestMapsAPI.linkedHashMap();
//        TestMapsAPI.sequencedMap();
//        TestMapsAPI.weakMap();
//        TestMapsAPI.concurrentSkipListMap();
//        TestMapsAPI.identityHashMap();
//        TestMapsAPI.enumMap();
    }

    /**
     * Execute the required Exercises
     */
    private static void executeExercises() {
        // Exercises
        var practiceApi = new PracticeAPI();
        practiceApi.displayExercises();
//        practiceApi.execute(P001);
//        practiceApi.execute(P002);
//        practiceApi.execute(P003);
//        practiceApi.execute(P004);
//        practiceApi.execute(P005);
//        practiceApi.execute(P006);
//        practiceApi.execute(P007);
//        practiceApi.execute(P008);
//        practiceApi.execute(P009);
//        practiceApi.execute(P010); // todo pending!
//        practiceApi.execute(P011);
//        practiceApi.execute(P012);
    }
}