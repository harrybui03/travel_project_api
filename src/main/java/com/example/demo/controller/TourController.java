package com.example.demo.controller;

import com.example.demo.entity.Tour;
import com.example.demo.service.impl.TourServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/tours")
public class TourController {

    @Autowired
    private TourServiceImpl tourService;

    @GetMapping()
    public ResponseEntity<List<Tour>> getAllTours() {
        List<Tour> tours = new ArrayList<>();
        tours = tourService.getAllTours();
        return new ResponseEntity<>(tours, HttpStatus.OK);

    }

    @GetMapping("/{id}")
    public ResponseEntity<Tour> getTourById(@PathVariable("id") Long id) {
        Tour Tour = tourService.getTourById(id);
        return new ResponseEntity<>(Tour, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<Tour> addTour(@RequestBody Tour tour) {
        System.out.println("Received Tour: " + tour);
        Tour savedTour = tourService.addTour(tour);
        return new ResponseEntity<>(savedTour, HttpStatus.CREATED);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Tour> updateTour(@PathVariable("id") Long id, @RequestBody Tour Tour) {
        Tour.setId(id);
        Tour updatedTour = tourService.updateTour(Tour);
        return new ResponseEntity<>(updatedTour, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> removeTour(@PathVariable("id") Long id) {
        tourService.deleteTour(id);
        return new ResponseEntity<>("Tour successfully removed", HttpStatus.OK);
    }
}
