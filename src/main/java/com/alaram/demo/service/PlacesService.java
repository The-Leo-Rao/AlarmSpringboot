package com.alaram.demo.service;

import org.springframework.beans.factory.annotation.Value;
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

    public JsonNode searchPlaces(String query, double lat, double lon){
        String body = """
            {
                "textQuery": "%s",
                "locationBias": {
                    "circle": {
                        "center": {
                            "latitude": %f,
                            "longitude": %f
                        },
                        "radius": 5000.0
                    }
                }
            }
            """.formatted(query, lat, lon);

        return restClient.post()
                .uri("/v1/places:searchText")
                .header("X-Goog-Api-Key", apikey)
                .header("X-Goog-FieldMask",
                        "places.id,places.displayName,places.formattedAddress,places.location"
                )
                .header("Content-Type","application/json")
                .body(body)
                .retrieve()
                .body(JsonNode.class);
    }

}
