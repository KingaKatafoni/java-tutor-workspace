package pl.kinga.exceptionio.logistics;

import pl.kinga.kolekcjegeneryki.publicsector.publicsectoriterator.Document;

public class ShipmentProcessor {
    //Method 1
    public void processShipment(String trackingNumber){
        try {
            if(trackingNumber == null){
                throw new RuntimeException("Error");
            }
            System.out.println("Shipment processed: " + trackingNumber);
        } catch (Exception e){
           //ignore
        }
    }

    //Method 2
    public double calculateShippingCost(String weightStr) {
        try {
            double weight = Double.parseDouble(weightStr);
            return weight * 5.5;
        } catch (Exception e){
            System.out.println("Error");
            throw e;
        }
    }

    //Method 3
    public boolean isValidTrackingNumber(String tracking){
        try {
            if(tracking.length() != 10) {
                throw new RuntimeException("bad length");
            }
            return true;
        } catch (RuntimeException e) {
            return false;
        }
    }

    //Method 4
    public void markAsDelivered(String trackingNumber, String date){
        if (trackingNumber == null) {
            throw new IllegalArgumentException("Invalid input");
        }

        if (date == null) {
            throw new IllegalArgumentException("Invalid input");
        }

        System.out.println("Shipment " + trackingNumber + " delivered on " + date);
    }
}
