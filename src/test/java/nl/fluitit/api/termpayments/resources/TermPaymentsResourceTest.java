package nl.fluitit.api.termpayments.resources;

import nl.fluitit.api.termpayments.model.TermPayment;
import nl.fluitit.api.termpayments.model.TypeOfRepayment;
import nl.fluitit.api.termpayments.resources.data.TermPaymentsRequest;
import nl.fluitit.api.termpayments.services.TermPaymentsCalculatorService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TermPaymentsResourceTest {

//    @MockBean
//    private TermPaymentsCalculatorService termPaymentsCalculatorService;
    @Autowired
    private TermPaymentsResource termPaymentsResource;

//    @Before
//    public void setup() {
//        termPaymentsResource = new TermPaymentsResource(termPaymentsCalculatorService);
//    }

    @Test
    public void givenValidRequestCalculateTermPaymentsReturnsValidResponse() {
        TermPaymentsRequest request = validRequest().build();

        List<TermPayment> termPayments = termPaymentsResource.calculateTermPayments(request);

        assertThat(termPayments.size(), equalTo(6));
        assertThat(termPayments.get(0).getTerm(), equalTo(5));
        assertThat(termPayments.get(5).getTerm(), equalTo(0));
    }

    private TermPaymentsRequest.TermPaymentsRequestBuilder validRequest() {
        return TermPaymentsRequest.builder()
                .remainingDebt(BigDecimal.TEN)
                .remainingPeriods(5)
                .interestRate(BigDecimal.ONE)
                .typeOfRepayment(TypeOfRepayment.ANNUITY);
    }
}