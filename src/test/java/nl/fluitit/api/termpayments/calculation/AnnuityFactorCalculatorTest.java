package nl.fluitit.api.termpayments.calculation;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

import static java.math.MathContext.DECIMAL32;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AnnuityFactorCalculatorTest {

    @Autowired
    private AnnuityFactorCalculator calculator;

    @Test
    public void calculateAnnuity() {

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