package nl.fluitit.api.termpayments.calculation;

import nl.fluitit.api.termpayments.model.TermPayment;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

@Component
public class LinearTermPaymentsCalculator extends TermPaymentsCalculator {

    @Override
    public List<TermPayment> calculateTermPayments(final BigDecimal remainingDebt,
                                                   final int remainingPeriods,
                                                   final BigDecimal interestRate) {
        List<TermPayment> termPayments = new ArrayList<>();
        BigDecimal interestRatio = interestRatio(interestRate);
        BigDecimal remainingDebtAfterRepayment = remainingDebt;

        for (int term = remainingPeriods; term > 0; term--) {
            BigDecimal interestAmount = interestAmount(interestRatio, remainingDebtAfterRepayment);
            BigDecimal repaymentAmount = remainingDebtAfterRepayment.divide(BigDecimal.valueOf(term), 2, RoundingMode.HALF_UP);
            BigDecimal termPaymentAmount = repaymentAmount.add(interestAmount);
            remainingDebtAfterRepayment = remainingDebtAfterRepayment(remainingDebtAfterRepayment, repaymentAmount);

            termPayments.add(new TermPayment(term, termPaymentAmount, interestAmount, repaymentAmount, remainingDebtAfterRepayment));
        }
        return termPayments;
    }
}