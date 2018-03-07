package nl.fluitit.api.termpayments.calculation;

import nl.fluitit.api.termpayments.model.TermPayment;
import nl.fluitit.api.termpayments.model.TypeOfRepayment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import static java.math.MathContext.DECIMAL32;

@Component
public class TermPaymentsCalculator {

    private final AnnuityCalculator annuityCalculator;

    @Autowired
    public TermPaymentsCalculator(AnnuityCalculator annuityCalculator) {
        this.annuityCalculator = annuityCalculator;
    }

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

        BigDecimal annuity = annuityCalculator.calculateAnnuity(remainingDebt, interestRate, remainingPeriods);
        BigDecimal interestRatio = interestRate.divide(BigDecimal.valueOf(100));
        BigDecimal remainingDebtAfterRepayment = remainingDebt;

        for (int term = remainingPeriods; term >= 0; term--) {
            BigDecimal interestAmount = interestAmount(term, interestRatio, remainingDebtAfterRepayment);
            BigDecimal repaymentAmount = repaymentAmount(annuity, interestAmount);
            remainingDebtAfterRepayment = remainingDebtAfterRepayment(remainingDebtAfterRepayment, repaymentAmount);

            termPayments.add(new TermPayment(term, annuity, interestAmount, repaymentAmount, remainingDebtAfterRepayment));
        }

        return termPayments;
    }

    private BigDecimal remainingDebtAfterRepayment(BigDecimal remainingDebtAfterRepayment, BigDecimal repaymentAmount) {
        remainingDebtAfterRepayment = remainingDebtAfterRepayment
                .subtract(repaymentAmount)
                .setScale(2, RoundingMode.HALF_UP)
                .max(BigDecimal.ZERO);
        return remainingDebtAfterRepayment;
    }

    private BigDecimal repaymentAmount(BigDecimal annuity, BigDecimal interestAmount) {
        return annuity
                        .subtract(interestAmount)
                        .setScale(2, RoundingMode.HALF_UP);
    }

    private BigDecimal interestAmount(int remainingPeriods, BigDecimal interestRatio, BigDecimal remainingDebtAfterRepayment) {
        BigDecimal monthsPerYear = new BigDecimal(12);
        BigDecimal interestRateRatioPerPeriod = interestRatio.divide(monthsPerYear, DECIMAL32);

        return remainingDebtAfterRepayment
                .multiply(interestRateRatioPerPeriod)
                .setScale(2, RoundingMode.HALF_UP);
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