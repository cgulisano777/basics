package org.example.collections.practice.exercises;

import java.util.HashMap;
import java.util.IdentityHashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Problem Statement:
 *
 * You are given a list of Person objects (name + age). Your task is to:
 * Add these objects to an IdentityHashMap and a regular HashMap.
 * Use the same logical values (same name and age) but different instances.
 * Count how many entries are stored in each map.
 * Print the contents of both maps and explain why the sizes differ.
 */
public class P005 implements Exercise {
    @Override
    public void resolveExercise() {
        // Solution here:
        var hashMap = new HashMap<Person, String>();
        var identityMap = new IdentityHashMap<Person, String>();

        var person1 = new Person("Rocco", 18);
        var person2 = new Person("Rocco", 18);
        var person3 = new Person("Rita", 2);
        var person4 = new Person("Rita", 2);

        hashMap.put(person1, "bark bark");
        hashMap.put(person2, "baaaaaark bark!");
        hashMap.put(person3, "baaaaaark bark!");
        hashMap.put(person4, "baaaaaark bark!");

        identityMap.put(person1, "bark bark");
        identityMap.put(person2, "baaaaaark bark!");
        identityMap.put(person3, "baaaaaark bark!");
        identityMap.put(person4, "baaaaaark bark!");

        System.out.println("\nDisplaying HashMap:");
        displayMap(hashMap);
        System.out.println("\nDisplaying IdentityHashMap:");
        displayMap(identityMap);
    }

    private <K, V> void displayMap(Map<K, V> map) {
        System.out.println("Displaying Map Entries");
        map.forEach((k,v) -> System.out.println("k: " + k + " | v: " + v));
    }


    // Defining Object as record, as a subclass to practicing purposes
    // TODO: I had to turn record into class to test this since java generates the equals and hashCode automatically for records
    // the idea is to see how Map uses equal and hashCode to compare entries, with the code commented it is comparing
    // instances so it can have several objects that look similar, while uncommented it is now doing a == validating
    // the objects are the same by theirs attributes.
    // In the other hand IdentityHashMap always compares using == that why it still have 4 entries no matter the overrides
    static class Person {
        String name;
        int age;

        public Person(String name, int age) {
            this.name = name;
            this.age = age;
        }

        // Uncomment this to test HashMap with equals/hashCode
//        @Override
//        public boolean equals(Object o) {
//            if (this == o) return true;
//            if (o == null || getClass() != o.getClass()) return false;
//            Person person = (Person) o;
//            return age == person.age && Objects.equals(name, person.name);
//        }
//
//        @Override
//        public int hashCode() {
//            return Objects.hash(name, age);
//        }

        @Override
        public String toString() {
            return name + " (" + age + ")";
        }
    }
}
