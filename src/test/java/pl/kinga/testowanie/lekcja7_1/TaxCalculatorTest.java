package pl.kinga.testowanie.lekcja7_1;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TaxCalculatorTest {
    @Test
    void shouldCalculate12PercentTaxForIncomeBelow120k(){
        // given
        TaxCalculator calc = new TaxCalculator();

        //when
        double tax = calc.calculateIncomeTax(100000);

        //then
        assertEquals(12000, tax);
    }

    @Test
    void shouldCalculate32PercentTaxForIncomeAbove120k(){

        TaxCalculator calc = new TaxCalculator();

        double tax = calc.calculateIncomeTax(200000);

        assertEquals(40000.0, tax);

    }

    @Test
    void shouldThrowExceptionForNegativeIncome(){

        TaxCalculator calc = new TaxCalculator();

        assertThrows(IllegalArgumentException.class,
                () -> calc.calculateIncomeTax(-5000)
                );
    }
}