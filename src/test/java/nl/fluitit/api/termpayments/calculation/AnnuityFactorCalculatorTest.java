package nl.fluitit.api.termpayments.calculation;

import org.junit.Test;

import java.math.BigDecimal;
import java.math.MathContext;

import static java.math.MathContext.DECIMAL32;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

public class AnnuityFactorCalculatorTest {

    @Test
    public void calculateAnnuity() {
        AnnuityFactorCalculator calculator = new AnnuityFactorCalculator();

        assertThat(calculator.annuityFactor(interestRatePerPeriod(new BigDecimal("0.1"), 12), 360),
                equalTo(new BigDecimal("113.9508")));
        assertThat(calculator.annuityFactor(interestRatePerPeriod(new BigDecimal("1"), 12), 360),
                equalTo(new BigDecimal("12.00000")));
        assertThat(calculator.annuityFactor(interestRatePerPeriod(new BigDecimal("10"), 12), 360),
                equalTo(new BigDecimal("1.200000")));
    }

    private BigDecimal interestRatePerPeriod(BigDecimal interestRate, int periods) {
        return interestRate.divide(BigDecimal.valueOf(periods), DECIMAL32);
    }
}