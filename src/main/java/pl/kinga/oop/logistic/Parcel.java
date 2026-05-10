package pl.kinga.oop.logistic;

public class Parcel {
    private String trackingId;
    private double weightKg;
    private String destination;
    private boolean delivered;

    public Parcel(String trackingId, double weightKg, String destination){
        this.trackingId = trackingId;
        this.weightKg = weightKg;
        this.destination = destination;
        this.delivered =false;
    }

    public String getTrackingId(){
        return trackingId;
    }

    public double getWeightKg(){
        return weightKg;
    }

    public String getDestination(){
        return destination;
    }

    public boolean isDelivered(){
        return delivered;
    }

    public void markAsDelivered(){
        delivered = true;
    }

    @Override
    public String toString() {
        return "Parcel{" +
                "trackingId='" + trackingId + '\'' +
                String.format(", weightKg=%.1f kg", weightKg) +
                ", destination='" + destination + '\'' +
                ", delivered=" + delivered +
                '}';
    }
}
