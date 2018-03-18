package nl.fluitit.api.termpayments.calculation;

import nl.fluitit.api.termpayments.model.TermPayment;

import java.math.BigDecimal;
import java.util.List;

public interface TermPaymentsCalculator {

    List<TermPayment> calculateTermPayments(final BigDecimal remainingDebt,
                                            final int remainingPeriods,
                                            final BigDecimal interestRate);
}
