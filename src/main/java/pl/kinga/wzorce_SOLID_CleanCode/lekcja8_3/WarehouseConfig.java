package pl.kinga.wzorce_SOLID_CleanCode.lekcja8_3;

public enum WarehouseConfig {
    INSTANCE;

    private String warehouseName = "Central Warehouse";
    private int maxCapacity = 10000;
    private String defaultCountry = "PL";

    public String getWarehouseName() {
        return warehouseName;
    }

    public int getMaxCapacity() {
        return maxCapacity;
    }

    public String getDefaultCountry() {
        return defaultCountry;
    }

    public void setMaxCapacity(int maxCapacity) {
        if (maxCapacity <= 0) {
            throw new IllegalArgumentException("Capacity must be positive");
        }

        this.maxCapacity = maxCapacity;
    }

    public void reset() {
        this.warehouseName = "Central Warehouse";
        this.maxCapacity = 10000;
        this.defaultCountry = "PL";
    }
}
