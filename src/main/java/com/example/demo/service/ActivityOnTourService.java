package com.example.demo.service;

import com.example.demo.entity.ActivityOnTour;

import java.util.List;

public interface ActivityOnTourService {
    List<ActivityOnTour> getAll();

    ActivityOnTour add(ActivityOnTour activity);
}
