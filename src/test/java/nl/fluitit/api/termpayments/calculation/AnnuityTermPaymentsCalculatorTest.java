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
                BigDecimal.valueOf(178700),
                360,
                BigDecimal.valueOf(4.2)
        );

        assertThat(termPayments.get(0).getTerm(), equalTo(360));
        assertThat(termPayments.get(0).getTotalAmount(), equalTo(new BigDecimal("873.87")));
        assertThat(termPayments.get(0).getInterestAmount(), equalTo(new BigDecimal("625.45")));
        assertThat(termPayments.get(0).getRepaymentAmount(), equalTo(new BigDecimal("248.42")));
        assertThat(termPayments.get(0).getRemainingDebtAfterRepayment(), equalTo(new BigDecimal("178451.58")));

        assertThat(termPayments.get(359).getTerm(), equalTo(1));
        assertThat(termPayments.get(359).getTotalAmount(), equalTo(new BigDecimal("873.87")));
        assertThat(termPayments.get(359).getInterestAmount(), equalTo(new BigDecimal("3.06")));
        assertThat(termPayments.get(359).getRepaymentAmount(), equalTo(new BigDecimal("870.81")));
        assertThat(termPayments.get(359).getRemainingDebtAfterRepayment(), equalTo(new BigDecimal("2.78")));
    }
}