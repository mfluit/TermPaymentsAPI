package nl.fluitit.api.termpayments.resources.data;

import lombok.Setter;
import nl.fluitit.api.termpayments.model.TypeOfRepayment;

import java.math.BigDecimal;

@Setter
public class TermPaymentsRequest {

    private BigDecimal remainingDebt;
    private int remainingPeriods;
    private BigDecimal interestRate;
    private TypeOfRepayment typeOfRepayment;

}
