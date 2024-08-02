package com.ctw.workstation;

import com.ctw.workstation.booking.boundary.BookingResource;
import com.ctw.workstation.booking.entity.Booking;
import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
@QuarkusTestResource(DatabaseTestResource.class)
class BookingResourceIT {

    @Inject
    BookingResource bookingResource;

    @Test
    void testGetBookings() {
        given()
                .when()
                    .get("/workstation/bookings")
                .then()
                    .statusCode(200)
                    .body(is("[]"));
    }

    @Test
    void testCreateBooking() {
        Booking booking = new Booking();
        booking.setBookFrom(LocalDateTime.now());
        booking.setBookTo(LocalDateTime.now().plusDays(7));
        bookingResource.createBooking(booking);

        given()
                .when()
                    .pathParam("id", booking.getId())
                    .get("/workstation/bookings/{id}")
                .then()
                    .statusCode(200);
    }

}
