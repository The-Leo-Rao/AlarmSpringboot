package com.alaram.demo.controller;

import com.alaram.demo.service.PlacesService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import tools.jackson.databind.JsonNode;

@RestController
@RequestMapping("/api/places")
public class PlacesController {

    private final PlacesService placesService;

    public PlacesController(PlacesService placesService){
        this.placesService=placesService;
    }

//    @GetMapping("/search")
//    public String searchPlaces(@RequestParam String query){
//        return placesService.searchPlaces(query);
//    }

    @GetMapping(
            value = "/search",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<String> searchPlaces(@RequestParam String query) {
        return ResponseEntity.ok(placesService.searchPlaces(query));
    }

}
