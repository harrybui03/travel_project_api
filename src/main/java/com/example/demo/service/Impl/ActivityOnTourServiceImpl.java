package com.example.demo.service.impl;


import com.example.demo.entity.ActivityOnTour;
import com.example.demo.repository.ActivityOnTourRepository;
import com.example.demo.repository.ActivityRepository;
import com.example.demo.repository.TourRepository;
import com.example.demo.service.ActivityOnTourService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActivityOnTourServiceImpl implements ActivityOnTourService {
    @Autowired
    private ActivityOnTourRepository repository;
    @Autowired
    private TourRepository tourRepository;

    @Autowired
    private ActivityRepository activityRepository;

    @Override
    public List<ActivityOnTour> getAll() {
        return repository.findAll();
    }

    @Override
    public ActivityOnTour add(ActivityOnTour entity) {
        ActivityOnTour savedEntity = repository.save(entity);
        return savedEntity;
    }
}
