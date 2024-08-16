package com.kushi.baseConfig;


/****
 * Author Santosh Kulkarni
 */

public class BaseTest {

    private static String baseUrl;
    private static String username;
    private static String password;

    // Getters and Setters

    public static String getBaseUrl() {
        // If baseUrl is not set locally, fetch from Jenkins
        if (baseUrl == null) {
            baseUrl = System.getProperty("baseUrl", "https://restful-booker.herokuapp.com");
        }
        return baseUrl;
    }

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public static String getUsername() {
        // If username is not set locally, fetch from Jenkins
        if (username == null) {
            username = System.getProperty("username", "admin");
        }
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public static String getPassword() {
        // If password is not set locally, fetch from Jenkins
        if (password == null) {
            password = System.getProperty("password", "password123");
        }
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    // Initialization method for test setup
    public void initialize() {
        // Fetching values (This will automatically fetch from Jenkins if not set locally)
        String baseUrl = getBaseUrl();
        String username = getUsername();
        String password = getPassword();

        // Print the values for debugging purposes
        System.out.println("Base URL: " + baseUrl);
        System.out.println("Username: " + username);
        System.out.println("Password: " + password);

        // Additional setup code can go here (e.g., initializing WebDriver, setting up test data, etc.)
    }

    // Main method for testing purposes
    public static void main(String[] args) {
        BaseTest baseTest = new BaseTest();
        baseTest.initialize();
    }
}
