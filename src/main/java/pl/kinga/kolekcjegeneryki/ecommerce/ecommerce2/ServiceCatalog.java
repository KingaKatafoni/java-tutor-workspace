package pl.kinga.kolekcjegeneryki.ecommerce.ecommerce2;

import java.util.Map;

public class ServiceCatalog {
    public static void main (String [] args){
        Map<String, String> services = Map.of(
                "DEL-STD", "Standard delivery 3-5 days",
                "DEL-EXP", "Express delivery next day",
                "DEL-SAM", "Same day delivery",
                "RET-FREE", "Free return within 30 days",
                "GIFT-WRAP", "Gift wrapping service"
        );

        for (Map.Entry<String, String> entry : services.entrySet()){
            System.out.println(entry.getKey() + " -> " + entry.getValue());
        }
        System.out.println();
        System.out.println("------Delivery services--------");
        for (String entryKey : services.keySet()) {
            if (entryKey.startsWith("DEL")){
                System.out.println(entryKey);
            }
        }

        for (String service : services.values()){
            if (service.toLowerCase().contains("free")){
                System.out.println(service);
            }
        }

    }
}
