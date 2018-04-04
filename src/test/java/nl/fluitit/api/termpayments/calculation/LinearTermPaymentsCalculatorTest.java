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
    public void givenAnLinear_calculateTermPayments_returnsCorrectListOfTermPayments() {
        List<TermPayment> result = calculator.calculateTermPayments(new BigDecimal("10000.00"), 10, new BigDecimal("5.0"));
        assertThat(result.get(0).getTerm(), equalTo(10));
        assertThat(result.get(0).getTotalAmount(), equalTo(new BigDecimal("1041.67")));
        assertThat(result.get(0).getInterestAmount(), equalTo(new BigDecimal("41.67")));
        assertThat(result.get(0).getRepaymentAmount(), equalTo(new BigDecimal("1000.00")));
        assertThat(result.get(0).getRemainingDebtAfterRepayment(), equalTo(new BigDecimal("9000.00")));

        assertThat(result.get(9).getTerm(), equalTo(1));
        assertThat(result.get(9).getTotalAmount(), equalTo(new BigDecimal("1004.17")));
        assertThat(result.get(9).getInterestAmount(), equalTo(new BigDecimal("4.17")));
        assertThat(result.get(9).getRepaymentAmount(), equalTo(new BigDecimal("1000.00")));
        assertThat(result.get(9).getRemainingDebtAfterRepayment(), equalTo(new BigDecimal("0.00")));

    }

}