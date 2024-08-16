package com.kushi.scenarios;

import static io.gatling.javaapi.core.CoreDsl.scenario;
import static io.gatling.javaapi.http.HttpDsl.http;
import static io.gatling.javaapi.http.HttpDsl.status;

import com.kushi.baseConfig.BaseTest;

import static io.gatling.javaapi.core.CoreDsl.csv;
import static io.gatling.javaapi.core.CoreDsl.feed;
import static io.gatling.javaapi.core.CoreDsl.StringBody;

import io.gatling.javaapi.core.ScenarioBuilder;
import io.gatling.javaapi.http.HttpProtocolBuilder;

/****
 * Author Santosh Kulkarni
 */
public class RestFullBookAPI {

    public static HttpProtocolBuilder httpProtocol = http
            .baseUrl(BaseTest.getBaseUrl())
            .contentTypeHeader("application/json");

    // Scenario for creating a booking
    public static ScenarioBuilder bookingScenario = scenario("Booking Scenario")
            .feed(csv("TestData/bookings.csv").random())
            .exec(http("Create Booking")
                    .post("/booking")
                    .body(StringBody(
                            """
                            {
                                "firstname": "#{firstname}",
                                "lastname": "#{lastname}",
                                "totalprice": #{totalprice},
                                "depositpaid": #{depositpaid},
                                "bookingdates": {
                                    "checkin": "#{checkin}",
                                    "checkout": "#{checkout}"
                                },
                                "additionalneeds": "#{additionalneeds}"
                            }
                            """
                    ))
                    .check(status().is(200))
            );

    // Scenario for getting all bookings
    public static ScenarioBuilder getAllBookings = scenario("Get All Bookings")
            .exec(http("Get All Bookings")
                    .get("/booking")
                    .check(status().is(200))
            );

    // Scenario for getting bookings by firstname and lastname
    public static ScenarioBuilder getBookingsByName = scenario("Get Bookings by Name")
            .feed(csv("TestData/names.csv").random()) // Assuming names.csv contains firstname, lastname
            .exec(http("Get Bookings by Firstname and Lastname")
                    .get("/booking")
                    .queryParam("firstname", "#{firstname}")
                    .queryParam("lastname", "#{lastname}")
                    .check(status().is(200))
            );

    // Scenario for getting bookings by checkin and checkout dates
    public static ScenarioBuilder getBookingsByDate = scenario("Get Bookings by Date")
            .feed(csv("TestData/dates.csv").random()) // Assuming dates.csv contains checkin, checkout
            .exec(http("Get Bookings by Checkin and Checkout Dates")
                    .get("/booking")
                    .queryParam("checkin", "#{checkin}")
                    .queryParam("checkout", "#{checkout}")
                    .check(status().is(200))
            );

  
}
