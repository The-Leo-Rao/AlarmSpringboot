package com.alaram.demo.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import tools.jackson.databind.JsonNode;

@Service
public class PlacesService {

    @Value("${google.places.api-key}")
    private String apikey;

    private final RestClient restClient;

    public PlacesService(){
        this.restClient=RestClient.builder()
                .baseUrl("https://places.googleapis.com")
                .build();
    }

    public String searchPlaces(String query) {
        String body = """
            {
                "textQuery": "%s"
            }
            """.formatted(query);

        String response = restClient.post()
                .uri("/v1/places:searchText")
                .header("X-Goog-Api-Key", apikey)
                .header(
                        "X-Goog-FieldMask",
                        "places.id,places.displayName,places.formattedAddress,places.location"
                )
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .body(body)
                .retrieve()
                .body(String.class);

        System.out.println("Google response from Cloud Run: " + response);
        System.out.println("API key length: " + apikey.length());
        System.out.println("Query: [" + query + "]");
        System.out.println("Request body: [" + body + "]");

        return response;
    }

}
