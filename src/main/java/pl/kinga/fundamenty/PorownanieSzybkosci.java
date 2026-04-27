package pl.kinga.fundamenty;

public class PorownanieSzybkosci {
    public static void main(String[] args) {
        int powtorzenia = 50000;

        // Sposob 1: String +
        long start1 = System.currentTimeMillis();
        String tekst = "";
        for (int i = 0; i < powtorzenia; i++) {
            tekst = tekst + "a";
        }
        long czas1 = System.currentTimeMillis() - start1;

        // Sposob 2: StringBuilder
        long start2 = System.currentTimeMillis();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < powtorzenia; i++) {
            sb.append("a");
        }
        String tekst2 = sb.toString();
        long czas2 = System.currentTimeMillis() - start2;

        System.out.println("String +:       " + czas1 + " ms"); // tu trwa proces 71ms
        System.out.println("StringBuilder:  " + czas2 + " ms"); // tu proces trwa 1 ms
    }
}
