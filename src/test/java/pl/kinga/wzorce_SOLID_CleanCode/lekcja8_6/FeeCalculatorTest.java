package pl.kinga.wzorce_SOLID_CleanCode.lekcja8_6;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FeeCalculatorTest {

    @Mock
    FeeStrategy feeStrategy;

    @InjectMocks
    FeeCalculator feeCalculator;

    @Test
    void shouldReturnResultFromStrategyWhenDataCorrect() {
        when(feeStrategy.calculate(1, false)).thenReturn(0.0);
        double calculateTotalFee = feeCalculator.calculateTotalFee(1, false);

        assertEquals(0.0, calculateTotalFee);
        verify(feeStrategy).calculate(1, false);

    }

    @Test
    void shouldThrowIllegalArgumentWhenQuantityBelowZero() {
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                () -> feeCalculator.calculateTotalFee(-1, true));

        assertEquals("Quantity must be positive", ex.getMessage());
    }


}