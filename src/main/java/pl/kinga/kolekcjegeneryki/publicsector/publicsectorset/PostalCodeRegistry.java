package pl.kinga.kolekcjegeneryki.publicsector.publicsectorset;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.TreeSet;

public class PostalCodeRegistry {
    private Set<String> hashCodes;
    private Set<String> linkedCodes;
    private Set<String> treeCodes;

    public PostalCodeRegistry() {
        this.hashCodes = new HashSet<>();
        this.linkedCodes = new LinkedHashSet<>();
        this.treeCodes = new TreeSet<>();
    }

    public void addCode(String code) {
        hashCodes.add(code);
        linkedCodes.add(code);
        treeCodes.add(code);
    }

    public void printAllSets() {
        System.out.println("HashSet: " + hashCodes + " - brak gwarancji kolejnosci");
        System.out.println("LinkedHashSet: " + linkedCodes + " - kolejnosc dodawania");
        System.out.println("TreeSet: " + treeCodes + " - posortowane");
    }

    public boolean containsCode(String code) {
        return hashCodes.contains(code); // O(1) najszybsze dla HashSet
    }

    public int getCodeCount() {
        return linkedCodes.size();
    }
}
