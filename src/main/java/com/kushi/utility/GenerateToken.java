package com.kushi.utility;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.HttpEntity;
import org.apache.hc.core5.http.io.entity.StringEntity;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class GenerateToken {

    private static final String URL = "https://restful-booker.herokuapp.com/auth";
    private static final String USERNAME = "admin";
    private static final String PASSWORD = "password123";

    public static String generateToken() {
        String token = null;

        try (CloseableHttpClient client = HttpClients.createDefault()) {
            // Create the POST request
            HttpPost post = new HttpPost(URL);
            post.setHeader("Content-Type", "application/json");

            // Create the request body
            String json = "{ \"username\" : \"" + USERNAME + "\", \"password\" : \"" + PASSWORD + "\" }";
            StringEntity entity = new StringEntity(json);
            post.setEntity(entity);

            // Execute the request
            try (CloseableHttpResponse response = client.execute(post)) {
                HttpEntity responseEntity = response.getEntity();
                if (responseEntity != null) {
                    // Parse the response
                    BufferedReader reader = new BufferedReader(new InputStreamReader(responseEntity.getContent()));
                    StringBuilder result = new StringBuilder();
                    String line;
                    while ((line = reader.readLine()) != null) {
                        result.append(line);
                    }

                    // Extract token from the response
                    ObjectMapper mapper = new ObjectMapper();
                    JsonNode rootNode = mapper.readTree(result.toString());
                    token = rootNode.path("token").asText();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return token;
    }

    public static void main(String[] args) {
        // Test the token generation
        String token = generateToken();
        System.out.println("Generated Token: " + token);
    }
}
