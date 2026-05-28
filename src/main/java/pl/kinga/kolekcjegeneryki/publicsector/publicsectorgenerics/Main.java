package pl.kinga.kolekcjegeneryki.publicsector.publicsectorgenerics;

public class Main {
    public static void main(String[] args) {
        Registry<Citizen> citizenRegistry = new Registry<>();
        citizenRegistry.add(new Citizen("93467829910", "Jan Kowalski", 1980));
        citizenRegistry.add(new Citizen("89765432135", "Milena Borsuk", 1990));
        citizenRegistry.add(new Citizen("96782003826", "Adam Barski", 1956));

        System.out.println("------Citizen Registry-------------");
        citizenRegistry.printAll();
        System.out.println(citizenRegistry.findById("93467829910"));
        citizenRegistry.removeById("96782003826");
        System.out.println("----CitizenRegistry after remove-------");
        citizenRegistry.printAll();

        Registry<Vehicle> vehicleRegistry = new Registry<>();
        vehicleRegistry.add(new Vehicle("BMW637288", "BMW", 2024));
        vehicleRegistry.add(new Vehicle("MER637278", "Mercedes", 2000));
        System.out.println("------Vehicle findById()-----------");
        System.out.println(vehicleRegistry.findById("BMW637288"));
        System.out.println("------Vehicle Registry");
        vehicleRegistry.printAll();

        System.out.println("One Registry class, two different types!");
    }
}
