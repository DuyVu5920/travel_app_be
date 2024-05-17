package com.sqa.project_sqa.controller;

import com.sqa.project_sqa.repositories.LocationRepository;
import com.sqa.project_sqa.repositories.SearchLocation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.sqa.project_sqa.entities.Location;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin
public class LocationController {
    @Autowired
    private LocationRepository locationRepository;

    @Autowired
    private SearchLocation searchLocation;

    @GetMapping("/api/v1/locations")
    public ResponseEntity<List<Location>> searchLocationsByName(@RequestParam(required = false, name = "keyword") String keyword) {
        try {
            List<Location> locations;
            if (keyword == null || keyword.isEmpty()) {
                locations = locationRepository.findAllOrderByRatingDesc().subList(0, 4);
            } else {
                locations = searchLocation.findAllByNameContainingKeyword(keyword);
            }

            if (locations.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(locations, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/api/v1/locations/{id}")
    public ResponseEntity<Location> getLocationById(@PathVariable Long id) {
        Location location = locationRepository.findById(id).orElse(null);
        if (location == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(location, HttpStatus.OK);
    }
}
