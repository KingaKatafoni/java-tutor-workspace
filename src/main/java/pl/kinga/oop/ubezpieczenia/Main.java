package pl.kinga.oop.ubezpieczenia;

import java.math.BigDecimal;

public class Main {
    public static void main(String[] args){
        Polisa[] polisy = {
                new Polisa("Po-124", "Karina Ciesielczyk", new BigDecimal("2000")),
                new PolisaNaZycie("Zy-456", "Maurycy Motyka", new BigDecimal("1000"), 70),
                new PolisaSamochodowa("Sa-378", "Renata Marzec", new BigDecimal("650"), 2020)
        };

        for (Polisa polisa : polisy){
            System.out.println(polisa.info(true));
            //System.out.print(polisa.obliczSkladke());
        }

        System.out.println("Konstruktor z kwota: " + new Polisa("Po-908", "Kinga Binga", new BigDecimal("1200")).info(true));
        System.out.println("Konstruktor bez kwoty: " + new Polisa("Po-765", "Jan Klacz").info(true));


        //Zadanie 2
        // Przypadek A -> Overriding (liczba parametrow sie nie zmienia, sygnatura ta sama)
        // Przypadek B-> Overloading parametry sie zmieniaja i typ zwracany
        // Przypadek C-> Overloading liczba parametrów sie
        // Przypadek D-> Overriding
        // Przypadek E-> Overloading
    }
}
