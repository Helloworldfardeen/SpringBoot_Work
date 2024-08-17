package com.tourandtraveller.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tourandtraveller.model.Location;
import com.tourandtraveller.service.LocationService;

@RestController
@RequestMapping("/api")
public class LocationController {
    @Autowired
    private LocationService locationService;

    @PostMapping("/locations")
    public ResponseEntity<List<Location>> createLocation(@RequestBody List<Location> location) {
        List<Location> newlocations = locationService.saveDetails(location);
        return ResponseEntity.ok(newlocations);
    }

    @GetMapping("/locations")
    public ResponseEntity<List<Location>> getAllLocations() {
        List<Location> locations = locationService.showDetails();
        return ResponseEntity.ok(locations);
    }
}