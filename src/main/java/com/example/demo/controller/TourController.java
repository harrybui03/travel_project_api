package com.example.demo.controller;

import com.example.demo.response.Tour;
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

    @GetMapping("listAll")
    public ResponseEntity<List<Tour>> getAllTours() {
        List<Tour> tours = new ArrayList<>();
        tours = tourService.getAllTours();
        return new ResponseEntity<>(tours, HttpStatus.OK);

    }

    @GetMapping("/{id}")
    public ResponseEntity<Tour> getTourById(@PathVariable("id") Long id) {
        Tour tour = tourService.getTourById(id);
        return new ResponseEntity<>(tour, HttpStatus.OK);
    }

    @PostMapping("/addTour")
    public ResponseEntity<Tour> addTour(@RequestBody Tour tour) {
        Tour savedTour = tourService.addTour(tour);
        return new ResponseEntity<>(savedTour, HttpStatus.CREATED);
    }

    @PatchMapping("/updateTour/{id}")
    public ResponseEntity<Tour> updateTour(@PathVariable("id") Long id, @RequestBody Tour tour) {
        tour.setId(id);
        Tour updatedTour = tourService.updateTour(tour);
        return new ResponseEntity<>(updatedTour, HttpStatus.OK);
    }

    @DeleteMapping("/remove/{id}")
    public ResponseEntity<String> removeTour(@PathVariable("id") Long id) {
        tourService.deleteTour(id);
        return new ResponseEntity<>("Tour successfully removed", HttpStatus.OK);
    }
}
