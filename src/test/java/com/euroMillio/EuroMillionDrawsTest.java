package com.euroMillio;

import javax.ws.rs.core.Application;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class EuroMillionDrawsTest extends JerseyTest {

    @Override
    protected Application configure() {
        return new ResourceConfig(EuroMillionDraws.class);
    }

    /**
     * Test to see that the message "Got it!" is sent in the response.
     */
    @Test
    public void testGetIt() {
        final String responseMsg = target().path("myresource").request().get(String.class);

        assertEquals("Hello, Euro Million!", responseMsg);
    }
}
