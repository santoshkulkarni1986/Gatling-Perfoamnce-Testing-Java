PERFORMANCE TESTING OF REST API USING GATLING AND JAVA USED https://restful-booker.herokuapp.com/apidoc/
USING THE HTTPPROTOCOLBUILDER CONFIGURED THE URL AND HEADERS. NOTE URL CAN BE CONFIGURED FROM TAKING INTEGRATION TOOLS LIKE JENKINS BY USING ENVIRONMENT VARIABLE CONCEPT
uSING THE ScenarioBuilder CREATED THE CURD OPERATIONS EXCLUDING DELETE OPERATION.
USING SIMULATION CLASS OF JAVA WRAPPER OF SCALA CLASS CREATED THE SIUMUALATION
CONFIGURED THE SIMULATION FILE IN POM.XML.
EXAMPLE OF CONFIGURING THE BASEURL AND HEADERS AS FOLLOWS 
 public static HttpProtocolBuilder httpProtocol = http
            .baseUrl(BaseTest.getBaseUrl())
            .contentTypeHeader("application/json");
EXAMPLE OF CREATING THE SCENARIO USING SCENARIOBUILDER AS FOLLOWS, WE CAN PARAMETERIZE WITH VARIOUS FEEDERS CSV,TSV,HASHMAP,JDBC,ARRAYS,LISTS AND FILES. THE FEEDERS PROVIDES THE FEATURE
OF QUEUE,RANDOM,SHUFFLE AND CIRCULAR. IN THIS HAVE USED CSV FEEDER AND CODE AS FOLLOWS
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
TO EXECUTE THE SCRIPTS mvn clean install
