package org.example.collections.practice;

import org.example.collections.practice.exercises.*;

import java.util.Arrays;
import java.util.Map;

public class PracticeAPI {
    // Configuration Map
    private final Map<String, Exercise> allExercises =
            Map.of(
                    Exercises.P001.name(), new P001(),
                    Exercises.P002.name(), new P002(),
                    Exercises.P003.name(), new P003(),
                    Exercises.P004.name(), new P004(),
                    Exercises.P005.name(), new P005(),
                    Exercises.P006.name(), new P006(),
                    Exercises.P007.name(), new P007(),
                    Exercises.P008.name(), new P008(),
                    Exercises.P009.name(), new P009(),
                    Exercises.P010.name(), new P010()
            );

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
