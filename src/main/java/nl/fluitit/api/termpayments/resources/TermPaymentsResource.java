package nl.fluitit.api.termpayments.resources;

import nl.fluitit.api.termpayments.model.TermPayment;
import nl.fluitit.api.termpayments.resources.data.TermPaymentsRequest;
import nl.fluitit.api.termpayments.services.TermPaymentsCalculatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import java.util.List;

@Component
@Path("/termpayments")
public class TermPaymentsResource {
    private TermPaymentsCalculatorService termPaymentsCalculatorService;

    @Autowired
    public TermPaymentsResource(TermPaymentsCalculatorService termPaymentsCalculatorService) {
        this.termPaymentsCalculatorService = termPaymentsCalculatorService;
    }

    @POST
    @Produces("application/json")
    @Consumes("application/json")
    public List<TermPayment> calculateTermPayments(@Valid @RequestBody TermPaymentsRequest termPaymentsRequest) {
        return termPaymentsCalculatorService.calculateTermPayments(
                termPaymentsRequest.getRemainingDebt(),
                termPaymentsRequest.getRemainingPeriods(),
                termPaymentsRequest.getInterestRate(),
                termPaymentsRequest.getTypeOfRepayment()
        );
    }
}

