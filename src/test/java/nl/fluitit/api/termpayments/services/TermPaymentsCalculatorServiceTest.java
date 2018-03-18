package nl.fluitit.api.termpayments.services;

import nl.fluitit.api.termpayments.calculation.AnnuityTermPaymentsCalculator;
import nl.fluitit.api.termpayments.calculation.LinearTermPaymentsCalculator;
import nl.fluitit.api.termpayments.model.TermPayment;
import nl.fluitit.api.termpayments.model.TypeOfRepayment;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TermPaymentsCalculatorServiceTest {

    @Autowired
    private TermPaymentsCalculatorService service;

    @MockBean
    AnnuityTermPaymentsCalculator annuityTermPaymentsCalculator;
    @MockBean
    LinearTermPaymentsCalculator linearTermPaymentsCalculator;

    @Mock
    private List<TermPayment> termPayments;

    private MockHelper mock;

    @Before
    public void setup() {
        mock = new MockHelper();
    }

    @Test
    public void givenTypeOfRepaymentAnnuity_WhenCalculatingTermPayments_callsAnnuityTermPaymentsCalculator() {
        mock.annuityTermPaymentsCalculatorReturnsResult();

        List<TermPayment> result =
                service.calculateTermPayments(mock.remainingDebt, mock.remainingPeriods, mock.interestRate, TypeOfRepayment.ANNUITY);

        verifyAnnuityTermPaymentsCalculatorIsCalled();
        verifyNoInteractionWithLinearTermPaymentsCalculator();
        assertThat(result, is(termPayments));
    }

    @Test
    public void givenTypeOfRepaymentLinear_WhenCalculatingTermPayments_callsLinearTermPaymentsCalculator() {
        mock.linearTermPaymentsCalculatorReturnsResult();

        List<TermPayment> result =
                service.calculateTermPayments(mock.remainingDebt, mock.remainingPeriods, mock.interestRate, TypeOfRepayment.LINEAR);

        verifyLinearTermPaymentsCalculatorIsCalled();
        verifyNoInteractionWithAnnuityTermPaymentsCalculator();
        assertThat(result, is(termPayments));
    }

    private void verifyAnnuityTermPaymentsCalculatorIsCalled() {
        verify(annuityTermPaymentsCalculator)
                .calculateTermPayments(eq(mock.remainingDebt), eq(mock.remainingPeriods), eq(mock.interestRate));
    }

    private void verifyLinearTermPaymentsCalculatorIsCalled() {
        verify(linearTermPaymentsCalculator)
                .calculateTermPayments(eq(mock.remainingDebt), eq(mock.remainingPeriods), eq(mock.interestRate));
    }

    private void verifyNoInteractionWithAnnuityTermPaymentsCalculator() {
        verify(annuityTermPaymentsCalculator,
                never()).calculateTermPayments(any(BigDecimal.class), any(Integer.class), any(BigDecimal.class));
    }

    private void verifyNoInteractionWithLinearTermPaymentsCalculator() {
        verify(linearTermPaymentsCalculator,
                never()).calculateTermPayments(any(BigDecimal.class), any(Integer.class), any(BigDecimal.class));
    }

    class MockHelper {

        BigDecimal remainingDebt = BigDecimal.TEN;
        int remainingPeriods = 5;
        BigDecimal interestRate = BigDecimal.ONE;

        public void annuityTermPaymentsCalculatorReturnsResult() {
            when(annuityTermPaymentsCalculator.calculateTermPayments(any(BigDecimal.class), any(Integer.class), any(BigDecimal.class)))
                    .thenReturn(termPayments);
        }

        public void linearTermPaymentsCalculatorReturnsResult() {
            when(linearTermPaymentsCalculator.calculateTermPayments(any(BigDecimal.class), any(Integer.class), any(BigDecimal.class)))
                    .thenReturn(termPayments);
        }
    }

}