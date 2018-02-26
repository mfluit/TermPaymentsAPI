package nl.fluitit.api.termpayments.calculation;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;

import static java.math.BigDecimal.ONE;
import static java.math.MathContext.DECIMAL32;

@Service
public class AnnuityFactorCalculator {
    /**
     * Calculates the annuity factor
     * <p>
     * ( 1 - ( 1 + INTEREST_RATIO_PER_PERIOD )^-REMAINING_PERIODS) / INTEREST_RATIO_PER_PERIOD
     */
    public BigDecimal annuityFactor(final BigDecimal interestRatioPerPeriod, final int remainingPeriods) {

        return ONE
                .subtract(ONE.add(interestRatioPerPeriod).pow(-remainingPeriods, DECIMAL32))
                .divide(interestRatioPerPeriod, DECIMAL32);
    }
}
