# Performance Testing of REST API with Gatling and Java

This guide details the steps to perform performance testing on a REST API using Gatling and Java. The API under test is the [Restful Booker API](https://restful-booker.herokuapp.com/apidoc/).

## Table of Contents

- [Introduction](#introduction)
- [Setup](#setup)
  - [Configuration](#configuration)
  - [Scenario Creation](#scenario-creation)
- [Execution](#execution)
- [Configuration in `pom.xml`](#configuration-in-pomxml)
- [Examples](#examples)
- [References](#references)

## Introduction

This project uses Gatling, a powerful load testing tool, to test the performance of RESTful APIs. The tests cover CRUD operations (excluding DELETE) using Gatling’s HTTP protocol builder and scenario builder, parameterized with CSV feeders.

## Setup

### Configuration

1. **Configure Base URL and Headers**

   In your simulation class, use `HttpProtocolBuilder` to set up the base URL and headers. You can configure the URL through environment variables for integration tools like Jenkins.

   ```java
   public static HttpProtocolBuilder httpProtocol = http
       .baseUrl(BaseTest.getBaseUrl())
       .contentTypeHeader("application/json");
   ```

2. **Scenario Creation**

   Using `ScenarioBuilder`, create scenarios to test various operations. In this example, a CSV feeder is used to parameterize the requests.

   ```java
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
   ```

   **Feeder Types:**
   - **CSV**
   - **TSV**
   - **HashMap**
   - **JDBC**
   - **Arrays**
   - **Lists**
   - **Files**

   **Feeder Features:**
   - **Queue**
   - **Random**
   - **Shuffle**
   - **Circular**

## Execution

To execute the performance test scripts, run the following Maven command:

```bash
mvn clean install
