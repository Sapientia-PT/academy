package com.ctw.workstation.rack.boundary;

import com.ctw.workstation.rack.entity.Rack;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.jboss.logging.Logger;

import java.util.UUID;

@Path("/racks")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class RackResource {

    private static final Logger logger = Logger.getLogger(RackResource.class);

    @Inject
    RackService rackService;

    @POST
    public Response createRack(Rack rack) {
        rackService.addRack(rack);
        logger.infof("Rack with id '%s' created.", rack.getId());
        return Response.status(200)
                .build();
    }

    @GET
    public Response getRacks() {
        logger.info("Fetching all racks.");
        return Response.ok(rackService.getRacks()).build();
    }

    @GET
    @Path("/{id}")
    public Response getRack(@PathParam("id") UUID id) {
        Rack queryRack = rackService.getRack(id);
        if (queryRack == null) {
            logger.infof("Rack with id '%s' not found.", id);
            return Response.status(Response.Status.NOT_FOUND).build();
        } else {
            logger.infof("Fetching rack with id '%s'.", id);
            return Response.ok(queryRack).build();
        }
    }

    @GET
    @Path("/racks")
    public Response getRacksByStatus(@QueryParam("status") String status) {
        logger.infof("Fetching all racks with status '%s'.", status);
        return Response.ok(rackService.getRacks().stream().filter(x -> x.getStatus().equals(status)))
                .build();
    }

    @DELETE
    @Path("/{id}")
    public Response removeRack(@PathParam("id") UUID id) {
        if (rackService.removeRack(id)) {
            logger.infof("Removing rack with id '%s'.", id);
            return Response.ok(id)
                    .build();
        } else {
            logger.infof("Rack with id '%s' not found.", id);
            return Response.status(Response.Status.NOT_FOUND)
                    .build();
        }
    }

    @PUT
    @Path("/{id}")
    public Response updateRack(@PathParam("id") UUID id, Rack rack) {
        logger.infof("Updating rack with id '%s'.", id);
        rackService.updateRack(id, rack);
        return Response.status(200)
                .build();
    }

}
