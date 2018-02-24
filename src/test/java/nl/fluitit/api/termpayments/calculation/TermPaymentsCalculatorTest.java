package nl.fluitit.api.termpayments.calculation;

import nl.fluitit.api.termpayments.model.TermPayment;
import nl.fluitit.api.termpayments.model.TypeOfRepayment;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.*;

public class TermPaymentsCalculatorTest {

    private TermPaymentsCalculator calculator = new TermPaymentsCalculator();

    @Test
    public void givenAnAnnuity_calculateTermPayments_returnsCorrectListofTermPayments() {
        List<TermPayment> result = calculator.calculateTermPayments(BigDecimal.TEN,10,BigDecimal.valueOf(5.0), TypeOfRepayment.ANNUITY);
        assertThat(result.get(0).getTerm(), equalTo(10));
    }

    @Test
    public void givenAnLinear_calculateTermPayments_returnsCorrectListofTermPayments() {
        List<TermPayment> result = calculator.calculateTermPayments(BigDecimal.TEN,10,BigDecimal.valueOf(5.0), TypeOfRepayment.ANNUITY);
        assertThat(result.get(0).getTerm(), equalTo(10));
    }

}