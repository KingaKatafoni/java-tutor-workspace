package pl.kinga.wzorce_SOLID_CleanCode.lekcja8_8.TaskB;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TransactionFeeDecoratorTest {

    @Test
    void shouldBasicFeeReturnAmount() {
        BasicFee basicFee = new BasicFee();
        double calculated = basicFee.calculate(20.0);

        assertEquals(20.0, calculated);

    }

    @Test
    void shouldReturn123WhenVatDecoratorCalculate100() {
        VatDecorator vatDecorator = new VatDecorator(new BasicFee());

        double calculated = vatDecorator.calculate(100);

        assertEquals(123.0, calculated);
    }

    @Test
    void shouldReturn90WhenPremiumDiscountCalculate100() {
        PremiumDiscountDecorator premiumDiscountDecorator = new PremiumDiscountDecorator(new BasicFee());

        double calculated = premiumDiscountDecorator.calculate(100.0);

        assertEquals(90.0, calculated);
    }

    @Test
    void shouldReturn120WhenExpressFeeCalculate100() {
        ExpressFeeDecorator expressFeeDecorator = new ExpressFeeDecorator(new BasicFee());

        double calculated = expressFeeDecorator.calculate(100.0);

        assertEquals(120.0, calculated);
    }

    @Test
    void shouldReturn1107WhenVatAndPremium() {
        PremiumDiscountDecorator premiumDiscountDecorator = new PremiumDiscountDecorator(new VatDecorator(new BasicFee()));

        double calculated = premiumDiscountDecorator.calculate(100.0);

        assertEquals(110.7, calculated);
    }

    @Test
    void shouldReturn143WhenVatAndExpress() {
        ExpressFeeDecorator expressFeeDecorator = new ExpressFeeDecorator(new VatDecorator(new BasicFee()));

        double calculated = expressFeeDecorator.calculate(100.0);
        assertEquals(143.0, calculated);
    }

    @Test
    void shouldReturn13284WhenAllDecorators() {
        VatDecorator vatDecorator = new VatDecorator(new PremiumDiscountDecorator(new ExpressFeeDecorator(new BasicFee())));

        double calculated = vatDecorator.calculate(100.0);
        assertEquals(132.84, calculated);
    }
}
