package com.example.demo.controller;

import com.example.demo.dto.TourDTO;
import com.example.demo.service.Impl.TourServiceImpl;
import com.example.demo.service.TourService;
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
    public ResponseEntity<List<TourDTO>> getAllTours() {
        List<TourDTO> tours = new ArrayList<>();
        tours = tourService.getAllTours();
        return new ResponseEntity<>(tours, HttpStatus.OK);

    }

    @GetMapping("/{id}")
    public ResponseEntity<TourDTO> getTourById(@PathVariable("id") Long id) {
        TourDTO tourDTO  = tourService.getTourById(id);
        return new ResponseEntity<>(tourDTO, HttpStatus.OK);
    }

    @PostMapping("/addTour")
    public ResponseEntity<TourDTO> addTour(@RequestBody TourDTO tourDTO) {
        TourDTO savedTour = tourService.addTour(tourDTO);
        return new ResponseEntity<>(savedTour, HttpStatus.CREATED);
    }

    @PatchMapping("/updateTour/{id}")
    public ResponseEntity<TourDTO> updateTour(@PathVariable("id") Long id, @RequestBody TourDTO tourDTO) {
        tourDTO.setId(id);
        TourDTO updatedTour = tourService.updateTour(tourDTO);
        return new ResponseEntity<>(updatedTour, HttpStatus.OK);
    }

    @DeleteMapping("/remove/{id}")
    public ResponseEntity<String> removeTour(@PathVariable("id") Long id) {
        tourService.deleteTour(id);
        return new ResponseEntity<>("Tour successfully removed", HttpStatus.OK);
    }
}
