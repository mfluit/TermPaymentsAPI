package nl.fluitit.api.termpayments.calculation;

import nl.fluitit.api.termpayments.model.TermPayment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import static java.math.MathContext.DECIMAL32;

@Component
public class AnnuityTermPaymentsCalculator implements TermPaymentsCalculator {

    private final AnnuityCalculator annuityCalculator;

    @Autowired
    public AnnuityTermPaymentsCalculator(AnnuityCalculator annuityCalculator) {
        this.annuityCalculator = annuityCalculator;
    }

    @Override
    public List<TermPayment> calculateTermPayments(final BigDecimal remainingDebt,
                                                   final int remainingPeriods,
                                                   final BigDecimal interestRate) {
        List<TermPayment> termPayments = new ArrayList<>();

        BigDecimal annuity = annuityCalculator.calculateAnnuity(remainingDebt, interestRate, remainingPeriods);
        BigDecimal interestRatio = interestRate.divide(BigDecimal.valueOf(100));
        BigDecimal remainingDebtAfterRepayment = remainingDebt;

        for (int term = remainingPeriods; term >= 0; term--) {
            BigDecimal interestAmount = interestAmount(interestRatio, remainingDebtAfterRepayment);
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

    private BigDecimal interestAmount(BigDecimal interestRatio, BigDecimal remainingDebtAfterRepayment) {
        BigDecimal monthsPerYear = new BigDecimal(12);
        BigDecimal interestRateRatioPerPeriod = interestRatio.divide(monthsPerYear, DECIMAL32);

        return remainingDebtAfterRepayment
                .multiply(interestRateRatioPerPeriod)
                .setScale(2, RoundingMode.HALF_UP);
    }
}