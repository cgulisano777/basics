package org.example.collections.practice.exercises;

import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.function.Predicate;

/**
 * Problem Statement:
 * Given a Tree of Maps containing nodes to the next Map o null when no more branches.
 * create a method that receives a String (min 3 chars) as argument
 * to filter those items that start with the received string.
 */
public class P009 implements Exercise {

    //    private final String inputWord = "Act";
//    private final String inputWord = "Rep";
    private final String inputWord = "Dai";
    //    private final String inputWord = "ate";

    @Override
    public void resolveExercise() {
        // Initialization
        TreeMap<String, TreeMap<String, ?>> treeMenu = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);

        treeMenu.put("Actions", new TreeMap<>(Map.of(
                "Create", new TreeMap<>(),
                "Update", new TreeMap<>(),
                "Delete", new TreeMap<>(Map.of(
                        "All DataBase", new TreeMap<>(),
                        "Records", new TreeMap<>()
                ))
        )));

        treeMenu.put("Reports", new TreeMap<>(Map.of(
                "Daily", new TreeMap<>(),
                "Monthly", new TreeMap<>(),
                "Create", new TreeMap<>(Map.of(
                        "New Report", new TreeMap<>(Map.of(
                                "Daily", new TreeMap<>(),
                                "Weekly", new TreeMap<>(),
                                "Monthly", new TreeMap<>()
                        )),
                        "Dashboard", new TreeMap<>(),
                        "Notifications", new TreeMap<>()
                ))
        )));
//        TreeMap<String, TreeMap<String, ?>> treeMenu = new TreeMap<>(Map.of(
//                "Actions", new TreeMap<>(Map.of(
//                        "Create", new TreeMap<>(),
//                        "Update", new TreeMap<>(),
//                        "Delete", new TreeMap<>(Map.of(
//                                "All DataBase", new TreeMap<>(),
//                                "Records", new TreeMap<>()
//                        ))
//                )),
//                "Reports", new TreeMap<>(Map.of(
//                        "Daily", new TreeMap<>(),
//                        "Monthly", new TreeMap<>(),
//                        "Create", new TreeMap<>(Map.of(
//                                "New Report", new TreeMap<>(Map.of(
//                                        "Daily", new TreeMap<>(),
//                                        "Weekly", new TreeMap<>(),
//                                        "Monthly", new TreeMap<>()
//                                )),
//                                "Dashboard", new TreeMap<>(),
//                                "Notifications", new TreeMap<>()
//                        ))
//                ))
//        ));

        displayTreeMenu(treeMenu, 0);


        // Solution here:
        System.out.println("\nResult:\n");
        var result = filterByStartWith(treeMenu, key -> key.toLowerCase().startsWith(inputWord.toLowerCase()));
        displayTreeMenu(result, 0);

//        System.out.println("\nResult for optimized:\n");
//        var result2 = filterByPrefix(treeMenu, inputWord);
//        displayTreeMenu(result2, 0);
//
//        System.out.println("\nResult for optimized 2:\n");
//        var result3 = filterByPrefixOptimized(treeMenu, inputWord);
//        displayTreeMenu(result3, 0);
    }

    private TreeMap<String, TreeMap<String, ?>> filterByStartWith(TreeMap<String, TreeMap<String, ?>> treeMap, Predicate<String> predicate) {
        TreeMap<String, TreeMap<String, ?>> result = new TreeMap<>();

        for (var entry : treeMap.entrySet()) {
            String key = entry.getKey();
            TreeMap<String, TreeMap<String, ?>> child = (TreeMap<String, TreeMap<String, ?>>) entry.getValue();

            var filteredChild = filterByStartWith(child, predicate);

            if (predicate.test(key) || !filteredChild.isEmpty()) {
                result.put(key, filteredChild);
            }
        }

        return result;
    }

//    /**
//     * Alternative to use a combination of Recursive + Ceiling + Submap to optimize the filtering
//     * it does not work too well...
//     */
//    private TreeMap<String, TreeMap<String, ?>> filterByPrefix(
//            TreeMap<String, TreeMap<String, ?>> treeMap,
//            String prefixInput
//    ) {
//        var prefix = prefixInput.toLowerCase();
//        TreeMap<String, TreeMap<String, ?>> filteredMap = new TreeMap<>();
//
//        String endPrefix = prefix + Character.MAX_VALUE;
//        SortedMap<String, TreeMap<String, ?>> subMap = treeMap.subMap(prefix, endPrefix);
//
//        for (Map.Entry<String, TreeMap<String, ?>> entry : subMap.entrySet()) {
//            String key = entry.getKey();
//            TreeMap<String, TreeMap<String, ?>> children = (TreeMap<String, TreeMap<String, ?>>) entry.getValue();
//
//            // Recursively filter children
//            TreeMap<String, TreeMap<String, ?>> filteredChildren = filterByPrefix(children, prefix);
//
//            // Only add if the key matches or has matching children
//            if (key.toLowerCase().startsWith(prefix) || !filteredChildren.isEmpty()) {
//                filteredMap.put(key, filteredChildren);
//            }
//        }
//
//        return filteredMap;
//    }
//
//
//    private TreeMap<String, TreeMap<String, ?>> filterByPrefixOptimized(
//            TreeMap<String, TreeMap<String, ?>> treeMap,
//            String prefixInput
//    ) {
//        TreeMap<String, TreeMap<String, ?>> result = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
//
//        // Tail map from prefix (inclusive)
//        for (var entry : treeMap.tailMap(prefixInput, true).entrySet()) {
//            String key = entry.getKey();
//
//            if (!key.toLowerCase().startsWith(prefixInput.toLowerCase())) {
//                break; // stop once prefix no longer matches (because it's sorted)
//            }
//
//            TreeMap<String, TreeMap<String, ?>> child = (TreeMap<String, TreeMap<String, ?>>) entry.getValue();
//            TreeMap<String, TreeMap<String, ?>> filteredChild = filterByPrefixOptimized(child, prefixInput);
//
//            result.put(key, filteredChild);
//        }
//
//        return result;
//    }




    // todo this is not good...

//    private <K, V> TreeMap<K, V> filterByStartWith(TreeMap<K, V> treeMap) {
//
////        var newMap = new TreeMap<K, V>();
//
//        // Filter Children
//        var newMap = treeMap.entrySet().stream()
//                .filter(child -> child.getKey().toString().startsWith(inputWord))
//                .collect(Collectors.toMap(
//                        Map.Entry::getKey,
//                        Map.Entry::getValue,
//                        (v1, v2) -> v1,
//                        TreeMap::new
//                ));
//
//
//
//        return new TreeMap<K, V>();
//    }

    private void displayTreeMenu(Map<String, ?> treeMenu, int level) {
        var spaces = "  ".repeat(level);
        var levelBar = level > 0
                ? spaces + "| "
                : "| ";

        if (level == 0) System.out.println("Menu");

        treeMenu.forEach((k, v) -> {
            System.out.print(levelBar);
            System.out.println(k);
            displayTreeMenu((Map<String, ?>) v, level + 1);
        });
    }
}
