package nl.fluitit.api.termpayments;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.Configuration;

import javax.ws.rs.ApplicationPath;

@Configuration
@ApplicationPath("api")
public class JerseyConfig extends ResourceConfig {
    public JerseyConfig() {
        // scans the resources package for our resources
        packages(getClass().getPackage().getName() + ".resources");
    }
}
