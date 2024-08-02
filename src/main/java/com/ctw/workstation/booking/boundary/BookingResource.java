package com.ctw.workstation.booking.boundary;

import com.ctw.workstation.booking.entity.Booking;
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
import org.slf4j.MDC;

import java.util.UUID;

@Path("/bookings")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class BookingResource {

    private static final Logger logger = Logger.getLogger(BookingResource.class);

    @Inject
    BookingService bookingService;

    @POST
    public Response createBooking(Booking booking) {
        bookingService.addBooking(booking);
        logger.infof("Rack with id '%s' created.", booking.getId());
        return Response.ok(booking)
                .build();
    }

    @GET
    public Response getBookings() {
        logger.info("Fetching all bookings.");
        return Response.ok(bookingService.getBookings()).build();
    }

    @GET
    @Path("/{id}")
    public Response getBooking(@PathParam("id") UUID id) {
        UUID requestId = UUID.randomUUID();
        MDC.put("requestId", requestId.toString());
        Booking queryBooking = bookingService.getBooking(id);
        if (queryBooking == null) {
            logger.infof("Booking with id '%s' not found.", id);
            return Response.status(Response.Status.NOT_FOUND).build();
        } else {
            logger.infof("Fetching booking with id '%s'.", id);
            return Response.ok(queryBooking).build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response removeBooking(@PathParam("id") UUID id) {
        if (bookingService.removeBooking(id)) {
            logger.infof("Removing booking with id '%s'.", id);
            return Response.ok(id)
                    .build();
        } else {
            logger.infof("Booking with id '%s' not found.", id);
            return Response.status(Response.Status.NOT_FOUND)
                    .build();
        }
    }

}
