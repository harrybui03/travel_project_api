package com.example.demo.controller;

import com.example.demo.dto.TourServiceDTO;
import com.example.demo.entity.Employee;
import com.example.demo.service.Impl.TourServiceEntityServiceImpl;
import com.example.demo.service.TourService;
import org.hibernate.annotations.UpdateTimestamp;
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
    public ResponseEntity<List<TourServiceDTO>> getAllTourService() {
        List<TourServiceDTO> tourServices = new ArrayList<>();
        tourServices = tourServiceEntityService.getAllTourServices();
        return new ResponseEntity<>(tourServices, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TourServiceDTO> getTourServiceById(@PathVariable("id") Long id ) {
        TourServiceDTO tourServiceDTO = tourServiceEntityService.getTourServiceById(id);
        return new ResponseEntity<>(tourServiceDTO, HttpStatus.OK);
    }

    @PostMapping("/addTourService")
    public  ResponseEntity<TourServiceDTO> addTourService(@RequestBody TourServiceDTO tourServiceDTO) {
        TourServiceDTO savedTourServiceDTO = tourServiceEntityService.addTourService(tourServiceDTO);
        return new ResponseEntity<>(savedTourServiceDTO, HttpStatus.CREATED);

    }

    @DeleteMapping("/remove/{id}")
    public ResponseEntity<String> removeTourService(@PathVariable("id") Long id) {
        tourServiceEntityService.deleteTourService(id);
        return new ResponseEntity<>("TourService successfully removed", HttpStatus.OK);
    }

    @PatchMapping("/update/{id}")
    public ResponseEntity<TourServiceDTO> updateTourService(@PathVariable("id") Long id, @RequestBody TourServiceDTO tourServiceDTO) {
        tourServiceDTO.setId(id);
        TourServiceDTO updatedTourService = tourServiceEntityService.updateTourService(tourServiceDTO);
        return new ResponseEntity<>(updatedTourService, HttpStatus.OK);
    }
}
