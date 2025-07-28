package com.br.caio.batman.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.br.caio.batman.entities.Locations;
import com.br.caio.batman.repository.LocationRepository;
import com.br.caio.batman.service.LocationService;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/api/locations")
public class LocationsController {

    private final LocationService locationService;
    private final LocationRepository locationRepository;

    @Autowired
    public LocationsController(LocationService locationService, LocationRepository locationRepository) {
        this.locationService = locationService;
        this.locationRepository = locationRepository;
    }

    @PostMapping
    public ResponseEntity<Locations> createLocations(@RequestBody Locations location) {
        Locations createdLocations = locationService.insert(location);
        return new ResponseEntity<>(createdLocations, HttpStatus.CREATED);
    }

    @PostMapping("/batch")
    public ResponseEntity<Void> saveAll(@RequestBody List<Locations> locations) {
        locationRepository.saveAll(locations);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    public ResponseEntity<List<Locations>> getAllLocations() {
        List<Locations> locations = locationService.listAll();
        return new ResponseEntity<>(locations, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Locations> getLocationsById(@PathVariable String id) {
        Optional<Locations> locations = locationService.findById(id);
        return locations
            .map(loc -> new ResponseEntity<>(loc, HttpStatus.OK))
            .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Locations> updateLocations(@PathVariable String id, @RequestBody Locations location) {
        Locations updatedLocations = locationService.update(id, location);
        if (updatedLocations != null) {
            return new ResponseEntity<>(updatedLocations, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLocation(@PathVariable String id) {
        boolean deleted = locationService.deleteById(id);
        return deleted
            ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
            : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
