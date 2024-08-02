package com.ctw.workstation.team.boundary;

import com.ctw.workstation.team.entity.Team;
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

@Path("/teams")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class TeamResource {

    private static final Logger logger = Logger.getLogger(TeamResource.class);

    @Inject
    TeamService teamService;

    @POST
    public Response createTeam(Team team) {
        teamService.addTeam(team);
        logger.infof("Team with id '%s' created.", team.getId());
        return Response.ok(team)
                .build();
    }

    @GET
    public Response getTeams() {
        logger.info("Fetching all teams.");
        return Response.ok(teamService.getTeams()).build();
    }

    @GET
    @Path("/{id}")
    public Response getTeam(@PathParam("id") UUID id) {
        Team queryTeam = teamService.getTeam(id);
        if (queryTeam == null) {
            logger.infof("Team with id '%s' not found.", id);
            return Response.status(Response.Status.NOT_FOUND).build();
        } else {
            logger.infof("Fetching Team with id '%s'.", id);
            return Response.ok(queryTeam).build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response removeTeam(@PathParam("id") UUID id) {
        if (teamService.removeTeam(id)) {
            logger.infof("Removing Team with id '%s'.", id);
            return Response.ok(id)
                    .build();
        } else {
            logger.infof("Team with id '%s' not found.", id);
            return Response.status(Response.Status.NOT_FOUND)
                    .build();
        }
    }

}
