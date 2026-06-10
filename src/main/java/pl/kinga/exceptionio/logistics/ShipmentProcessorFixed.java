package pl.kinga.exceptionio.logistics;

public class ShipmentProcessorFixed {
    //Method 1 -> fixed Generic name + empty catch
    public void processShipment(String trackingNumber) {
        try {
            if (trackingNumber == null) {
                throw new RuntimeException("Tracking number is " + trackingNumber);
            }
            System.out.println("Shipment processed: " + trackingNumber);
        } catch (RuntimeException e) {
            System.out.println("Shipment processing failed: " + e.getMessage());
        }
    }

    //Method 2 -> catch to wide, Log or throw (print removed)
    public double calculateShippingCost(String weightStr) {
        try {
            double weight = Double.parseDouble(weightStr);
            return weight * 5.5;
        } catch (NumberFormatException e) {
            throw e;
        }
    }

    //Method 3 -> try catch instead of if validation
    public boolean isValidTrackingNumber(String tracking) {
        if (tracking.length() != 10) {
            return false;
        }
        return true;
    }

    //Method 4 -> generic error message
    public void markAsDelivered(String trackingNumber, String date) {
        if (trackingNumber == null) {
            throw new IllegalArgumentException("Tracking number cannot be null");
        }
        if (date == null) {
            throw new IllegalArgumentException("Date cannot be null");
        }
        System.out.println("Shipment " + trackingNumber + " delivered on " + date);
    }
}
