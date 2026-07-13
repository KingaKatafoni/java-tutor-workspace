package pl.kinga.testowanie.lekcja7_4;

import java.util.*;

public class LibraryService {
    private final Map<String, Book> books = new HashMap<>();
    private final Map<String, List<String>> borrowHistory = new HashMap<>();

    public record Book(String isbn, String title, String author, boolean available) {
    }

    public Book addBook(String isbn, String title, String author) {
        if (isbn == null || isbn.isEmpty()) {
            throw new IllegalArgumentException("ISBN cannot be null or empty");
        }

        if (books.containsKey(isbn)) {
            throw new IllegalArgumentException("Book already exists: " + isbn);
        }

        Book addedBook = new Book(isbn, title, author, true);
        books.put(isbn, addedBook);

        return addedBook;
    }

    public String borrowBook(String isbn, String borrowerName) {
        if (!books.containsKey(isbn)) {
            throw new IllegalArgumentException("Book not found: " + isbn);
        }
        Book book = books.get(isbn);

        if (!book.available) {
            throw new IllegalStateException("Book is already borrowed: " + isbn);
        }

        Book bookBorrowed = new Book(isbn, book.title(), book.author(), false);

        books.put(isbn, bookBorrowed);
        borrowHistory.computeIfAbsent(isbn, k ->
                        new ArrayList<>())
                .add(borrowerName);
        return "Borrowed: " + books.get(isbn).title() + " by " + borrowerName;
    }

    public String returnBook(String isbn) {

        if (!books.containsKey(isbn)) {
            throw new IllegalArgumentException("Book not found: " + isbn);
        }
        Book book = books.get(isbn);
        if (book.available) {
            throw new IllegalStateException("Book is not borrowed: " + isbn);
        }

        Book returnedBook = new Book(isbn, book.title, book.author, true);

        books.put(isbn, returnedBook);

        return "Returned: " + book.title;
    }

    public List<Book> findAvailableBooks() {
        return books.values().stream()
                .filter(book -> book.available)
                .sorted(Comparator.comparing(Book::title))
                .toList();
    }

    public List<String> getBorrowHistory(String isbn) {
        if (!books.containsKey(isbn)) {
            throw new IllegalArgumentException("Book not found: " + isbn);
        }

        if (!borrowHistory.containsKey(isbn)) {
            return new ArrayList<>();
        }
        return borrowHistory.get(isbn);
    }

}
