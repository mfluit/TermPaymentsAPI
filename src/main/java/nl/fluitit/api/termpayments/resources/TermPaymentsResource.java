package nl.fluitit.api.termpayments.resources;

import org.springframework.stereotype.Component;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Component
@Path("/termpayments")
public class TermPaymentsResource {

    @GET
    public Response termpayments() {
        return Response
                .ok()
                .entity("termpayments")
                .build();
    }
}

