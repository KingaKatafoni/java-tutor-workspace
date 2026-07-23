package pl.kinga.wzorce_SOLID_CleanCode.lekcja8_3;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


class WarehouseServiceTest {
    @AfterEach
    void cleanup() {
        WarehouseConfig.INSTANCE.reset();
    }


    @Test
    void shouldReturnConfirmationWhenAcceptPackageIsCorrect() {
        WarehouseService warehouseService = new WarehouseService(WarehouseConfig.INSTANCE);

        String confirmation = warehouseService.acceptPackage("PCK-001", 34);

        assertEquals("Package PCK-001 accepted at Central Warehouse, country: PL", confirmation);

    }

    @Test
    void shouldThrowIllegalArgumentWhenWeightIsBelowEquals0() {
        WarehouseService warehouseService = new WarehouseService(WarehouseConfig.INSTANCE);

        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                () -> warehouseService.acceptPackage("PCK-001", 0));

        assertEquals("Weight must be positive", ex.getMessage());
    }

    @Test
    void shouldReturnInfoWhenGetWarehouseInfo() {
        WarehouseService warehouseService = new WarehouseService(WarehouseConfig.INSTANCE);

        String info = warehouseService.getWarehouseInfo();

        assertEquals("Central Warehouse (capacity: 10000, country: PL)", info);

    }

}