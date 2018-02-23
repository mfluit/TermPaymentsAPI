package nl.fluitit.api.termpayments.resources;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TermPaymentsResourceTest {

    @LocalServerPort
    private int port;

    private URI uri;

    @Before
    public void setUp() throws Exception {
        this.uri = new URI("http://localhost:" + port);
    }

    @Test
    public void termpayments() {
        Client client = ClientBuilder.newClient();
        Response response = client
                .target(uri)
                .path("api/termpayments")
                .request(MediaType.TEXT_PLAIN)
                .get();

        String entity = response.readEntity(String.class);
        assertEquals("termpayments", entity);
    }

}