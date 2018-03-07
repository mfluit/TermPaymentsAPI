package nl.fluitit.api.termpayments.calculation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static java.math.MathContext.DECIMAL32;

@Component
public class AnnuityCalculator {

    private final AnnuityFactorCalculator annuityFactorCalculator;

    @Autowired
    public AnnuityCalculator(AnnuityFactorCalculator annuityFactorCalculator) {
        this.annuityFactorCalculator = annuityFactorCalculator;
    }

    public BigDecimal calculateAnnuity(final BigDecimal remainingDebt,
                                       final BigDecimal interestRate,
                                       final int remainingPeriods) {

        BigDecimal interestRatio = interestRate.divide(new BigDecimal(100));
        BigDecimal interestRatioPerMonth = interestRatio.divide(BigDecimal.valueOf(12), DECIMAL32);
        BigDecimal annuityFactor = annuityFactorCalculator.annuityFactor(interestRatioPerMonth, remainingPeriods);

        return remainingDebt.divide(annuityFactor, 2, RoundingMode.HALF_UP);
    }
}
