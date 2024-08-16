Performance Testing of REST API with Gatling and Java
This guide details the steps to perform performance testing on a REST API using Gatling and Java. The API under test is the Restful Booker API.

Table of Contents
Introduction
Setup
Configuration
Scenario Creation
Execution
Configuration in pom.xml
Examples
References
Introduction
This project uses Gatling, a powerful load testing tool, to test the performance of RESTful APIs. The tests cover CRUD operations (excluding DELETE) using Gatling’s HTTP protocol builder and scenario builder, parameterized with CSV feeders.

Setup
Configuration
Configure Base URL and Headers

In your simulation class, use HttpProtocolBuilder to set up the base URL and headers. You can configure the URL through environment variables for integration tools like Jenkins.

java
Copy code
public static HttpProtocolBuilder httpProtocol = http
    .baseUrl(BaseTest.getBaseUrl())
    .contentTypeHeader("application/json");
Scenario Creation

Using ScenarioBuilder, create scenarios to test various operations. In this example, a CSV feeder is used to parameterize the requests.

java
Copy code
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
Feeder Types:

CSV
TSV
HashMap
JDBC
Arrays
Lists
Files
Feeder Features:

Queue
Random
Shuffle
Circular
Execution
To execute the performance test scripts, run the following Maven command:

bash
Copy code
mvn clean install
Configuration in pom.xml
Ensure your pom.xml is properly configured to include Gatling dependencies and any required plugins. Below is an example configuration for Gatling in pom.xml:

xml
Copy code
<dependencies>
    <!-- Gatling Dependencies -->
    <dependency>
        <groupId>io.gatling.highcharts</groupId>
        <artifactId>gatling-charts-highcharts</artifactId>
        <version>${gatling.version}</version>
        <scope>test</scope>
    </dependency>
    <dependency>
        <groupId>io.gatling</groupId>
        <artifactId>gatling-core</artifactId>
        <version>${gatling.version}</version>
        <scope>test</scope>
    </dependency>
    <!-- Other Dependencies -->
</dependencies>

<build>
    <plugins>
        <plugin>
            <groupId>com.github.eirslett</groupId>
            <artifactId>frontend-maven-plugin</artifactId>
            <version>1.12.0</version>
            <configuration>
                <arguments>install</arguments>
            </configuration>
        </plugin>
    </plugins>
</build>
Examples
Base URL and Headers Configuration:

java
Copy code
public static HttpProtocolBuilder httpProtocol = http
    .baseUrl(BaseTest.getBaseUrl())
    .contentTypeHeader("application/json");
Scenario Example Using CSV Feeder:

java
Copy code
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
References
Gatling Documentation
Restful Booker API Documentation
Gatling GitHub Repository
