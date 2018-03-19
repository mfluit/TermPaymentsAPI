package nl.fluitit.api.termpayments.calculation;

import nl.fluitit.api.termpayments.model.TermPayment;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

public class AnnuityTermPaymentsCalculatorTest {

    private TermPaymentsCalculator calculator = new AnnuityTermPaymentsCalculator(new AnnuityCalculator(new AnnuityFactorCalculator()));

    @Test
    public void givenAnAnnuity_calculateTermPayments_returnsCorrectListOfTermPayments() {
        List<TermPayment> termPayments = calculator.calculateTermPayments(
                new BigDecimal("178700"),
                360,
                new BigDecimal("4.2")
        );

        assertThat(termPayments.get(0).getTerm(), equalTo(360));
        assertThat(termPayments.get(0).getTotalAmount(), equalTo(BigDecimal.valueOf(873.87)));
        assertThat(termPayments.get(0).getInterestAmount(), equalTo(BigDecimal.valueOf(625.45)));
        assertThat(termPayments.get(0).getRepaymentAmount(), equalTo(BigDecimal.valueOf(248.42)));
        assertThat(termPayments.get(0).getRemainingDebtAfterRepayment(), equalTo(BigDecimal.valueOf(178451.58)));

        assertThat(termPayments.get(359).getTerm(), equalTo(1));
        assertThat(termPayments.get(359).getTotalAmount(), equalTo(BigDecimal.valueOf(873.87)));
        assertThat(termPayments.get(359).getInterestAmount(), equalTo(BigDecimal.valueOf(3.06)));
        assertThat(termPayments.get(359).getRepaymentAmount(), equalTo(BigDecimal.valueOf(870.81)));
        assertThat(termPayments.get(359).getRemainingDebtAfterRepayment(), equalTo(BigDecimal.valueOf(2.78)));
    }

    @Test
    public void testje() {
        List<TermPayment> result = calculator.calculateTermPayments(BigDecimal.TEN, 10, BigDecimal.valueOf(5.0));
        assertThat(result.get(0).getTerm(), equalTo(10));
    }

}