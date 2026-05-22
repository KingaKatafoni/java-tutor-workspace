package pl.kinga.kolekcjegeneryki.publicsector.publicsectorset;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        PostalCodeRegistry postalCodeRegistry = new PostalCodeRegistry();
        for (String code : List.of("62-510", "00-001", "61-250", "30-001", "80-100", "62-510", "44-200", "10-001")) {
            postalCodeRegistry.addCode(code);
        }

        postalCodeRegistry.printAllSets();

        System.out.println(postalCodeRegistry.containsCode("62-510"));
        System.out.println(postalCodeRegistry.getCodeCount());

        // do tego problemu wybralabym HashSet poniewaz nie potrzebuje do przechowywyania kodow pocztowych, ani zachowanej kolejnosci, ani elementow posortowanych
    }
}
