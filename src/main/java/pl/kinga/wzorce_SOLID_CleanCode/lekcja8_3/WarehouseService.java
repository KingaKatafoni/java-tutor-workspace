package pl.kinga.wzorce_SOLID_CleanCode.lekcja8_3;

public class WarehouseService {
    private final WarehouseConfig warehouseConfig;

    public WarehouseService(WarehouseConfig warehouseConfig){
        this.warehouseConfig = warehouseConfig;
    }

    public String acceptPackage(String packageId, int weight){
        if(weight <= 0){
            throw new IllegalArgumentException("Weight must be positive");
        }

        if(warehouseConfig.getMaxCapacity() <= 0){
            throw new IllegalStateException("Warehouse is full");
        }

        return "Package " + packageId + " accepted at " + warehouseConfig.getWarehouseName() + ", country: " + warehouseConfig.getDefaultCountry();
    }

    public String getWarehouseInfo(){
        return warehouseConfig.getWarehouseName() + " (capacity: " + warehouseConfig.getMaxCapacity() + ", country: " + warehouseConfig.getDefaultCountry()+ ")";
    }
}
