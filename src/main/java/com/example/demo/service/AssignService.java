package com.example.demo.service;

import com.example.demo.entity.Employee;
import com.example.demo.entity.Tour;
import com.example.demo.entity.TourGuide;
import com.example.demo.entity.TourSchedule;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AssignService {
    List<TourSchedule> getListTours();
    List<TourGuide> getListTourGuides();
    boolean assignTour(Tour tour);
}
