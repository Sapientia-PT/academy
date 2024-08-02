package com.ctw.workstation;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

@QuarkusTest
class WorkstationResourceTest {

    @Test
    void goodGetAllBookings() {
        given()
                .when().get("/workstation/bookings")
                .then()
                .statusCode(200);
    }

    @Test
    void goodGetAllRacks() {
        given()
                .when().get("/workstation/racks")
                .then()
                .statusCode(200);
    }

    @Test
    void goodGetAllRackAssets() {
        given()
                .when().get("/workstation/rack_assets")
                .then()
                .statusCode(200);
    }

    @Test
    void goodGetAllTeams() {
        given()
                .when().get("/workstation/teams")
                .then()
                .statusCode(200);
    }

    @Test
    void goodGetAllTeamMembers() {
        given()
                .when().get("/workstation/teammembers")
                .then()
                .statusCode(200);
    }

}
