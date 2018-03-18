package nl.fluitit.api.termpayments.calculation;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AnnuityCalculatorTest {

    @Autowired
    private AnnuityCalculator annuityCalculator;

    @Test
    public void calculateAnnuity() {
        BigDecimal remainingDebt = new BigDecimal("178700");
        BigDecimal interestRate = new BigDecimal("4.2");
        int remainingPeriods = 360;

        BigDecimal annuity = annuityCalculator.calculateAnnuity(remainingDebt, interestRate, remainingPeriods);

        assertThat(annuity, equalTo(BigDecimal.valueOf(873.87)));
    }


}