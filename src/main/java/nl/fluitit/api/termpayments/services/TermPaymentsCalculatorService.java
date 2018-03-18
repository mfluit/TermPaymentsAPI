package nl.fluitit.api.termpayments.services;

import nl.fluitit.api.termpayments.calculation.AnnuityTermPaymentsCalculator;
import nl.fluitit.api.termpayments.calculation.LinearTermPaymentsCalculator;
import nl.fluitit.api.termpayments.calculation.TermPaymentsCalculator;
import nl.fluitit.api.termpayments.model.TermPayment;
import nl.fluitit.api.termpayments.model.TypeOfRepayment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
public class TermPaymentsCalculatorService {

    private final TermPaymentsCalculator annuityTermPaymentsCalculator;
    private final TermPaymentsCalculator linearTermPaymentsCalculator;

    @Autowired
    public TermPaymentsCalculatorService(AnnuityTermPaymentsCalculator annuityTermPaymentsCalculator, LinearTermPaymentsCalculator linearTermPaymentsCalculator) {
        this.annuityTermPaymentsCalculator = annuityTermPaymentsCalculator;
        this.linearTermPaymentsCalculator = linearTermPaymentsCalculator;
    }

    public List<TermPayment> calculateTermPayments(final BigDecimal remainingDebt,
                                                   final int remainingPeriods,
                                                   final BigDecimal interestRate,
                                                   final TypeOfRepayment typeOfRepayment) {
        if (typeOfRepayment.equals(TypeOfRepayment.ANNUITY)) {
            return annuityTermPaymentsCalculator.calculateTermPayments(remainingDebt, remainingPeriods, interestRate);
        } else {
            return linearTermPaymentsCalculator.calculateTermPayments(remainingDebt, remainingPeriods, interestRate);
        }
    }
}