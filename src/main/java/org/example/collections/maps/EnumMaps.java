package org.example.collections.maps;

import java.util.EnumMap;
import java.util.List;

/**
 * Specialized for enum keys
 *
 * EnumMap<K extends Enum<K>, V> is a specialized Map implementation designed
 * specifically for use with keys of a single enum type.
 *
 * | Feature            | EnumMap                                                                     |
 * | ------------------ | --------------------------------------------------------------------------- |
 * | Key type           | Must be an `enum`                                                           |
 * | Performance        | Faster than `HashMap`                                                       |
 * | Memory usage       | Much lower than `HashMap`                                                   |
 * | Ordering           | Maintains the **natural order** of enum constants (i.e., declaration order) |
 * | Null keys allowed? | ❌ No                                                                       |
 * | Null values?       | ✅ Yes                                                                      |
 *
 *             VS HashMap
 * | Feature              | `EnumMap`                     | `HashMap`                                       |
 * | -------------------- | ----------------------------- | ----------------------------------------------- |
 * | Key type restriction | Only works with enums         | Works with any object                           |
 * | Ordering             | Enum constant order           | Unordered (or insertion order in LinkedHashMap) |
 * | Memory efficiency    | Very efficient (array-backed) | Less efficient (hash table + buckets)           |
 * | Performance          | Faster for enums              | General purpose                                 |
 */
public class EnumMaps {

    public void example1() {
        enum Day {
            MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY
        }

        EnumMap<Day, String> schedule = new EnumMap<>(Day.class);

        schedule.put(Day.MONDAY, "Gym");
        schedule.put(Day.FRIDAY, "Party");
        schedule.put(Day.WEDNESDAY, "Work from home");

        System.out.println("\nExample 1 :");
        schedule.forEach((day, activity) ->
                System.out.println(day + " → " + activity));
    }

    public void example2() {
        System.out.println("\nExample 2 :");
        enum Status {
            SUCCESS, FAILURE, TIMEOUT
        }

        List<Status> responses = List.of(
                Status.SUCCESS, Status.FAILURE, Status.SUCCESS, Status.TIMEOUT, Status.FAILURE
        );

        EnumMap<Status, Integer> countMap = new EnumMap<>(Status.class);
        for (Status s : Status.values()) {
            countMap.put(s, 0);
        }

        for (Status s : responses) {
            countMap.put(s, countMap.get(s) + 1);
        }

        System.out.println(countMap);  // {SUCCESS=2, FAILURE=2, TIMEOUT=1}
    }
}
