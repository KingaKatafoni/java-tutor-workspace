package pl.kinga.kolekcjegeneryki.publicsector.publicsectoriterator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DocumentArchive implements Iterable<Document> {
    private List<Document> documents;

    public DocumentArchive() {
        this.documents = new ArrayList<>();
    }

    public void addDocument(Document doc) {
        documents.add(doc);
    }

    public int getDocumentCount() {
        return documents.size();
    }

    @Override
    public Iterator<Document> iterator() {
        return documents.iterator();
    }
}
