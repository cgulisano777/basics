package org.example.collections.practice;

import org.example.collections.practice.exercises.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class PracticeAPI {
    // Configuration Map
    private static final Map<String, Exercise> allExercises = new HashMap<>();
    static {
        allExercises.put(Exercises.P001.name(), new P001());
        allExercises.put(Exercises.P002.name(), new P002());
        allExercises.put(Exercises.P003.name(), new P003());
        allExercises.put(Exercises.P004.name(), new P004());
        allExercises.put(Exercises.P005.name(), new P005());
        allExercises.put(Exercises.P006.name(), new P006());
        allExercises.put(Exercises.P007.name(), new P007());
        allExercises.put(Exercises.P008.name(), new P008());
        allExercises.put(Exercises.P009.name(), new P009());
        allExercises.put(Exercises.P010.name(), new P010());
        allExercises.put(Exercises.P011.name(), new P011());
        allExercises.put(Exercises.P012.name(), new P012());
        allExercises.put(Exercises.P013.name(), new P013());
        allExercises.put(Exercises.P014.name(), new P014());
    }

    public void displayExercises() {
        Arrays.stream(Exercises.values()).forEach(exe -> {
            System.out.println("Exercise: " + exe.name() + " | Description: " + exe.description + "\n\n");
        });
    }

    public void execute(Exercises exercise) {
        System.out.println("--------------------------------------------------------------------------------");
        System.out.println("Executing : " + exercise.name() + "!\n");
        var exe = allExercises.get(exercise.name());
        exe.resolveExercise();
    }
}
