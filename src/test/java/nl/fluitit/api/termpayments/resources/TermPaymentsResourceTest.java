package nl.fluitit.api.termpayments.resources;

import nl.fluitit.api.termpayments.model.TermPayment;
import nl.fluitit.api.termpayments.model.TypeOfRepayment;
import nl.fluitit.api.termpayments.resources.data.TermPaymentsRequest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TermPaymentsResourceTest {

    @Autowired
    private TermPaymentsResource termPaymentsResource;

    @Test
    public void givenValidRequestCalculateTermPaymentsReturnsValidResponse() {
        TermPaymentsRequest request = validRequest().build();

        List<TermPayment> termPayments = termPaymentsResource.calculateTermPayments(request);

        assertThat(termPayments.size(), equalTo(5));
        assertThat(termPayments.get(0).getTerm(), equalTo(5));
        assertThat(termPayments.get(4).getTerm(), equalTo(1));
    }

    private TermPaymentsRequest.TermPaymentsRequestBuilder validRequest() {
        return TermPaymentsRequest.builder()
                .remainingDebt(BigDecimal.TEN)
                .remainingPeriods(5)
                .interestRate(BigDecimal.ONE)
                .typeOfRepayment(TypeOfRepayment.ANNUITY);
    }
}