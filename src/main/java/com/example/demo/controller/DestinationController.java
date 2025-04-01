package com.example.demo.controller;

import com.example.demo.dto.DestinationDTO;
import com.example.demo.service.DestinationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/destinations")
public class DestinationController {

    private final DestinationService destinationService;

    @Autowired
    public DestinationController(DestinationService destinationService) {
        this.destinationService = destinationService;
    }

    @PostMapping
    public ResponseEntity<DestinationDTO> createDestination(@RequestBody DestinationDTO destinationDTO) {
        DestinationDTO createdDestination = destinationService.createDestination(destinationDTO);
        return new ResponseEntity<>(createdDestination, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DestinationDTO> getDestinationById(@PathVariable Long id) {
        DestinationDTO destinationDTO = destinationService.getDestinationById(id);
        return ResponseEntity.ok(destinationDTO);
    }

    @GetMapping
    public ResponseEntity<List<DestinationDTO>> getAllDestinations() {
        List<DestinationDTO> destinations = destinationService.getAllDestinations();
        return ResponseEntity.ok(destinations);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DestinationDTO> updateDestination(
            @PathVariable Long id,
            @RequestBody DestinationDTO destinationDTO) {
        DestinationDTO updatedDestination = destinationService.updateDestination(id, destinationDTO);
        return ResponseEntity.ok(updatedDestination);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDestination(@PathVariable Long id) {
        destinationService.deleteDestination(id);
        return ResponseEntity.noContent().build();
    }

}