package pl.kinga.oop.logistic2;

import java.util.List;

public record Waybill(String waybillNumber, String sender, String receiver, List<String> parcels) {

    public Waybill{
        if(waybillNumber == null || waybillNumber.isEmpty() || sender == null || sender.isEmpty() || receiver == null || receiver.isEmpty() || parcels == null || parcels.isEmpty()){
            throw new IllegalArgumentException("The input cannot be null or empty");
        }
        parcels = List.copyOf(parcels);
    }

    @Override
    public List<String> parcels(){
        return List.copyOf(parcels);
    }

    public int getParcelCount(){
        return parcels().size();
    }

    public boolean hasParcel(String parcelId){
        return parcels().contains(parcelId);
    }
}
