package nl.fluitit.api.termpayments.resources;

import nl.fluitit.api.termpayments.resources.data.TermPaymentsRequest;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.ws.rs.core.Response;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TermPaymentsResourceTest {

    TermPaymentsResource termPaymentsResource;

    @Before
    public void setup() {
        termPaymentsResource = new TermPaymentsResource();
    }

    @Test
    public void termpayments() {
        TermPaymentsRequest request = mock(TermPaymentsRequest.class);

        Response response = termPaymentsResource.termpayments(request);

        assertEquals("termpayments", response.getEntity().toString());
    }

}