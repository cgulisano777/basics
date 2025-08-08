package org.example.collections.practice.exercises;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import static org.example.collections.practice.exercises.P012.BookCategory.*;

/**
 * Problem Statement:
 * Create a Library application to manage books by Type, Stock, Availability
 */
public class P012 implements Exercise {
    private final BookFactory bookFactory = new BookFactory();
    private final HashMap<String, Library> libraryMap = new HashMap<>();

    // Test Library
    private final Library testLibrary =
            new Library("TestLib", new TreeMap<>(Map.of(
                    "A-001", new AdultBook("La Pasion"),
                    "A-002", new AdultBook("La Pasion"),
                    "A-003", new AdultBook("Como cocinar gente"),
                    "C-001", new ChildrenBook("El osito Bobo")
            )));

    @Override
    public void resolveExercise() {
        // Initialization
        libraryMap.put(testLibrary.name, testLibrary);

        // Solution here:
        displayAllLibraries();

        createBook(testLibrary.name, "La Pasion", ADULTS);
        displayAllLibraries();

        System.out.println("\nRenting a book:");
        rentBook("La Pasion", ADULTS, "TestLib");
        displayAllLibraries();

        System.out.println("\nRenting a another book:");
        rentBook("La Pasion", ADULTS, "TestLib");
        displayAllLibraries();

        System.out.println("\nRenting a another book:");
        rentBook("La Pasion", ADULTS, "TestLib");
        displayAllLibraries();

        System.out.println("\nRenting a book that has not availability:");
        rentBook("La Pasion", ADULTS, "TestLib");
        displayAllLibraries();
    }


    public void rentBook(String bookName, BookCategory bookCategory, String libraryName) {
        // Find the book and see if there is available
        var library = libraryMap.get(libraryName);
        if (null == library) {
            // todo exception
            System.out.println("Library does not exist!: " + libraryName);
        }
        var availableBook = lookForBookByNameAndCategory(bookName, bookCategory, library);

        // todo exception or error handling
        if (availableBook == null) {
            System.out.println("The book is not available!! " + bookName);
            return;
        }

        // Set it as rented
        var bookToRent = library.books().get(availableBook);
        bookToRent.setRented(true);
        library.books().replace(availableBook, bookToRent);
        libraryMap.replace(libraryName, library);
    }

    public void createBook(String libraryName, String name, BookCategory bookCategory) {
        // Make sure library exists
        var library = libraryMap.get(libraryName);
        if (null == library) {
            // todo exception
            System.out.println("Library does not exist!: " + libraryName);
        }

        // Get latest book (if any) of the same and add sequenced id
//        var latestBook = lookForBookByNameAndCategory(name, bookCategory, library);
        var latestBookId = lookForBookCategory(bookCategory, library);

        var newBook = bookFactory.getNewBook(name, bookCategory);

//        var newBookIdSeqNm = Integer.parseInt(latestBook.getKey().split("-")[1]) + 1;
        var newBookIdSeqNm = Integer.parseInt(latestBookId.split("-")[1]) + 1;

        library.books().put(String.format(bookCategory.prefix + "-%03d", newBookIdSeqNm), newBook);

        libraryMap.replace(libraryName, library);
    }

    private String lookForBookCategory(BookCategory bookCategory, Library library) {
        var sortedMap = new TreeMap<String, Book>();

        var filteredBook = library.books().entrySet().stream()
                .filter(bookEntry ->
                        bookEntry.getValue().getCategory().equals(bookCategory))
                .toList();

        filteredBook.forEach(book -> sortedMap.put(book.getKey(), book.getValue()));

        return sortedMap.lastEntry().getKey();
    }


    // this can be reused for stock counting
    private String lookForBookByNameAndCategory(String name, BookCategory bookCategory, Library library) {
        var sortedMap = new TreeMap<String, Book>();

        // Get all the books matching name and cat, that are available
        var filteredBook = library.books().entrySet().stream()
                .filter(bookEntry ->
                        bookEntry.getValue().getName().equals(name)
                                && bookEntry.getValue().getCategory().equals(bookCategory)
                                && !bookEntry.getValue().isRented()
                )
                .toList();

        if (filteredBook.isEmpty()) {
            return null;
        }

        filteredBook.forEach(book -> sortedMap.put(book.getKey(), book.getValue()));

        return sortedMap.firstEntry().getKey();
    }


    private void displayAllLibraries() {
        libraryMap.forEach((libraryName, library) -> {
            System.out.println("Library Stock: " + libraryName);

            library.books().forEach((bookId, book) ->
                    System.out.println("BookId: " + bookId +
                            " | Book:  " + book.getName() +
                    " | Category: " + book.getCategory() +
                    " | Rented: " + book.isRented()));
        });
    }




    // Library with books by id
    record Library(String name, TreeMap<String, Book> books) {}


    abstract static class Book {
        private final String name;
        private final BookCategory category;
        private boolean rented;

        public Book(String name, BookCategory bookCategory) {
            this.name = name;
            this.category = bookCategory;
        }

        public String getName() {
            return name;
        }

        public BookCategory getCategory() {
            return category;
        }

        public boolean isRented() {
            return rented;
        }
        public void setRented(boolean rented) {
            this.rented = rented;
        }
    }

    static class AdultBook extends Book {
        public AdultBook(String name) {
            super(name, ADULTS);
        }
    }

    static class ChildrenBook extends Book {
        public ChildrenBook(String name) {
            super(name, CHILDREN);
        }
    }

    static class EncyclopediaBook extends Book {
        public EncyclopediaBook(String name) {
            super(name, ENCYCLOPEDIA);
        }
    }

    static class LanguageBook extends Book {
        public LanguageBook(String name) {
            super(name, LANGUAGE);
        }
    }

    enum BookCategory {
        ADULTS('A'),
        CHILDREN('C'),
        ENCYCLOPEDIA('E'),
        LANGUAGE('L');

        final char prefix;

        BookCategory(char prefix) {
            this.prefix = prefix;
        }
    }

    static class BookFactory {
        public Book getNewBook(String name, BookCategory bookCategory) {
            return switch (bookCategory) {
                case ADULTS -> new AdultBook(name);
                case CHILDREN -> new ChildrenBook(name);
                case ENCYCLOPEDIA ->  new EncyclopediaBook(name);
                case LANGUAGE ->  new LanguageBook(name);
            };
        }
    }
}
