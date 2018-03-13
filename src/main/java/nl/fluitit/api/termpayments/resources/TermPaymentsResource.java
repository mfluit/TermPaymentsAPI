package nl.fluitit.api.termpayments.resources;

import nl.fluitit.api.termpayments.resources.data.TermPaymentsRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Component
@Path("/termpayments")
public class TermPaymentsResource {

    @POST
    public Response termpayments(@Valid @RequestBody TermPaymentsRequest termPaymentsRequest) {

        /*
         * TODO: - implement GET method
         *       - implement (bean) validation of the request
         *       - call TermPaymentCalculator and return result
         *       - check exception handling
         */



        return Response
                .ok()
                .entity("termpayments")
                .build();
    }
}

