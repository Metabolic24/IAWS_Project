package iaws.ws;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.ws.test.server.MockWebServiceClient;

import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;

import static org.springframework.ws.test.server.RequestCreators.withPayload;
import static org.springframework.ws.test.server.ResponseMatchers.payload;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("application-context.xml")
public class AvailableBikesEndpointTest {

    @Autowired
    private ApplicationContext applicationContext;

    private MockWebServiceClient mockClient;

    @Before
    public void createClient() {
        mockClient = MockWebServiceClient.createClient(applicationContext);
    }

    @Test
    public void availableBikesEndpoint() throws Exception {
        Source requestPayload = new StreamSource(new ClassPathResource("AvailableBikesRequest.xml").getInputStream() );
        Source responsePayload = new StreamSource(new ClassPathResource("AvailableBikesResponse.xml").getInputStream());

        mockClient.sendRequest(withPayload(requestPayload)).
                andExpect(payload(responsePayload));
    }
}