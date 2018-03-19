package nl.fluitit.api.termpayments.calculation;

import nl.fluitit.api.termpayments.model.TermPayment;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import static java.math.MathContext.DECIMAL32;

public abstract class TermPaymentsCalculator {

    public abstract List<TermPayment> calculateTermPayments(final BigDecimal remainingDebt,
                                                     final int remainingPeriods,
                                                     final BigDecimal interestRate);

    BigDecimal interestRatio(BigDecimal interestRate) {
            return interestRate.divide(BigDecimal.valueOf(100));
    }

    BigDecimal interestAmount(BigDecimal interestRatio, BigDecimal remainingDebtAfterRepayment) {
        BigDecimal monthsPerYear = new BigDecimal(12);
        BigDecimal interestRateRatioPerPeriod = interestRatio.divide(monthsPerYear, DECIMAL32);

        return remainingDebtAfterRepayment
                .multiply(interestRateRatioPerPeriod)
                .setScale(2, RoundingMode.HALF_UP);
    }

    BigDecimal remainingDebtAfterRepayment(BigDecimal remainingDebtAfterRepayment, BigDecimal repaymentAmount) {
        remainingDebtAfterRepayment = remainingDebtAfterRepayment
                .subtract(repaymentAmount)
                .setScale(2, RoundingMode.HALF_UP)
                .max(BigDecimal.ZERO);
        return remainingDebtAfterRepayment;
    }

}
