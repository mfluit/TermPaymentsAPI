package nl.fluitit.api.termpayments.calculation;

import nl.fluitit.api.termpayments.model.TermPayment;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Component
public class LinearTermPaymentsCalculator implements TermPaymentsCalculator {

    /**
     * For linear calculation no term revision is applied (for now).
     * This means that the repayment amount will be the same until full repayment.
     */
    @Override
    public List<TermPayment> calculateTermPayments(final BigDecimal remainingDebt,
                                                   final int remainingPeriods,
                                                   final BigDecimal interestRate) {
        List<TermPayment> termPayments = new ArrayList<>();

        // 1) calculateRepaymentAmount (if no term revision, this will stay the same, therefore outside of loop)


        for (int term = remainingPeriods; term >= 0; term--) {
            //2) calculate interestAmount
            //3) calculate new remaining debt
            //4) calculate total amount
            //4) add amounts to list of term payments


            //TODO: implement the calculations for linear and add the correct values
            termPayments.add(new TermPayment(term, BigDecimal.TEN, BigDecimal.ONE, BigDecimal.ONE, remainingDebt.subtract(BigDecimal.ONE)));
        }

        return termPayments;
    }
}