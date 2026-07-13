package pl.kinga.testowanie.lekcja7_4;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LibraryServiceTest {

    LibraryService libraryService;

    @BeforeEach
    void setUp() {

        libraryService = new LibraryService();
        libraryService.addBook("ISBN-001", "W pustyni i w puszczy", "Henryk Sienkiewicz");
        libraryService.addBook("ISBN-002", "Lalka", "Bolesław Prus");
        libraryService.addBook("ISBN-003", "Diuna", "Frank Herbert");
        libraryService.addBook("ISBN-004", "Proces", "Frank Kafka");
    }

    //addBook
    @Test
    void shouldReturnBookWhenIsCorrectlyAdded() {

        LibraryService.Book book = libraryService.addBook("ISBN-005", "Hobbit", "Tolkien");

        assertAll(
                () -> assertEquals("ISBN-005", book.isbn()),
                () -> assertEquals("Hobbit", book.title()),
                () -> assertEquals("Tolkien", book.author()),
                () -> assertTrue(book.available())
        );
    }

    @Test
    void shouldThrowIllegalArgumentWhenIsbnIsNull() {

        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                () -> libraryService.addBook(null, "Ameba", "Romuald Lipko"));
        assertEquals("ISBN cannot be null or empty", ex.getMessage());
    }

    @Test
    void shouldThrowIllegalArgumentWhenIsbnIsEmpty() {
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                () -> libraryService.addBook("", "Kombucha", "Lamir Samir")
        );

        assertEquals("ISBN cannot be null or empty", ex.getMessage());
    }

    @Test
    void shouldThrowIllegalArgumentWhenAddDuplicate() {
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                () -> libraryService.addBook("ISBN-001", "W pustyni i w puszczy", "Henryk Sienkiewicz")
        );

        assertEquals("Book already exists: ISBN-001", ex.getMessage());
    }

    //borrwBook

    @Test
    void shouldBorrowBookWhenAvailable() {
        String borrowedBook = libraryService.borrowBook("ISBN-002", "Kamil Wisniewski");
        assertEquals("Borrowed: Lalka by Kamil Wisniewski", borrowedBook);
    }

    @Test
    void shouldNotFindBookWhenBorrowed() {
        libraryService.borrowBook("ISBN-001", "Jan Kowalski");

        List<LibraryService.Book> availableBooks = libraryService.findAvailableBooks();

        assertAll(
                () -> assertEquals(3, availableBooks.size()),
                () -> assertTrue(availableBooks.stream().noneMatch(book -> book.isbn().equals("ISBN-001")))
        );
    }

    @Test
    void shouldThrowIllegalStateWhenBorrowUnavailable() {
        libraryService.borrowBook("ISBN-001", "Karolina Polko");


        IllegalStateException ex = assertThrows(IllegalStateException.class,
                () -> libraryService.borrowBook("ISBN-001", "Adam Malysz"));

        assertEquals("Book is already borrowed: ISBN-001", ex.getMessage());
    }

    @Test
    void shouldThrowIllegalArgumentWhenIsbnDoesntExist() {
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                () -> libraryService.borrowBook("ISBN-005", "Natalia Oreiro")
        );

        assertEquals("Book not found: ISBN-005", ex.getMessage());
    }

    //returnBook

    @Test
    void shouldReturnInfoWhenReturnBookCorrectly() {
        libraryService.borrowBook("ISBN-003", "Marian Pazdzioch");
        String info = libraryService.returnBook("ISBN-003");

        assertEquals("Returned: Diuna", info);
    }

    @Test
    void shouldReturnAvailableWhenBookReturned() {
        libraryService.borrowBook("ISBN-002", "Piotr Polk");

        List<LibraryService.Book> availableBooks = libraryService.findAvailableBooks();
        libraryService.returnBook("ISBN-002");

        List<LibraryService.Book> availableBooks1 = libraryService.findAvailableBooks();

        assertEquals(3, availableBooks.size());
        assertEquals(4, availableBooks1.size());
    }

    @Test
    void shouldThrowIllegalStateWhenReturnUnborrowed() {

        IllegalStateException ex = assertThrows(IllegalStateException.class,
                () -> libraryService.returnBook("ISBN-003"));
        assertEquals("Book is not borrowed: ISBN-003", ex.getMessage());
    }

    @Test
    void shouldThrowIllegalArgumentWhenReturnIsbnDoesntExist() {

        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                () -> libraryService.returnBook("ISBN-006"));
        assertEquals("Book not found: ISBN-006", ex.getMessage());
    }

    //findAvailableBooks

    @Test
    void shouldReturnFourWhenAllAvailable() {
        int size = libraryService.findAvailableBooks().size();
        assertEquals(4, size);
    }

    @Test
    void shouldReturnTwoWhenTwoBooksBorrowed() {
        libraryService.borrowBook("ISBN-001", "Xavier Li");
        libraryService.borrowBook("ISBN-002", "Xavier Li");

        int size = libraryService.findAvailableBooks().size();
        assertEquals(2, size);
    }

    @Test
    void shouldReturnAlphabeticallySortedListWhenAllAvailable() {
        List<LibraryService.Book> availableBooks = libraryService.findAvailableBooks();

        assertAll(
                () -> assertEquals("Diuna", availableBooks.get(0).title()),
                () -> assertEquals("Lalka", availableBooks.get(1).title()),
                () -> assertEquals("Proces", availableBooks.get(2).title()),
                () -> assertEquals("W pustyni i w puszczy", availableBooks.get(3).title())
        );
    }

    //getBorrowHistory
    @Test
    void shouldReturnThreeNamesWhenThreeBooksBorrowed() {
        libraryService.borrowBook("ISBN-001", "Adam Malysz");
        libraryService.returnBook("ISBN-001");
        libraryService.borrowBook("ISBN-001", "Monika Dobra");
        libraryService.returnBook("ISBN-001");
        libraryService.borrowBook("ISBN-001", "Lukasz Szukasz");

        List<String> borrowHistory = libraryService.getBorrowHistory("ISBN-001");

        assertEquals(3, borrowHistory.size());
    }

    @Test
    void shouldReturnEmptyListWhenNeverBorrowed() {
        List<String> borrowHistory = libraryService.getBorrowHistory("ISBN-001");
        assertEquals(0, borrowHistory.size());
    }

    @Test
    void shouldThrowIllegalArgumentWhenHistoryIsbnDoesntExist() {
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                () -> libraryService.getBorrowHistory("ISBN-006")
        );

        assertEquals("Book not found: ISBN-006", ex.getMessage());
    }
}