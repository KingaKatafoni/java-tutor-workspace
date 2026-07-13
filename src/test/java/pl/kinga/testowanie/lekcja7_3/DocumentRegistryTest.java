package pl.kinga.testowanie.lekcja7_3;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class DocumentRegistryTest {
    DocumentRegistry registry = new DocumentRegistry();

    //addDocument()
    @Test
    void shouldReturnAddedDocumentWhenDataIsCorrect() {
        DocumentRegistry.Document document1 = registry.addDocument("DOC-1", "Wedding agreement", "civil", 1999);
        DocumentRegistry.Document document2 = registry.addDocument("DOC-2", "Budget 2024", "FINANCE", 2024);
        DocumentRegistry.Document document3 = registry.addDocument("DOC-3", "Budget 2022", "FINANCE", 2022);
        DocumentRegistry.Document document4 = registry.addDocument("DOC-4", "Agreements 2020", "CIVIL", 2020);
        DocumentRegistry.Document document5 = registry.addDocument("DOC-5", "Agreements 2023", "CIVIL", 2023);


        assertAll(
                () -> assertEquals("DOC-1", document1.id()),
                () -> assertEquals("Wedding agreement", document1.title()),
                () -> assertEquals("civil", document1.category()),
                () -> assertEquals(1999, document1.year()),
                () -> assertEquals("DOC-2", document2.id()),
                () -> assertEquals("DOC-3", document3.id()),
                () -> assertEquals("DOC-4", document4.id()),
                () -> assertEquals("DOC-5", document5.id())
        );
    }

    @Test
    void shouldReturnAddedDocumentWhenCategoryExists() {
        registry.addDocument("DOC-1", "Wedding agreement", "civil", 1999);
        registry.addDocument("DOC-2", "Budget 2024", "FINANCE", 2024);
        registry.addDocument("DOC-3", "Budget 2022", "FINANCE", 2022);
        registry.addDocument("DOC-4", "Agreements 2020", "CIVIL", 2020);
        registry.addDocument("DOC-5", "Agreements 2023", "CIVIL", 2023);

        assertEquals(2, registry.findByCategory("FINANCE").size());
    }

    @Test
    void shouldThrowIllegalArgumentWhenIdNull() {
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                () -> registry.addDocument(null, "Wedding agreement", "civil", 1999));
        assertEquals("Document ID cannot be null or empty", ex.getMessage());
    }

    @Test
    void shouldThrowIllegalArgumentWhenIdIsEmpty() {
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                () -> registry.addDocument("", "Wedding agreement", "civil", 1999));

        assertEquals("Document ID cannot be null or empty", ex.getMessage());
    }

    @Test
    void shouldThrowIllegalArgumentWhenIdDuplicated() {

        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> {
            registry.addDocument("DOC-1", "Wedding agreement", "civil", 1999);
            registry.addDocument("DOC-1", "Wedding agreement", "civil", 1999);
        });

        assertEquals("Document already exists: DOC-1", ex.getMessage());
    }

    //findByCategory()

    @Test
    void shouldReturnDocumentsSortedByYearDescWhenCategoryExists() {
        registry.addDocument("DOC-1", "Budget", "FINANCE", 2020);
        registry.addDocument("DOC-2", "Report", "FINANCE", 2022);
        registry.addDocument("DOC-3", "Audit", "FINANCE", 2024);
        registry.addDocument("DOC-4", "Policy", "HR", 2023);
        List<DocumentRegistry.Document> results = registry.findByCategory("FINANCE");

        assertAll(
                () -> assertEquals(3, results.size()),
                () -> assertEquals(2024, results.get(0).year()),
                () -> assertEquals(2022, results.get(1).year()),
                () -> assertEquals(2020, results.get(2).year())
        );
    }

    @Test
    void shouldReturnOneDocumentWhenCategoryExists() {

        registry.addDocument("DOC-1", "Budget 2020", "CIVIL", 2020);
        registry.addDocument("DOC-2", "Budget 2024", "FINANCE", 2024);
        registry.addDocument("DOC-3", "Budget 2022", "FINANCE", 2022);
        registry.addDocument("DOC-4", "Agreements 2020", "CIVIL", 2020);
        registry.addDocument("DOC-5", "Agreements 2023", "HR", 2023);

        List<DocumentRegistry.Document> results = registry.findByCategory("HR");

        assertEquals(1, results.size());
    }

    @Test
    void shouldReturnThreeDocumentWhenCategoryCivil() {

        registry.addDocument("DOC-1", "Budget 2020", "CIVIL", 2020);
        registry.addDocument("DOC-2", "Budget 2024", "FINANCE", 2024);
        registry.addDocument("DOC-3", "Budget 2022", "FINANCE", 2022);
        registry.addDocument("DOC-4", "Agreements 2020", "CIVIL", 2020);
        registry.addDocument("DOC-5", "Agreements 2023", "CIVIL", 2023);

        List<DocumentRegistry.Document> results = registry.findByCategory("CIVIL");

        assertEquals(3, results.size());
    }

    @Test
    void shouldReturnTwoDocumentsWhenCategoryFinance() {

        registry.addDocument("DOC-1", "Budget 2020", "CIVIL", 2020);
        registry.addDocument("DOC-2", "Budget 2024", "FINANCE", 2024);
        registry.addDocument("DOC-3", "Budget 2022", "HR", 2022);
        registry.addDocument("DOC-4", "Agreements 2020", "CIVIL", 2020);
        registry.addDocument("DOC-5", "Agreements 2023", "HR", 2023);

        List<DocumentRegistry.Document> results = registry.findByCategory("HR");

        assertEquals(2, results.size());
    }

    @Test
    void shouldReturnIsEmptyWhenCategoryDoesntExist() {

        registry.addDocument("DOC-1", "Budget", "FINANCE", 2020);
        registry.addDocument("DOC-2", "Report", "FINANCE", 2022);
        registry.addDocument("DOC-3", "Audit", "FINANCE", 2024);
        registry.addDocument("DOC-4", "Policy", "HR", 2023);
        assertTrue(registry.findByCategory("").isEmpty());
    }

    @Test
    void shouldReturnSortedListWhenCategoryExists() {

        registry.addDocument("DOC-1", "Budget", "FINANCE", 2020);
        registry.addDocument("DOC-2", "Report", "FINANCE", 2022);
        registry.addDocument("DOC-3", "Audit", "FINANCE", 2024);
        registry.addDocument("DOC-4", "Policy", "HR", 2023);

        List<DocumentRegistry.Document> documents = registry.findByCategory("FINANCE");

        assertEquals(2024, documents.getFirst().year());
    }

    //findByYearRange()
    @Test
    void shouldReturnDocumentsWhenRange20202024() {

        registry.addDocument("DOC-1", "Budget", "FINANCE", 2020);
        registry.addDocument("DOC-2", "Report", "FINANCE", 2022);
        registry.addDocument("DOC-3", "Audit", "FINANCE", 2024);
        registry.addDocument("DOC-4", "Policy", "HR", 2023);
        registry.addDocument("DOC-5", "Agreements 2023", "CIVIL", 2023);

        List<DocumentRegistry.Document> byYearRange = registry.findByYearRange(2020, 2024);

        assertAll(
                () -> assertEquals(5, byYearRange.size()),
                () -> assertEquals("DOC-1", byYearRange.get(0).id()),
                () -> assertEquals("DOC-2", byYearRange.get(1).id())
        );
    }

    @Test
    void shouldReturnDocumentsWhenRangeExactlyYear() {

        registry.addDocument("DOC-1", "Budget", "FINANCE", 2020);
        registry.addDocument("DOC-2", "Report", "FINANCE", 2022);
        registry.addDocument("DOC-3", "Audit", "FINANCE", 2024);
        registry.addDocument("DOC-4", "Policy", "HR", 2023);
        registry.addDocument("DOC-5", "Agreements 2023", "CIVIL", 2023);

        List<DocumentRegistry.Document> documents = registry.findByYearRange(2023, 2023);
        assertEquals(2, documents.size());
        assertEquals(2023, documents.get(0).year());
    }

    @Test
    void shouldReturnDocumentsWhenRangeIsBottomLimit() {

        registry.addDocument("DOC-1", "Budget", "FINANCE", 2020);
        registry.addDocument("DOC-2", "Report", "FINANCE", 2022);
        registry.addDocument("DOC-3", "Audit", "FINANCE", 2024);
        registry.addDocument("DOC-4", "Policy", "HR", 2023);
        registry.addDocument("DOC-5", "Agreements 2023", "CIVIL", 2020);

        List<DocumentRegistry.Document> documents = registry.findByYearRange(2020, 2020);
        assertEquals(2, documents.size());
        assertEquals(2020, documents.get(0).year());
    }

    @Test
    void shouldReturnDocumentsWhenRangeIsUpperLimit() {

        registry.addDocument("DOC-1", "Budget", "FINANCE", 2020);
        registry.addDocument("DOC-2", "Report", "FINANCE", 2022);
        registry.addDocument("DOC-3", "Audit", "FINANCE", 2024);
        registry.addDocument("DOC-4", "Policy", "HR", 2023);
        registry.addDocument("DOC-5", "Agreements 2023", "CIVIL", 2020);

        List<DocumentRegistry.Document> documents = registry.findByYearRange(2024, 2024);
        assertEquals(1, documents.size());
        assertEquals(2024, documents.get(0).year());
    }

    @Test
    void shouldReturnDocumentsWhenRangeDoesntExist() {

        registry.addDocument("DOC-1", "Budget", "FINANCE", 2020);
        registry.addDocument("DOC-2", "Report", "FINANCE", 2022);
        registry.addDocument("DOC-3", "Audit", "FINANCE", 2024);
        registry.addDocument("DOC-4", "Policy", "HR", 2023);
        registry.addDocument("DOC-5", "Agreements 2023", "CIVIL", 2023);

        List<DocumentRegistry.Document> documents = registry.findByYearRange(2015, 2017);
        assertEquals(0, documents.size());
    }

    @Test
    void shouldThrowIllegalArgumentWhenRangeIsIncorrect() {

        registry.addDocument("DOC-1", "Budget", "FINANCE", 2020);
        registry.addDocument("DOC-2", "Report", "FINANCE", 2022);
        registry.addDocument("DOC-3", "Audit", "FINANCE", 2024);
        registry.addDocument("DOC-4", "Policy", "HR", 2023);
        registry.addDocument("DOC-5", "Agreements 2023", "CIVIL", 2023);

        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                () -> registry.findByYearRange(2024, 2020));

        assertEquals("Invalid year range", ex.getMessage());
    }

    // statistics
    @Test
    void shouldReturnStatisticsWhenDocumentsExist() {
        registry.addDocument("DOC-1", "Budget", "FINANCE", 2020);
        registry.addDocument("DOC-2", "Report", "FINANCE", 2022);
        registry.addDocument("DOC-3", "Policy", "HR", 2021);
        registry.addDocument("DOC-4", "Audit", "LEGAL", 2024);
        registry.addDocument("DOC-5", "Review", "HR", 2019);

        Map<String, Integer> statistics = registry.getStatistics();

        assertAll(
                () -> assertEquals(5, statistics.get("totalDocuments")),
                () -> assertEquals(3, statistics.get("categories")),
                () -> assertEquals(2019, statistics.get("oldestYear")),
                () -> assertEquals(2024, statistics.get("newestYear"))
        );
    }

    @Test
    void shouldThrowIllegalStateWhenListIsEmpty() {
        IllegalStateException ex = assertThrows(IllegalStateException.class,
                () -> registry.getStatistics()
        );

        assertEquals("No documents", ex.getMessage());
    }
}
