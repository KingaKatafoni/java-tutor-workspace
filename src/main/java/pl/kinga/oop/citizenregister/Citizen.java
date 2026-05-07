package pl.kinga.oop.citizenregister;

public class Citizen {
    private String name;
    private String pesel;
    private String city;

    public Citizen(String name, String pesel, String city){
        this.name = name;
        this.pesel = pesel;
        this.city = city;
    }

    public String getName(){
        return name;
    }

    public String getPesel(){
        return pesel;
    }

    public String getCity(){
        return city;
    }

    @Override
    public String toString() {
        return "Citizen{" +
                "name='" + name + '\'' +
                ", pesel='" + pesel + '\'' +
                ", city='" + city + '\'' +
                '}';
    }
}
