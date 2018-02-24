package nl.fluitit.api.termpayments.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
public class TermPayment {
    private int term;
    private BigDecimal totalAmount;
    private BigDecimal interestAmount;
    private BigDecimal repaymentAmount;
    private BigDecimal remainingDebtAfterRepayment;
}
