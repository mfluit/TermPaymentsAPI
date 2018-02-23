package nl.fluitit.api.termpayments;

import nl.fluitit.api.termpayments.resources.TermPaymentsResource;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

import javax.ws.rs.ApplicationPath;

@Component
@ApplicationPath("api")
public class JerseyConfig extends ResourceConfig {
    public JerseyConfig() {
        register(TermPaymentsResource.class);
    }
}
