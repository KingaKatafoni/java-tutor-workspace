package pl.kinga.testowanie.lekcja7_3;

import java.util.*;

public class DocumentRegistry {
    private final List<Document> documents = new ArrayList<>();

    public record Document(String id, String title, String category, int year) {
    }

    public Document addDocument(String id, String title, String category, int year) {
        if (id == null || id.isEmpty()) {
            throw new IllegalArgumentException("Document ID cannot be null or empty");
        }

        for (Document doc : documents) {
            if (doc.id.equals(id)) {
                throw new IllegalArgumentException("Document already exists: " + id);
            }
        }

        Document documentAdded = new Document(id, title, category, year);
        documents.add(documentAdded);
        return documentAdded;
    }

    public List<Document> findByCategory(String category) {
        return documents.stream()
                .filter(doc -> doc.category.equals(category))
                .sorted(Comparator.comparing(Document::year).reversed())
                .toList();
    }

    public List<Document> findByYearRange(int fromYear, int toYear) {
        if (fromYear > toYear) {
            throw new IllegalArgumentException("Invalid year range");
        }

        return documents.stream()
                .filter(doc -> (doc.year >= fromYear) && (doc.year <= toYear))
                .toList();
    }

    public Map<String, Integer> getStatistics() {
        if (documents.isEmpty()) {
            throw new IllegalStateException("No documents");
        }

        Integer totalDocuments = documents.size();
        long categoriesCount = documents.stream()
                .map(Document::category)
                .distinct()
                .count();


        Integer oldestYear = documents.stream()
                .min(Comparator.comparing(Document::year))
                .orElseThrow(IllegalStateException::new)
                .year();


        int newestYear = documents.stream()
                .max(Comparator.comparing(doc -> doc.year))
                .orElseThrow(IllegalStateException::new)
                .year();

        Map<String, Integer> statistics = new HashMap<>();
        statistics.put("totalDocuments", totalDocuments);
        statistics.put("categories", (int) categoriesCount);
        statistics.put("oldestYear", oldestYear);
        statistics.put("newestYear", newestYear);

        return statistics;
    }


}
