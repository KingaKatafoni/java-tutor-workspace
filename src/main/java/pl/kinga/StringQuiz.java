package pl.kinga;

public class StringQuiz {
    public static void main(String[] args) {
        String a = "Urzad Miasta";
        String b = "Urzad Miasta";
        String c = new String("Urzad Miasta");
        String d = c.intern();

        System.out.println("1: " + (a == b));           // true
        System.out.println("2: " + (a == c));           // false
        System.out.println("3: " + (a.equals(c)));      // true
        System.out.println("4: " + (a == d));           // true

        String e = "Urzad" + " " + "Miasta";
        System.out.println("5: " + (a == e));           // true

        String prefix = "Urzad";
        String f = prefix + " Miasta";
        System.out.println("6: " + (a == f));           // false
    }
}
