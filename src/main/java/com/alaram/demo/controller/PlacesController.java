package com.alaram.demo.controller;

import com.alaram.demo.service.PlacesService;
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

    @GetMapping("/search")
    public JsonNode searchPlaces(@RequestParam("query") String query,@RequestParam("lat") double lat,@RequestParam("lon") double lon){
        return placesService.searchPlaces(query,lat, lon);
    }

}
