package org.example.collections.practice.exercises;

import java.util.EnumMap;

import static org.example.collections.practice.exercises.P006.TrafficLight.*;

/**
 * Problem Statement:
 *
 * You are tasked with implementing a simple traffic light controller using an EnumMap.
 * Define a TrafficLight enum with the states: RED, GREEN, and YELLOW.
 * Each state must define what the next state is in the traffic light cycle:
 * RED → GREEN
 * GREEN → YELLOW
 * YELLOW → RED
 * Implement a method getNextLight(TrafficLight current) that returns the next light.
 * Simulate 10 transitions and print each one.
 */
public class P006 implements Exercise {
    private final int K = 10;
    private TrafficLight currentLight = RED;
    private static final EnumMap<TrafficLight, TrafficLight> trafficLightMap = new EnumMap<>(TrafficLight.class);
    static {
        trafficLightMap.put(RED, GREEN);
        trafficLightMap.put(GREEN, YELLOW);
        trafficLightMap.put(YELLOW, RED);
    }

    @Override
    public void resolveExercise() {
        // Solution here:
        for (int i = 0; i < K; i++) {
            var nextLight = getNextLight(currentLight);
            System.out.println("Current Light : " + currentLight + " -> Next Light: " + nextLight);
            currentLight = nextLight;
        }
    }

    private TrafficLight getNextLight(TrafficLight current) {
        return trafficLightMap.get(current);
    }

    enum TrafficLight {
        RED,
        GREEN,
        YELLOW;
    }

    // Option of ENUM driven logic
//    enum TrafficLight {
//        RED {
//            public TrafficLight next() { return GREEN; }
//        },
//        GREEN {
//            public TrafficLight next() { return YELLOW; }
//        },
//        YELLOW {
//            public TrafficLight next() { return RED; }
//        };
//
//        public abstract TrafficLight next();
//    }
}
