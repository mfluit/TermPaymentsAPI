package nl.fluitit.api.termpayments.calculation;

import nl.fluitit.api.termpayments.model.TermPayment;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

public class LinearTermPaymentsCalculatorTest {

    private TermPaymentsCalculator calculator = new LinearTermPaymentsCalculator();

    @Test
    public void givenAnLinear_calculateTermPayments_returnsCorrectListofTermPayments() {
        List<TermPayment> result = calculator.calculateTermPayments(BigDecimal.TEN, 10, BigDecimal.valueOf(5.0));
        assertThat(result.get(0).getTerm(), equalTo(10));
    }

}