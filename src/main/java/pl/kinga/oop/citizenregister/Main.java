package pl.kinga.oop.citizenregister;

public class Main {
    public static void main(String[] args){
        System.out.println("----Task 1----");
        Citizen citizenOne = new Citizen("Anna Kowalska", "82010112345", "Warszawa");
        Citizen citizenTwo = new Citizen("Kamil Szczesniak", "93010216745", "Poznan");
        Citizen citizenThree = new Citizen("Monika Kubelek", "89020118765", "Krakow");

        System.out.println(citizenOne);
        System.out.println(citizenTwo);
        System.out.println(citizenThree);

        System.out.println(citizenOne.getClass().getSimpleName());

        Object citizenObject = citizenThree;

        if (citizenObject instanceof Citizen c){
            System.out.println(c.getName() + " is type of " + c.getClass().getSimpleName());
        }

        //2# e-commerce
        System.out.println("----Task 2----");
        Product productOne = new Product("SKU-2345", "Laptop Dell");
        Product productTwo = new Product("SKU-2345", "Laptop Dell");

        System.out.println(productOne == productTwo);
        System.out.println(productOne.equals(productTwo));
        System.out.println(productOne.hashCode());
        System.out.println(productTwo.hashCode());

        // > Dlaczego `equals()` zwraca `false`,
        // chociaz oba produkty maja te same dane?
        // Co trzeba zrobic, zeby to zmienić?

        // equals() zwraca false ponieważ sprawdzana jest referencja a nie wartosc obiektu, a mamy do czynienia z dwoma osobnymi obiektami o roznych referencjach.
        // Zeby otrzymac true nalezaloby
        // 1 -> przypisac do zmiennej productTwo zmienna productOne.
        // 2 -> porownywac obiekty po dodaniu toString: productOne.toString().equals(productTwo.toString())
    }


}
