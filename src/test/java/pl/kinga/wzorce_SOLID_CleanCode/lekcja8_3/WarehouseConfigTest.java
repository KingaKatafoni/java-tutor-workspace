package pl.kinga.wzorce_SOLID_CleanCode.lekcja8_3;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WarehouseConfigTest {

    @AfterEach
    void setUp() {
        WarehouseConfig.INSTANCE.reset();
    }

    @Test
    void shouldReturnSameInstanceWhenGetInstance() {
        WarehouseConfig instance1 = WarehouseConfig.INSTANCE;
        WarehouseConfig instance2 = WarehouseConfig.INSTANCE;

        assertSame(instance1, instance2);
    }

    @Test
    void shouldReturnDefaultWarehouseNameWhenGetWarehouseName() {
        String warehouseName = WarehouseConfig.INSTANCE.getWarehouseName();

        assertEquals("Central Warehouse", warehouseName);
    }

    @Test
    void shouldChangeValueWhenSetMaxCapacity() {
        WarehouseConfig.INSTANCE.setMaxCapacity(30);

        int maxCapacity = WarehouseConfig.INSTANCE.getMaxCapacity();

        assertEquals(30, maxCapacity);
    }

    @Test
    void shouldThrowIllegalArgumentWhenSetMaxCapacityBelow0() {
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                () -> WarehouseConfig.INSTANCE.setMaxCapacity(-23)
        );

        assertEquals("Capacity must be positive", ex.getMessage());
    }

    @Test
    void shouldReturnDefaultValueWhenReset() {

        WarehouseConfig.INSTANCE.setMaxCapacity(23);
        int maxCapacityBeforeReset = WarehouseConfig.INSTANCE.getMaxCapacity();
        WarehouseConfig.INSTANCE.reset();
        int maxCapacityAfterReset = WarehouseConfig.INSTANCE.getMaxCapacity();

        assertEquals(23, maxCapacityBeforeReset);
        assertEquals(10000, maxCapacityAfterReset);

    }


}