package com.kushi.simulations;

import static io.gatling.javaapi.core.CoreDsl.atOnceUsers;
import static io.gatling.javaapi.core.CoreDsl.rampUsers;

import com.kushi.scenarios.RestFullBookAPI;

import io.gatling.javaapi.core.PopulationBuilder;
import io.gatling.javaapi.core.Simulation;

public class RestFullBookAPISimulation extends Simulation {

    // Define the scenarios to be executed
    PopulationBuilder createBookingScenario = RestFullBookAPI.bookingScenario.injectOpen(
            atOnceUsers(10), // 10 users at once
            rampUsers(50).during(20) // 50 users ramped up over 20 seconds
    );

	
	  PopulationBuilder getAllBookingsScenario =
	  RestFullBookAPI.getAllBookings.injectOpen( atOnceUsers(1),
	  rampUsers(5).during(10) );
	 

    PopulationBuilder getBookingsByNameScenario = RestFullBookAPI.getBookingsByName.injectOpen(
            atOnceUsers(1),
            rampUsers(50).during(20)
    );

    PopulationBuilder getBookingsByDateScenario = RestFullBookAPI.getBookingsByDate.injectOpen(
            atOnceUsers(1),
            rampUsers(50).during(20)
    );

	
	 
	 
    // Set up the simulation
    {
        setUp(
                createBookingScenario,
                getAllBookingsScenario,
                getBookingsByNameScenario,
                getBookingsByDateScenario
        ).protocols(RestFullBookAPI.httpProtocol);
    }
}
