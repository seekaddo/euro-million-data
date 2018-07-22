package com.euroMillio;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static com.euroMillio.model.AwsDynamoDB.getAllItemsFromTable;
import static com.euroMillio.model.AwsDynamoDB.init;

/**
 * Root resource (exposed at "myresource" path)
 */
@Path("euro-million")
public class EuroMillionDraws {

    /**
     * Method handling HTTP GET requests. The returned object will be sent
     * to the client as "application/json" media type.
     *
     *
     *
     * This returns all euro-million draws from 2004 up to date
     * @return String that will be returned as a application/json response.
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllDraws() {

        try {
            init();
        } catch (Exception e) {
            return Response.ok().type(MediaType.APPLICATION_JSON).entity(e.getMessage()).build();
        }
        return Response.ok().type(MediaType.APPLICATION_JSON).entity(getAllItemsFromTable().toString()).build();

    }
}
