package nl.fluitit.api.termpayments.resources.data;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import nl.fluitit.api.termpayments.model.TypeOfRepayment;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Getter
@Builder
public class TermPaymentsRequest {

    private static final String MIN_AMOUNT = "1.00";
    private static final String MAX_AMOUNT = "1000000000.00";

    @NotNull(message = "The remaining debt is required.")
    @DecimalMin(value = "0.00", inclusive = false, message = "The remaining debt amount must be higher than {value}.")
    @DecimalMax(value = MAX_AMOUNT, message = "The remaining debt amount must be lower than {value}.")
    private BigDecimal remainingDebt;

    @NotNull(message = "The remaining periods are required.")
    @Min(value = 1, message = "The remaining periods must be at least {value} month.")
    @Max(value = 360, message = "The remaining periods cannot be larger than {value} months.")
    private Integer remainingPeriods;

    @NotNull(message = "The interest rate is required.")
    @DecimalMin(value = "0.000", inclusive = true, message = "The interest rate cannot be negative.")
    @DecimalMax(value = "999.000", inclusive = true, message = "The interest rate cannot be higher than {value}.")
    private BigDecimal interestRate;

    @NotNull(message = "The type of repayment is required.")
    private TypeOfRepayment typeOfRepayment;

    @JsonCreator
    public TermPaymentsRequest(@JsonProperty("remainingDebt") final BigDecimal remainingDebt,
                               @JsonProperty("remainingPeriods") final Integer remainingPeriods,
                               @JsonProperty("interestRate") final BigDecimal interestRate,
                               @JsonProperty("typeOfRepayment") final TypeOfRepayment typeOfRepayment) {
        this.remainingDebt = remainingDebt;
        this.remainingPeriods = remainingPeriods;
        this.interestRate = interestRate;
        this.typeOfRepayment = typeOfRepayment;
    }
}
