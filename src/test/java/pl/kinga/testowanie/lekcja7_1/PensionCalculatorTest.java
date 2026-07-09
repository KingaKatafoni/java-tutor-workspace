package pl.kinga.testowanie.lekcja7_1;

import org.junit.jupiter.api.Test;
import pl.kinga.fundamenty.PrzewidzWynik;

import static org.junit.jupiter.api.Assertions.*;

class PensionCalculatorTest {
    //Konwencja: `should[OczekiwanyWynik]When[Warunek]`

    @Test
    void shouldReturnCorrectPensionWhenRetirementAge60(){
        PensionCalculator calculator = new PensionCalculator();

        double pension = calculator.calculateMonthlyPension(300000, 60);

        assertEquals(1149.43, pension);
    }

    @Test
    void shouldReturnCorrectMonthlyPensionWhenRetirementAge65(){
        PensionCalculator calculator = new PensionCalculator();

        double pension = calculator.calculateMonthlyPension(500000, 65);

        assertEquals(2347.42, pension);
    }

    @Test
    void shouldReturnCorrectMonthlyPensionWhenRetirementAge67(){
        PensionCalculator calculator = new PensionCalculator();

        double pension = calculator.calculateMonthlyPension(800000, 67);

        assertEquals(4040.40, pension);
    }

    @Test
    void shouldThrowIllegalArgumentExceptionWhenAgeUnsupported(){
        PensionCalculator calculator = new PensionCalculator();

        assertThrows(IllegalArgumentException.class,
                () -> calculator.calculateMonthlyPension(50000.0, 55));
    }

    @Test
    void shouldThrowIllegalArgumentWhenContributionBelowZero(){
        PensionCalculator calculator = new PensionCalculator();

        assertThrows(IllegalArgumentException.class,
                () -> calculator.calculateMonthlyPension(-1000, 60));
    }

    @Test
    void shouldReturnBelowMinimumWhenPension1200(){
        PensionCalculator calculator = new PensionCalculator();

        String category = calculator.estimateCategory(1200.0);

        assertEquals("BELOW_MINIMUM", category);
    }

    @Test
    void shouldReturnMinimumWhenPension1600(){
        PensionCalculator calculator = new PensionCalculator();
        String category = calculator.estimateCategory(1600.0);
        assertEquals("MINIMUM", category);
    }

    @Test
    void shouldReturnMinimumWhenPension2999_99(){
        PensionCalculator calculator = new PensionCalculator();
        String category = calculator.estimateCategory(2999.99);
        assertEquals("MINIMUM", category);
    }

    @Test
    void shouldReturnAverageWhenPension3000(){
        PensionCalculator calculator = new PensionCalculator();
        String category = calculator.estimateCategory(3000.0);
        assertEquals("AVERAGE", category);
    }

    @Test
    void shouldReturnAboveAverageWhenPension5000(){
        PensionCalculator calculator = new PensionCalculator();
        String category = calculator.estimateCategory(5000.00);
        assertEquals("ABOVE_AVERAGE", category);
    }

    @Test
    void shouldThrowIllegalArgumentWhenPensionBelow0(){
        PensionCalculator calculator = new PensionCalculator();
        assertThrows(IllegalArgumentException.class,
                () -> calculator.estimateCategory(-100.00));
    }



}