package nl.fluitit.api.termpayments.resources.data;

import nl.fluitit.api.termpayments.model.TypeOfRepayment;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.math.BigDecimal;
import java.util.Set;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TermPaymentsRequestTest {

    private static ValidatorFactory validatorFactory;
    private static Validator validator;

    @Before
    public void setup(){
        validatorFactory = Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.getValidator();
    }

    @After
    public void close() {
        validatorFactory.close();
    }

    @Test
    public void passesValidationWhenValidRequestIsPresent(){
        TermPaymentsRequest validRequest = validRequest().build();

        Set<ConstraintViolation<TermPaymentsRequest>> violations = validator.validate(validRequest);

        assertThat(violations, hasSize(0));
    }

    @Test
    public void failsValidationWhenNoRemainingDebtIsPresent() {
        TermPaymentsRequest invalidRequest = validRequest().remainingDebt(null).build();

        Set<ConstraintViolation<TermPaymentsRequest>> violations = validator.validate(invalidRequest);

        assertThat(violations.iterator().next().getMessage(), is("The remaining debt is required."));
    }

    @Test
    public void failsValidationWhenRemainingDebtIsLowerThanZero() {
        TermPaymentsRequest invalidRequest = validRequest().remainingDebt(BigDecimal.ZERO).build();

        Set<ConstraintViolation<TermPaymentsRequest>> violations = validator.validate(invalidRequest);

        assertThat(violations.iterator().next().getMessage(), is("The remaining debt amount must be higher than 0.00."));
    }

    @Test
    public void failsValidationWhenRemainingDebtIsHigherThanOneBillion() {
        TermPaymentsRequest invalidRequest = validRequest().remainingDebt(new BigDecimal("1000000001.00")).build();

        Set<ConstraintViolation<TermPaymentsRequest>> violations = validator.validate(invalidRequest);

        assertThat(violations.iterator().next().getMessage(), is("The remaining debt amount must be lower than 1000000000.00."));
    }

    @Test
    public void failsValidationWhenNoRemainingPeriodsArePresent() {
        TermPaymentsRequest invalidRequest = validRequest().remainingPeriods(null).build();

        Set<ConstraintViolation<TermPaymentsRequest>> violations = validator.validate(invalidRequest);

        assertThat(violations.iterator().next().getMessage(), is("The remaining periods are required."));
    }

    @Test
    public void failsValidationWhenRemainingPeriodsAreZero(){
        TermPaymentsRequest invalidRequest = validRequest().remainingPeriods(0).build();

        Set<ConstraintViolation<TermPaymentsRequest>> violations = validator.validate(invalidRequest);

        assertThat(violations.iterator().next().getMessage(), is("The remaining periods must be at least 1 month."));
    }

    @Test
    public void failsValidationWhenRemainingPeriodsAreLargerThan360Months(){
        TermPaymentsRequest invalidRequest = validRequest().remainingPeriods(361).build();

        Set<ConstraintViolation<TermPaymentsRequest>> violations = validator.validate(invalidRequest);

        assertThat(violations.iterator().next().getMessage(), is("The remaining periods cannot be larger than 360 months."));
    }

    @Test
    public void failsValidationWhenNoInterestRateIsPresent() {
        TermPaymentsRequest invalidRequest = validRequest().interestRate(null).build();

        Set<ConstraintViolation<TermPaymentsRequest>> violations = validator.validate(invalidRequest);

        assertThat(violations.iterator().next().getMessage(), is("The interest rate is required."));
    }

    @Test
    public void failsValidationWhenInterestRateIsBelowZero() {
        TermPaymentsRequest invalidRequest = validRequest().interestRate(new BigDecimal("-1.00")).build();

        Set<ConstraintViolation<TermPaymentsRequest>> violations = validator.validate(invalidRequest);

        assertThat(violations.iterator().next().getMessage(), is("The interest rate cannot be negative."));
    }

    @Test
    public void failsValidationWhenInterestRateIsAbove999Percent() {
        TermPaymentsRequest invalidRequest = validRequest().interestRate(new BigDecimal("1000.000")).build();

        Set<ConstraintViolation<TermPaymentsRequest>> violations = validator.validate(invalidRequest);

        assertThat(violations.iterator().next().getMessage(), is("The interest rate cannot be higher than 999.000."));
    }

    @Test
    public void failsValidationWhenNoTypeOfRepaymentIsPresent() {
        TermPaymentsRequest invalidRequest = validRequest().typeOfRepayment(null).build();

        Set<ConstraintViolation<TermPaymentsRequest>> violations = validator.validate(invalidRequest);

        assertThat(violations.iterator().next().getMessage(), is("The type of repayment is required."));
    }


    private TermPaymentsRequest.TermPaymentsRequestBuilder validRequest() {
        return TermPaymentsRequest.builder()
                .remainingDebt(BigDecimal.TEN)
                .remainingPeriods(5)
                .interestRate(BigDecimal.ONE)
                .typeOfRepayment(TypeOfRepayment.ANNUITY);
    }
}