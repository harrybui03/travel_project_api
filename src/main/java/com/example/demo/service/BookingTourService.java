package com.example.demo.service;

import com.example.demo.dto.BillDTO;
import com.example.demo.dto.CreateTourBookingRequest;
import com.example.demo.entity.Bill;
import com.example.demo.entity.Tour;
import com.example.demo.entity.TourBooking;
import com.example.demo.entity.TourSchedule;

import java.util.List;

public interface BookingTourService {
    List<Tour> getListTours(String keyword);
    List<TourSchedule> getListTourSchedule(Long tourId);
    Tour getTourById(Long tourId);
    TourBooking createTourBooking(Long tourScheduleId , CreateTourBookingRequest createTourBookingRequest);
    Bill createBill(Long tourBookingId , BillDTO billDTO);
}
