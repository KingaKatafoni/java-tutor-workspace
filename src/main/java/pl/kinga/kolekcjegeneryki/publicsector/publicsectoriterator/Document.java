package pl.kinga.kolekcjegeneryki.publicsector.publicsectoriterator;

public record Document(String id, String title, int year) {

    public Document {
        if (id == null || id.isEmpty() ||
                title == null || title.isEmpty() ||
                year < 1900){
            throw new IllegalArgumentException("Input value is incorrect!");
        }
    }
}
