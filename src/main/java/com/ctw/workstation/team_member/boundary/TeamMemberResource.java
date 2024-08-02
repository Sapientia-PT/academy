package com.ctw.workstation.team_member.boundary;

import com.ctw.workstation.team_member.entity.TeamMember;
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

@Path("/teammembers")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class TeamMemberResource {

    private static final Logger logger = Logger.getLogger(TeamMemberResource.class);

    @Inject
    private TeamMemberService teamMemberService;

    @POST
    public Response createTeamMember(TeamMember teamMember) {
        teamMemberService.addTeamMember(teamMember);
        logger.infof("TeamMember with id '%s' created.", teamMember.getId());
        return Response.ok(teamMember)
                .build();
    }

    @GET
    public Response getTeamMembers() {
        logger.info("Fetching all team members.");
        return Response.ok(teamMemberService.getTeamMembers()).build();
    }

    @GET
    @Path("/{id}")
    public Response getTeamMembers(@PathParam("id") UUID id) {
        TeamMember queryTeamMember = teamMemberService.getTeamMember(id);
        if (queryTeamMember == null) {
            logger.infof("TeamMember with id '%s' not found.", id);
            return Response.status(Response.Status.NOT_FOUND).build();
        } else {
            logger.infof("Fetching TeamMember with id '%s'.", id);
            return Response.ok(queryTeamMember).build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response removeTeamMember(@PathParam("id") UUID id) {
        if (teamMemberService.removeTeamMember(id)) {
            logger.infof("Removing TeamMember with id '%s'.", id);
            return Response.ok(id)
                    .build();
        } else {
            logger.infof("TeamMember with id '%s' not found.", id);
            return Response.status(Response.Status.NOT_FOUND)
                    .build();
        }
    }

}
