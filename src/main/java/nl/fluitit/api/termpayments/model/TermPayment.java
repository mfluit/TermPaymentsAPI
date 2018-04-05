package nl.fluitit.api.termpayments.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.xml.bind.annotation.XmlRootElement;
import java.math.BigDecimal;

@Getter
@AllArgsConstructor
@XmlRootElement
public class TermPayment {
    private int term;
    private BigDecimal totalAmount;
    private BigDecimal interestAmount;
    private BigDecimal repaymentAmount;
    private BigDecimal remainingDebtAfterRepayment;
}
