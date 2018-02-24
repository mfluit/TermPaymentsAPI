package nl.fluitit.api.termpayments.calculation;

import nl.fluitit.api.termpayments.model.TermPayment;
import nl.fluitit.api.termpayments.model.TypeOfRepayment;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class TermPaymentsCalculator {

    public List<TermPayment> calculateTermPayments(final BigDecimal remainingDebt,
                                                   final int remainingPeriods,
                                                   final BigDecimal interestRate,
                                                   final TypeOfRepayment typeOfRepayment) {
        if (typeOfRepayment.equals(TypeOfRepayment.ANNUITY)) {
            return calculateAnnuityTermPayments(remainingDebt, remainingPeriods, interestRate);
        } else {
            return calculateLinearTermPayments(remainingDebt, remainingPeriods, interestRate);
        }

    }

    private List<TermPayment> calculateAnnuityTermPayments(final BigDecimal remainingDebt,
                                                           final int remainingPeriods,
                                                           final BigDecimal interestRate) {
        List<TermPayment> termPayments = new ArrayList<>();

        for (int term = remainingPeriods; term >= 0; term--) {
            //TODO: implement the calculations for annuity and add the correct values
            termPayments.add(new TermPayment(term, BigDecimal.TEN, BigDecimal.ONE, BigDecimal.ONE, remainingDebt.subtract(BigDecimal.ONE)));
        }

        return termPayments;
    }

    private List<TermPayment> calculateLinearTermPayments(final BigDecimal remainingDebt,
                                                          final int remainingPeriods,
                                                          final BigDecimal interestRate) {
        List<TermPayment> termPayments = new ArrayList<>();

        for (int term = remainingPeriods; term >= 0; term--) {
            //TODO: implement the calculations for linear and add the correct values
            termPayments.add(new TermPayment(term, BigDecimal.TEN, BigDecimal.ONE, BigDecimal.ONE, remainingDebt.subtract(BigDecimal.ONE)));
        }

        return termPayments;
    }
}