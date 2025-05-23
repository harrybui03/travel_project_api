package com.example.demo.service;

import com.example.demo.entity.*;
import com.example.demo.request.BillRequest;
import com.example.demo.request.CreateTourBookingRequest;

import java.util.List;

public interface BookingTourService {
    List<Tour> getListTours(String keyword);
    List<TourSchedule> getListTourSchedule(Long tourId);
    Tour getTourById(Long tourId);
    Bill createTourBooking(Tour tour , Customer customer);
}
