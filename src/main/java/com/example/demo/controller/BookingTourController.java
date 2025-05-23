package com.example.demo.controller;

import com.example.demo.entity.Bill;
import com.example.demo.entity.Tour;
import com.example.demo.entity.TourBooking;
import com.example.demo.entity.TourSchedule;
import com.example.demo.request.BillRequest;
import com.example.demo.request.CreateTourBookingRequest;
import com.example.demo.service.BookingTourService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/booking")
public class BookingTourController {
    private final BookingTourService bookingTourService;

    public BookingTourController(BookingTourService bookingTourService) {
        this.bookingTourService = bookingTourService;
    }

    @GetMapping("/tours")
    public ResponseEntity<List<Tour>> getTours(@RequestParam(value = "keyword", required = false) String keyword) {
        List<Tour> tours = bookingTourService.getListTours(keyword);
        return new ResponseEntity<>(tours, HttpStatus.OK);
    }

    @GetMapping("/tours/{tourId}/schedules")
    public ResponseEntity<List<TourSchedule>> getTourSchedules(@PathVariable Long tourId) {
        List<TourSchedule> schedules = bookingTourService.getListTourSchedule(tourId);
        return new ResponseEntity<>(schedules, HttpStatus.OK);
    }

    @GetMapping("/tours/{tourId}")
    public ResponseEntity<Tour> getTourById(@PathVariable Long tourId) {
        Tour tour = bookingTourService.getTourById(tourId);
        if (tour != null) {
            return new ResponseEntity<>(tour, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/schedules")
    public ResponseEntity<Bill> createTourBooking(@RequestBody CreateTourBookingRequest createTourBookingRequest) {
        Bill bill = bookingTourService.createTourBooking(createTourBookingRequest.getTour(), createTourBookingRequest.getCustomer());
        if (bill != null) {
            return new ResponseEntity<>(bill, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}