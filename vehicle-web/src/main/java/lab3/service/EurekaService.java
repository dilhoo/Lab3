package lab3.service;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Path("")
public class EurekaService {

    public static final String HEARTBEAT_ENDPOINT = "health-check";
    public static final String STATUS_ENDPOINT = "status";

    @GET
    @Path(HEARTBEAT_ENDPOINT)
    public Response heartbeat() {
        return Response.ok("alive").build();
    }

    @GET
    @Path(STATUS_ENDPOINT)
    public Response status() {
        return Response.ok().build();
    }
}