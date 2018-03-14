package nl.fluitit.api.termpayments.resources;

import nl.fluitit.api.termpayments.calculation.TermPaymentsCalculator;
import nl.fluitit.api.termpayments.model.TermPayment;
import nl.fluitit.api.termpayments.resources.data.TermPaymentsRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import java.util.List;

@Component
@Path("/termpayments")
public class TermPaymentsResource {
    private TermPaymentsCalculator calculator;

    @Autowired
    public TermPaymentsResource(TermPaymentsCalculator calculator) {
        this.calculator = calculator;
    }

    @POST
    public List<TermPayment> calculateTermPayments(@Valid @RequestBody TermPaymentsRequest termPaymentsRequest) {

        return calculator.calculateTermPayments(
                termPaymentsRequest.getRemainingDebt(),
                termPaymentsRequest.getRemainingPeriods(),
                termPaymentsRequest.getInterestRate(),
                termPaymentsRequest.getTypeOfRepayment()
        );
    }
}

