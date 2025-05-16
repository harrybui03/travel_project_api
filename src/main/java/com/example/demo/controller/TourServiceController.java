package com.example.demo.controller;

import com.example.demo.response.TourService;
import com.example.demo.service.impl.TourServiceEntityServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/tourservices")
public class TourServiceController {

    @Autowired
    TourServiceEntityServiceImpl tourServiceEntityService;

    @GetMapping("listAll")
    public ResponseEntity<List<TourService>> getAllTourService() {
        List<TourService> tourServices = new ArrayList<>();
        tourServices = tourServiceEntityService.getAllTourServices();
        return new ResponseEntity<>(tourServices, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TourService> getTourServiceById(@PathVariable("id") Long id ) {
        TourService tourService = tourServiceEntityService.getTourServiceById(id);
        return new ResponseEntity<>(tourService, HttpStatus.OK);
    }

    @PostMapping("/addTourService")
    public  ResponseEntity<TourService> addTourService(@RequestBody TourService tourService) {
        TourService savedTourService = tourServiceEntityService.addTourService(tourService);
        return new ResponseEntity<>(savedTourService, HttpStatus.CREATED);

    }

    @DeleteMapping("/remove/{id}")
    public ResponseEntity<String> removeTourService(@PathVariable("id") Long id) {
        tourServiceEntityService.deleteTourService(id);
        return new ResponseEntity<>("TourService successfully removed", HttpStatus.OK);
    }

    @PatchMapping("/update/{id}")
    public ResponseEntity<TourService> updateTourService(@PathVariable("id") Long id, @RequestBody TourService tourService) {
        tourService.setId(id);
        TourService updatedTourService = tourServiceEntityService.updateTourService(tourService);
        return new ResponseEntity<>(updatedTourService, HttpStatus.OK);
    }
}
