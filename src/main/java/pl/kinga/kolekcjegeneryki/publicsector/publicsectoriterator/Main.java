package pl.kinga.kolekcjegeneryki.publicsector.publicsectoriterator;

import java.util.Iterator;

public class Main {
    public static void main(String[] args) {
        DocumentArchive documentArchive = new DocumentArchive();

        documentArchive.addDocument(new Document("DOC/069695/2020", "Constitution", 2020));
        documentArchive.addDocument(new Document("DOC/239695/2023", "Registry", 2023));
        documentArchive.addDocument(new Document("DOC/439695/2021", "Obligatory taxes", 2021));
        documentArchive.addDocument(new Document("DOC/879654/2023", "Constitution", 2023));
        documentArchive.addDocument(new Document("DOC/329695/2022", "Recipe", 2025));

        for (Document dok : documentArchive){
            System.out.println(dok);
        }

        Iterator<Document> it = documentArchive.iterator();
        while (it.hasNext()) {
            Document dok = it.next();
            if (dok.year() < 2023){
                it.remove();
            }
        }
        System.out.println("---removed---");
        for (Document dok : documentArchive){
            System.out.println(dok);
        }
    }
}
