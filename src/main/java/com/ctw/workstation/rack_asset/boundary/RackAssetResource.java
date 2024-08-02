package com.ctw.workstation.rack_asset.boundary;

import com.ctw.workstation.rack_asset.entity.RackAsset;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.jboss.logging.Logger;

import java.util.UUID;

@Path("/rack_assets")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class RackAssetResource {

    private static final Logger logger = Logger.getLogger(RackAssetResource.class);

    @Inject
    RackAssetService rackAssetService;

    @POST
    public Response createRackAsset(RackAsset rackAsset) {
        rackAssetService.addRackAsset(rackAsset);
        logger.infof("RackAsset with id '%s' created.", rackAsset.getId());
        return Response.ok(rackAsset)
                .build();
    }

    @GET
    public Response getRackAssets() {
        logger.info("Fetching all rack assets.");
        return Response.ok(rackAssetService.getRackAssets()).build();
    }

    @GET
    @Path("/{id}")
    public Response getRackAsset(@PathParam("id") UUID id) {
        RackAsset queryRack = rackAssetService.getRackAsset(id);
        if (queryRack == null) {
            logger.infof("RackAsset with id '%s' not found.", id);
            return Response.status(Response.Status.NOT_FOUND).build();
        } else {
            logger.infof("Fetching RackAsset with id '%s'.", id);
            return Response.ok(queryRack).build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response removeRackAsset(@PathParam("id") UUID id) {
        if (rackAssetService.removeRackAsset(id)) {
            logger.infof("Removing RackAsset with id '%s'.", id);
            return Response.ok(id)
                    .build();
        } else {
            logger.infof("RackAsset with id '%s' not found.", id);
            return Response.status(Response.Status.NOT_FOUND)
                    .build();
        }
    }

}
