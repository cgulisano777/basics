package org.example.collections.practice.exercises;

import java.util.Arrays;

/**
 * Problem Statement:
 * Given an array of Strings, create a method that receives a String (min 3 chars) as argument
 * to filter those items that start with the received string.
 */
public class P007 implements Exercise {
    private final String[] menuList = {"Actions", "Reports", "Dashboards" ,"Tasks" ,"Repositories" ,"Incidents" ,"TaskManager", "Reparations"};
    private final String inputWord = "repo"; // "Repo" ,  "tio"
    @Override
    public void resolveExercise() {
        // Solution here:
        System.out.println("Menu List :");
        display(menuList);

        var result = filterStartWith(inputWord);
        System.out.println("\nResult of StartWith for input: \"" + inputWord + "\"");
        display(result);

        var result2 = filterContains(inputWord);
        System.out.println("\nResult of Contains for input: \"" + inputWord + "\"");
        display(result2);
    }

    private String[] filterStartWith(String input) {
        // Filter only if the input value is at least 3 characters long
        if (input.length() < 3) {
            return menuList;
        }

        return Arrays.stream(menuList)
                .filter(item ->
                        item.toLowerCase().startsWith(input.toLowerCase()))
                .toArray(String[]::new);
    }

    private String[] filterContains(String input) {
        // Filter only if the input value is at least 3 characters long
        if (input.length() < 3) {
            return menuList;
        }

        return Arrays.stream(menuList)
                .filter(item ->
                        item.toLowerCase().contains(input.toLowerCase()))
                .toArray(String[]::new);
    }

    private <V> void display(V[] input) {
        Arrays.stream(input).forEach(System.out::println);
    }
}
