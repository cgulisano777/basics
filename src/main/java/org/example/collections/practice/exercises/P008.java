package org.example.collections.practice.exercises;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Problem Statement:
 * Given a Tree of Nodes containing nodes to the next Node o null when no more branches.
 * create a method that receives a String (min 3 chars) as argument
 * to filter those items that start with the received string.
 */
public class P008 implements Exercise {

    private final Node menuTree = new Node("Menu", new ArrayList<>());
//    private final String inputWord = "Act";
    private final String inputWord = "dai";
//    private final String inputWord = "ate";

    @Override
    public void resolveExercise() {
        // Initialization
        var row1 = new Node("Actions",
                List.of(new Node("Create", new ArrayList<>()),
                        new Node("Update", new ArrayList<>()),
                        new Node("Delete", List.of(
                                new Node("AllDatabase", new ArrayList<>()),
                                new Node("Records", new ArrayList<>())))
                ));
        var row2 = new Node("Reports",
                List.of(new Node("Daily", new ArrayList<>()),
                        new Node("Monthly", new ArrayList<>()),
                        new Node("Create", List.of(
                                new Node("New Report",
                                        List.of(new Node("Daily", new ArrayList<>()),
                                                new Node("Weekly", new ArrayList<>()),
                                                new Node("Monthly", new ArrayList<>()))),
                                new Node("Dashboard", new ArrayList<>()),
                                new Node("Notification", new ArrayList<>())))
                ));


        menuTree.nextNode.addAll(List.of(row1, row2));
        displayNodeTree(menuTree, 0);

        // Solution here:
        var result = filterByStartWith(menuTree, inputWord);
        if (null == result) {
            result = new Node("Menu", new ArrayList<>());
        }
        System.out.println("\nResult StartWith:");
        displayNodeTree(result, 0);

        result = filterByContains(menuTree, inputWord);
        if (null == result) {
            result = new Node("Menu", new ArrayList<>());
        }
        System.out.println("\nResult Contains:");
        displayNodeTree(result, 0);
    }

    private Node filterByStartWith(Node node, String inputWord) {

        // Filter recursively the children first
        var filteredChildren = node.nextNode.stream()
                .map(child -> filterByStartWith(child, inputWord))
                .filter(Objects::nonNull)
                .toList();

        // If this node matches or has any children, include it
        if (node.name().toLowerCase().startsWith(inputWord.toLowerCase()) || !filteredChildren.isEmpty()) {
            return new Node(node.name(), filteredChildren);
        }

        // Otherwise, exclude it by returning null
        return null;


//        var nextNode = nodeTree.nextNode;
//
//        if (hasMoreNodes(nextNode)) {
//            nextNode.forEach(node -> filterByStartWith(node, inputWord));
//        } else {
//            var removeList = nextNode.stream().filter(node -> !node.name.startsWith(inputWord)).toList();
//            nodeTree.nextNode.removeAll(removeList);
//        }
    }

    private Node filterByContains(Node node, String inputWord) {

        // Filter recursively the children first
        var filteredChildren = node.nextNode.stream()
                .map(child -> filterByContains(child, inputWord))
                .filter(Objects::nonNull)
                .toList();

        // If this node matches or has any children, include it
        if (node.name().toLowerCase().contains(inputWord.toLowerCase()) || !filteredChildren.isEmpty()) {
            return new Node(node.name(), filteredChildren);
        }

        // Otherwise, exclude it by returning null
        return null;
    }

//    private boolean hasMoreNodes(List<Node> nodeList) {
//        if (nodeList.isEmpty()) return false;
//
//        // find non-empty nodes
//        var hasNodes = nodeList.stream().filter(node -> hasMoreNodes(node.nextNode)).toList();
//        return !hasNodes.isEmpty();
//    }

    private void displayNodeTree(Node nodeTree, int level) {
        var spaces = "  ".repeat(level);
        System.out.println(nodeTree.name);
        var nextNodes = nodeTree.nextNode;
        if (!nextNodes.isEmpty()) {
            nextNodes.forEach(node -> {
                System.out.print(spaces + "| ");
                displayNodeTree(node, level + 1);
            });
        }
    }


    record Node(String name, List<Node> nextNode) {}
}
