package nl.fluitit.api.termpayments.calculation;

import org.junit.Test;

import java.math.BigDecimal;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

public class AnnuityCalculatorTest {

    private AnnuityFactorCalculator annuityFactorCalculator = new AnnuityFactorCalculator();
    private AnnuityCalculator annuityCalculator = new AnnuityCalculator(annuityFactorCalculator);

    @Test
    public void calculateAnnuity() {
        BigDecimal remainingDebt = new BigDecimal("178700");
        BigDecimal interestRate = new BigDecimal("4.2");
        int remainingPeriods = 360;

        BigDecimal annuity = annuityCalculator.calculateAnnuity(remainingDebt, interestRate, remainingPeriods);

        assertThat(annuity, equalTo(BigDecimal.valueOf(873.87)));
    }


}