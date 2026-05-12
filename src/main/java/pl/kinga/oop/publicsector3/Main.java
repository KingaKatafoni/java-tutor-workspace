package pl.kinga.oop.publicsector3;

public class Main {
    public static void main(String[] args) {
        DocumentType[] all = DocumentType.values();
        for (DocumentType documentType : all) {
            System.out.println(documentType.toString());
        }

        System.out.println("Is " + DocumentType.ID_CARD.name() + " free? " + DocumentType.ID_CARD.isFree());
        System.out.println("Is " + DocumentType.PASSPORT.name() + " free? " + DocumentType.PASSPORT.isFree());
        System.out.println("Is " + DocumentType.DRIVING_LICENSE.name() + " free? " + DocumentType.DRIVING_LICENSE.isFree());

        System.out.println("valueOf() " + DocumentType.valueOf("PASSPORT"));

        DocumentType dok = DocumentType.PASSPORT;
        switch (dok) {
            case ID_CARD -> System.out.println("Prosze udac sie do urzedu gminy");
            case PASSPORT -> System.out.println("Prosze udac sie do urzedu wojewodzkiego");
            case DRIVING_LICENSE -> System.out.println("Prosze udac sie do wydzialu komunikacji");
        }
    }
}
